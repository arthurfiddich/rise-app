package com.rise.common.util.reader;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;

import com.rise.common.util.Helper.GenericJaxbHelper;

public abstract class AbstractConfigReader implements ConfigReader {

	private static final Logger logger = LogManager
			.getLogger(AbstractConfigReader.class);

	public Object read(Resource argResource, String argXsd,
			String argPackageName, boolean argSkipValidation) {
		try {
			String resourceUri = argResource.getURI().toString();
			return read(resourceUri, argResource.getInputStream(), argXsd,
					argPackageName, argSkipValidation);
		} catch (Exception e) {
			throw new RuntimeException("Failed load resource ", e);
		}
	}

	public Object read(String argConfigurationFile, InputStream argInputStream,
			String argXsd, String argPackageName, boolean argSkipValidation) {
		String resourceUri = argConfigurationFile;
		try {
			logger.info("LOADING RESOURCE: " + resourceUri);
			if (argSkipValidation) {
				return new GenericJaxbHelper<Object>(argPackageName).unmarshal(
						argInputStream).getValue();
			} else {
				return GenericJaxbHelper.unmarshalWithXsdValidation(
						resourceUri, argInputStream, argXsd, argPackageName);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed load file/resource "
					+ resourceUri, e);
		}
	}

	public Properties read(String argFilename, InputStream argInputStream) {
		try {
			logger.info("LOADING RESOURCE: " + argFilename);
			Properties properties = new Properties();
			properties.load(argInputStream);
			return properties;
		} catch (Exception e) {
			throw new RuntimeException("Failed load resource " + argFilename, e);
		}
	}
}
