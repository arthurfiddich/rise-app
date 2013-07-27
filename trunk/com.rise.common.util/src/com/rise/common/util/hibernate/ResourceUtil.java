package com.rise.common.util.hibernate;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class ResourceUtil {
	private static PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
	private static Logger logger = LogManager.getLogger(ResourceUtil.class);

	public static String getRootDirectory() {
		String rootDirectory = System.getenv("ROOT_DIRECTORY");
		if (rootDirectory == null || rootDirectory.trim().equals("")) {
			rootDirectory = ".";
		}
		return rootDirectory;
	}

	public static String getHomeDirectory() {
		String rootDirectory = getRootDirectory();
		if (rootDirectory != null && !rootDirectory.isEmpty()
				&& rootDirectory.equals(".")) {
			return rootDirectory;
		}
		return rootDirectory + File.separator + "home";
	}

	public static URL getResource(String resource) {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource(resource);
		if (url == null) {
			url = ResourceUtil.class.getResource(resource);
		}
		return url;
	}

	public static Resource[] getResources(String... relativePathNames) {
		try {
			Set<String> alreadyAdded = new HashSet<String>();
			List<Resource> allResources = new ArrayList<Resource>();
			for (String relativePathName : relativePathNames) {
				for (Resource resource : getResourcesByPattern("classpath*:/**/"
						+ relativePathName)) {
					String resourcePath = resource.getURL().toString();
					if (!alreadyAdded.contains(resourcePath)) {
						allResources.add(resource);
						alreadyAdded.add(resourcePath);
					}
				}
			}
			Resource[] resourceArray = new Resource[allResources.size()];
			for (int i = 0; i < allResources.size(); i++) {
				resourceArray[i] = allResources.get(i);
			}
			return (resourceArray);
		} catch (Exception e) {
			throw new RuntimeException(
					"Could not find resource(s) by pattern: "
							+ getPrintString(relativePathNames), e);
		}
	}

	private static Resource[] getResourcesByPattern(String... patterns) {
		Set<String> alreadyAdded = new HashSet<String>();
		List<Resource> resources = new ArrayList<Resource>();
		for (String pattern : patterns) {
			try {
				pattern = pattern.replaceAll("\\\\", "/");
				for (Resource resource : pathMatchingResourcePatternResolver
						.getResources(pattern)) {
					String resourcePath = resource.getURL().toString();
					if (!alreadyAdded.contains(resourcePath)) {
						resources.add(resource);
						alreadyAdded.add(resourcePath);
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(
						"Could not find resource(s) by pattern: " + pattern, e);
			}
		}
		Resource[] resourceArray = new Resource[resources.size()];
		resourceArray = resources.toArray(resourceArray);
		return resourceArray;
	}

	private static String getPrintString(String... relativePathNames) {
		String string = "(";
		int i = 0;
		for (String relativePathName : relativePathNames) {
			if (i != relativePathNames.length - 1) {
				string += relativePathName + ",";
			} else {
				string += relativePathName + ")";
			}
			i++;
		}
		return string;
	}

	public static Resource getFirstResourceByPattern(String relativePathName) {
		Resource[] resources = getResources(relativePathName);
		if (resources != null && resources.length > 0) {
			return resources[0];
		}
		return null;
	}

	public static String getWritableConfigFolderPath() {
		return getAbsolutePath("conf");
	}
	
	public static String getAbsolutePath(String path) {
		if (path != null) {
			if (path.charAt(0) != File.separatorChar) {
				path = getRootDirectory() + File.separatorChar + path;
			}
		}
		return path;
	}
}
