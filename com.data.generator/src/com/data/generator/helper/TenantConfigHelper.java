package com.data.generator.helper;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.util.PropertiesHelper;
import com.data.generator.util.ResourceUtil;
import com.data.generator.util.TextFileHelper;

public class TenantConfigHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(TenantConfigHelper.class);
	private static final String DATA_GENERATOR_CONFIGURATION_XML_FILE = "data-generator-configuration.xml";
	private static final String GEO_NAMES_CONFIGURATION_PROPERTY_FILE = "geonames.properties";
	private static final String COUNTRIES_GEO_IDS_FILE = "countriesGeoIds.txt";
	private static TenantConfigHelper instance = createInstance();
	private DataGenerationConfigHelper dataGenerationConfigHelper;
	private PropertiesHelper propertiesHelper;
	private TextFileHelper textFileHelper;

	private static TenantConfigHelper createInstance() {
		TenantConfigHelper tenantConfigHelper = new TenantConfigHelper();
		tenantConfigHelper.initialize();
		return tenantConfigHelper;
	}

	private void initialize() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing TenantConfig:");
		}
		// URL url = ResourceUtil
		// .getResource(DATA_GENERATOR_CONFIGURATION_XML_FILE);
		// this.setDataGenerationConfigHelper(DataGenerationConfigHelper
		// .createInstance(DATA_GENERATOR_CONFIGURATION_XML_FILE));
		this.setPropertiesHelper(PropertiesHelper
				.createInstance(GEO_NAMES_CONFIGURATION_PROPERTY_FILE));
		this.setTextFileHelper(TextFileHelper
				.createInstance(COUNTRIES_GEO_IDS_FILE));
	}

	public DataGenerationConfigHelper getDataGenerationConfigHelper() {
		return this.dataGenerationConfigHelper;
	}

	public void setDataGenerationConfigHelper(
			DataGenerationConfigHelper argDataGenerationConfigHelper) {
		this.dataGenerationConfigHelper = argDataGenerationConfigHelper;
	}

	public PropertiesHelper getPropertiesHelper() {
		return this.propertiesHelper;
	}

	public void setPropertiesHelper(PropertiesHelper argPropertiesHelper) {
		this.propertiesHelper = argPropertiesHelper;
	}

	public TextFileHelper getTextFileHelper() {
		return this.textFileHelper;
	}

	public void setTextFileHelper(TextFileHelper argTextFileHelper) {
		this.textFileHelper = argTextFileHelper;
	}

	public static TenantConfigHelper getInstance() {
		return instance;
	}

}
