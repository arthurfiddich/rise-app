package com.rise.common.util.reader;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;

import com.rise.common.util.hibernate.ResourceUtil;

public class FileSystemConfigReader extends AbstractConfigReader {

	private static final Logger logger = LogManager
			.getLogger(FileSystemConfigReader.class);

	@Override
	public Object readXmlFile(String argAppKey, String argFilename,
			String argXsd, String argPackageName, boolean argTenantOrgScope,
			boolean argSkipValidation) {
		return null;
	}

	@Override
	public Properties readPropertyFile(String argAppKey, String argFilename,
			boolean argTenantOrgScope) {
		return null;
	}

	@Override
	public Object readXmlFile(String argFilename, String argXsd,
			String argPackageName, boolean argSkipValidation) {
		String pattern = argFilename;
		Resource resource = ResourceUtil.getFirstResourceByPattern(pattern);
		if (resource != null) {
			logger.info("LOADING RESOURCE: " + pattern);
			return read(resource, argXsd, argPackageName, argSkipValidation);
		} else {
			throw new RuntimeException("Could not locate resource by pattern: "
					+ pattern);
		}
	}

	@Override
	public Properties readPropertyFile(String argFilename) {
		String pattern = argFilename;
		Resource resource = ResourceUtil.getFirstResourceByPattern(pattern);
		if (resource != null) {
			try {
				String resourceUri = resource.getURI().toString();
				logger.info("LOADING RESOURCE: " + resource.getURI().toString());
				return read(resourceUri, resource.getInputStream());
			} catch (Exception e) {
				throw new RuntimeException("Failed load resource " + pattern, e);
			}
		} else {
			throw new RuntimeException("Could not locate resource by pattern: " + pattern);
		}
	}

}
