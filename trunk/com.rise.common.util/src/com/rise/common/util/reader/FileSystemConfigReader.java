package com.rise.common.util.reader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.rise.common.util.checker.Precondition;
import com.rise.common.util.file.AutoFileCloser;
import com.rise.common.util.hibernate.ResourceUtil;

public class FileSystemConfigReader extends AbstractConfigReader {

	private static final Logger logger = LoggerFactory
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
			throw new RuntimeException("Could not locate resource by pattern: "
					+ pattern);
		}
	}

	@Override
	public List<String> readTextFile(String argFileName) {
		String inputFile = Precondition.ensureNotEmpty(argFileName,
				"Input File");
		Resource resource = ResourceUtil.getFirstResourceByPattern(inputFile);
		resource = Precondition.ensureNotNull(resource, "Resource");
		try {
			String resourceUri = resource.getURI().toString();
			logger.info("LOADING RESOURCE: " + resourceUri);
			return read(resource.getInputStream());
		} catch (Exception e) {
			throw new RuntimeException("Failed load resource " + inputFile, e);
		}
	}

	private List<String> read(final InputStream argInputStream) {
		final List<String> tokens = new ArrayList<String>();
		new AutoFileCloser() {

			@Override
			protected void doWork() throws Throwable {
				InputStream inputStream = autoClose(inputStream = Precondition
						.ensureNotNull(argInputStream, "Input Stream"));
				InputStreamReader inputStreamReader = autoClose(inputStreamReader = new InputStreamReader(
						inputStream));
				BufferedReader bufferedReader = autoClose(bufferedReader = new BufferedReader(
						inputStreamReader));
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					tokens.add(line);
				}
			}
		};
		return tokens;
	}
}
