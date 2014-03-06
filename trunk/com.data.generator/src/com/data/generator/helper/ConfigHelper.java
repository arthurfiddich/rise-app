package com.data.generator.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigHelper {

	private static Logger logger = LoggerFactory.getLogger(ConfigHelper.class);

	private static ConfigHelper instance = createInstance();

	private static ConfigHelper createInstance() {
		ConfigHelper configHelper = new ConfigHelper();
		configHelper.initialize();
		return configHelper;
	}

	private void initialize() {
		if (logger.isDebugEnabled()) {
			logger.debug("Creating/Initializing instances for all the XML files: ");
		}
	}
	
	public static ConfigHelper getInstance() {
		return instance;
	}
}
