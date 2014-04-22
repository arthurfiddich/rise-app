package com.data.generator.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.file.Reader;
import com.data.generator.file.impl.TextFileReader;
import com.data.generator.helper.DataGenerationConfigHelper;

public class TextFileHelper {

	private static Logger logger = LoggerFactory
			.getLogger(DataGenerationConfigHelper.class);
	private Reader<String, List<String>> reader;
	private String configurationFile;
	private List<String> countriesGeoIdsList;

	public static TextFileHelper createInstance(
			String argGeoNamesConfigurationFile) {
		logger.debug("Creating instance...");
		TextFileHelper textFileHelper = new TextFileHelper();
		textFileHelper.setConfigurationFile(argGeoNamesConfigurationFile);
		textFileHelper.initialize();
		return textFileHelper;
	}

	private void initialize() {
		reader = new TextFileReader();
		List<String> countriesGeoIdsList = reader.read(this.getConfigurationFile());
		Precondition.ensureNotNull(countriesGeoIdsList, "GeoNames Properties");
		this.setCountriesGeoIdsList(countriesGeoIdsList);
	}

	public String getConfigurationFile() {
		return this.configurationFile;
	}

	public void setConfigurationFile(String argConfigurationFile) {
		this.configurationFile = argConfigurationFile;
	}

	public List<String> getCountriesGeoIdsList() {
		return this.countriesGeoIdsList;
	}

	public void setCountriesGeoIdsList(List<String> argCountriesGeoIdsList) {
		this.countriesGeoIdsList = argCountriesGeoIdsList;
	}

}
