package com.data.generator.helper;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.util.ResourceUtil;

public class TenantConfigHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(TenantConfigHelper.class);
	private static final String DATA_GENERATOR_CONFIGURATION_XML_FILE = "data-generator-configuration.xml";
	private static TenantConfigHelper instance = createInstance();
	private DataGenerationConfigHelper dataGenerationConfigHelper;

	private static TenantConfigHelper createInstance() {
		TenantConfigHelper tenantConfigHelper = new TenantConfigHelper();
		tenantConfigHelper.initialize();
		return tenantConfigHelper;
	}

	private void initialize() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing TenantConfig:");
		}
		URL url = ResourceUtil
				.getResource(DATA_GENERATOR_CONFIGURATION_XML_FILE);
		this.setDataGenerationConfigHelper(DataGenerationConfigHelper
				.createInstance(DATA_GENERATOR_CONFIGURATION_XML_FILE));
	}

	public DataGenerationConfigHelper getDataGenerationConfigHelper() {
		return this.dataGenerationConfigHelper;
	}

	public void setDataGenerationConfigHelper(
			DataGenerationConfigHelper argDataGenerationConfigHelper) {
		this.dataGenerationConfigHelper = argDataGenerationConfigHelper;
	}

	public static TenantConfigHelper getInstance() {
		return instance;
	}

}
