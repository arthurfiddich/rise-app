package com.data.generator.util;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.constants.GeoNameConstants;
import com.data.generator.constants.KeyBoardConstants;
import com.data.generator.file.Reader;
import com.data.generator.file.impl.PropertyFileReader;
import com.data.generator.helper.DataGenerationConfigHelper;
import com.data.generator.penum.GeoNameProperty;

public class PropertiesHelper {

	private static Logger logger = LoggerFactory
			.getLogger(DataGenerationConfigHelper.class);
	private Reader<String, Properties> reader;
	private String configurationFile;
	private Properties geoNameProperties;

	public static PropertiesHelper createInstance(
			String argGeoNamesConfigurationFile) {
		PropertiesHelper propertyUtil = new PropertiesHelper();
		propertyUtil.setConfigurationFile(argGeoNamesConfigurationFile);
		propertyUtil.initialize();
		return propertyUtil;
	}

	private void initialize() {
		reader = new PropertyFileReader();
		Properties properties = reader.read(this.getConfigurationFile());
		Precondition.ensureNotNull(properties, "GeoNames Properties");
		this.setGeoNameProperties(properties);
	}

	public String getConfigurationFile() {
		return this.configurationFile;
	}

	public void setConfigurationFile(String argConfigurationFile) {
		this.configurationFile = argConfigurationFile;
	}

	public Properties getGeoNameProperties() {
		return this.geoNameProperties;
	}

	public void setGeoNameProperties(Properties argGeoNameProperties) {
		this.geoNameProperties = argGeoNameProperties;
	}

	public String getProperty(String argPropertyName, String... values) {
		if (Precondition.checkNotEmpty(argPropertyName)
				&& Precondition.checkNotEmpty(values)) {
			String geoNameProperty = this.getGeoNameProperties().getProperty(
					argPropertyName);
			return replaceMessage(geoNameProperty, values);
		}
		return null;
	}

	private String replaceMessage(String argPropertyName, String... argValues) {
		String messageAfterReplacement = argPropertyName;
		for (int i = 0; i < argValues.length; i++) {
			String mesage = argValues[i];
			String regex = KeyBoardConstants.OPEN_CURLY_BRACKETS + (i + 1)
					+ KeyBoardConstants.CLOSE_CURLY_BRACKETS;
			messageAfterReplacement = StringUtils.replace(
					messageAfterReplacement, regex, mesage);
		}
		return messageAfterReplacement;
	}

	public String constructGeoNameEarthUrl() {
		return this.getProperty(
				GeoNameProperty.GEO_NAME_CHILDREN_URL.getPropertyName(),
				GeoNameConstants.EARTH_GEO_NAME_ID,
				GeoNameConstants.GEO_NAME_USER_NAME);
	}

}
