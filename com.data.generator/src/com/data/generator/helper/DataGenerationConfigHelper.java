package com.data.generator.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.service.ConfigurationService;
import com.data.generator.service.XmlFileConfigurationService;
import com.generator.data.xmlns.configuration.v1.DataSourceConfiguration;

public class DataGenerationConfigHelper {

	private static Logger logger = LoggerFactory
			.getLogger(DataGenerationConfigHelper.class);
	private static final String PACKAGE_NAME = com.generator.data.xmlns.configuration.v1.ObjectFactory.class
			.getPackage().getName();
	private String configurationFile;
	private ConfigurationService configurationService;
	private DataSourceConfiguration dataSourceConfiguration;

	public DataGenerationConfigHelper() {
		super();
	}

	public static DataGenerationConfigHelper createInstance(
			String argConfigurationFile) {
		DataGenerationConfigHelper dataGenerationConfigHelper = new DataGenerationConfigHelper();
		dataGenerationConfigHelper.setConfigurationFile(argConfigurationFile);
		dataGenerationConfigHelper.initialize();
		return dataGenerationConfigHelper;
	}

	private void initialize() {
		if (logger.isInfoEnabled()) {
			logger.info("Initializing DataGenerationConfiguration: ");
		}
		try {
			this.setConfigurationService(new XmlFileConfigurationService(this
					.getConfigurationFile()));
			this.setDataSourceConfiguration(this.getConfigurationService()
					.getDataSourceConfiguration());
		} catch (Exception e) {
			throw new RuntimeException(
					"Exception while unmarshalling the XML file: "
							+ this.getConfigurationFile() + " with package "
							+ PACKAGE_NAME, e);
		}
	}

	public String getConfigurationFile() {
		return this.configurationFile;
	}

	public void setConfigurationFile(String argConfigurationFile) {
		this.configurationFile = argConfigurationFile;
	}

	public ConfigurationService getConfigurationService() {
		return this.configurationService;
	}

	public void setConfigurationService(
			ConfigurationService argConfigurationService) {
		this.configurationService = argConfigurationService;
	}

	public DataSourceConfiguration getDataSourceConfiguration() {
		return this.dataSourceConfiguration;
	}

	public void setDataSourceConfiguration(
			DataSourceConfiguration argDataSourceConfiguration) {
		this.dataSourceConfiguration = argDataSourceConfiguration;
	}

}
