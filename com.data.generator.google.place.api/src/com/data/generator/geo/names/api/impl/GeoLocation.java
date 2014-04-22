package com.data.generator.geo.names.api.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.data.generator.constants.GeoNameConstants;
import com.data.generator.constants.KeyBoardConstants;
import com.data.generator.exceptions.BaseUncheckedException;
import com.data.generator.file.AutoFileCloser;
import com.data.generator.geo.names.api.Fetcher;
import com.data.generator.helper.TenantConfigHelper;
import com.data.generator.util.Precondition;
import com.data.generator.util.PropertiesHelper;
import com.data.generator.util.TextFileHelper;
import com.generator.data.xmlns.geo.names.api.v1.GeoName;

public class GeoLocation {

	private Fetcher<List<GeoName>> geoLocationFetcher = new GeoLocationFetcher();
	private PropertiesHelper propertiesHelper = TenantConfigHelper
			.getInstance().getPropertiesHelper();
	private TextFileHelper textFileHelper = TenantConfigHelper.getInstance()
			.getTextFileHelper();
	private Fetcher<List<GeoName>> fetcher = new GeoLocationFetcher();
	private final List<String> countriesGeoIdsList = textFileHelper
			.getCountriesGeoIdsList();

	public void buildGeoLocationInformationFiles() {
		List<String> countriesGeoIdsList = this.textFileHelper
				.getCountriesGeoIdsList();
		Precondition.ensureNotEmpty(countriesGeoIdsList,
				"Countries GeoIds List");
		String url = propertiesHelper
				.constructGeoNameUrl(GeoNameConstants.EARTH_GEO_NAME_ID);
		Precondition.ensureNotEmpty(url, "Earth GeoName URL");
		buildGeoLocationInformationFiles(url, null, new StringBuilder());
	}

	private void buildGeoLocationInformationFiles(String argGeoNameUrl,
			BufferedWriter argBufferedWriter, StringBuilder argHierarchyBuilder) {
		Precondition.ensureNotEmpty(argGeoNameUrl, "GeoName URL");
		List<GeoName> geoNamesList = fetcher.fetch(argGeoNameUrl);
		String hierarchyToken = argHierarchyBuilder.toString();
		for (GeoName geoName : geoNamesList) {
			String url = this.propertiesHelper.constructGeoNameUrl(geoName
					.getGeonameId());
			if (this.getCountriesGeoIdsList().contains(geoName.getGeonameId())) {
				close(argBufferedWriter);
				BufferedWriter bufferedWriter = null;
				try {
					String toponymName = geoName.getToponymName();
					String fileLocation = constructFileLocation(toponymName);
					if (Precondition.checkNotEmpty(fileLocation)) {
						bufferedWriter = new BufferedWriter(new FileWriter(
								fileLocation));
					}
				} catch (IOException e) {
					throw new BaseUncheckedException(
							"Exception while creating an instance of BufferedWriter: ",
							e);
				}
				if(Precondition.checkNotEmpty(hierarchyToken)){
					argHierarchyBuilder.append(KeyBoardConstants.DOT);
				}
				argHierarchyBuilder.append(geoName.getToponymName());
				buildGeoLocationInformationFiles(url, bufferedWriter,
						argHierarchyBuilder);
			} else if (Precondition.checkNotNull(argBufferedWriter)) {
				List<GeoName> subGeoNamesList = fetcher.fetch(url);
				if (Precondition.checkNotEmpty(subGeoNamesList)) {
					if (Precondition.checkNotEmpty(hierarchyToken)) {
						argHierarchyBuilder.append(KeyBoardConstants.DOT);
					}
					argHierarchyBuilder.append(geoName.getToponymName());
					buildGeoLocationInformationFiles(url, argBufferedWriter,
							argHierarchyBuilder);
				} else {
					if (Precondition.checkNotEmpty(hierarchyToken)) {
						try {
							argHierarchyBuilder.append(KeyBoardConstants.DOT);
							argHierarchyBuilder
									.append(geoName.getToponymName());
							hierarchyToken = argHierarchyBuilder.toString();
							argBufferedWriter.write(hierarchyToken);
							argBufferedWriter.newLine();
							System.out.println(hierarchyToken);
						} catch (IOException e) {
							throw new BaseUncheckedException(
									"Exception while writing the content into a writer: ",
									e);
						}
						hierarchyToken = buildHierarchy(argHierarchyBuilder,
								hierarchyToken);
					}
				}
			} else if (Precondition.checkNull(argBufferedWriter)) {
				argHierarchyBuilder.append(geoName.getToponymName());
				buildGeoLocationInformationFiles(url, argBufferedWriter,
						argHierarchyBuilder);
			} else {
				argHierarchyBuilder.append(geoName.getToponymName());
			}
		}
		hierarchyToken = buildHierarchy(argHierarchyBuilder, hierarchyToken);
	}

	private void close(BufferedWriter argBufferedWriter) {
		if (Precondition.checkNotNull(argBufferedWriter)) {
			try {
				argBufferedWriter.close();
			} catch (Exception ignore) {
				// ignore
			}
		}
	}

	private String constructFileLocation(String argToponymName) {
		if (Precondition.checkNotEmpty(argToponymName)) {
			String toponymName = argToponymName;
			toponymName = StringUtils.replace(toponymName,
					KeyBoardConstants.SPACE, KeyBoardConstants.HYPHEN);
			StringBuilder fileLocationBuilder = new StringBuilder();
			fileLocationBuilder.append(KeyBoardConstants.DOT);
			fileLocationBuilder.append(KeyBoardConstants.FORWARD_SLASH);
			fileLocationBuilder.append(GeoNameConstants.OUTPUT_FOLDER_NAME);
			fileLocationBuilder.append(KeyBoardConstants.FORWARD_SLASH);
			fileLocationBuilder.append(toponymName);
			fileLocationBuilder.append(GeoNameConstants.OUTPUT_FILE_EXTENSION);
			return fileLocationBuilder.toString();
		}
		return null;
	}

	public void buildGeoLocationInformationFiles(List<GeoName> argGeoNamesList,
			BufferedWriter argBufferedWriter, StringBuilder argHierarchyBuilder) {
		if (Precondition.checkNotEmpty(argGeoNamesList)
				&& Precondition.checkNotNull(argBufferedWriter)) {
			String hierarchyToken = argHierarchyBuilder.toString();
			for (GeoName geoName : argGeoNamesList) {
				String url = this.propertiesHelper.constructGeoNameUrl(geoName
						.getGeonameId());
				List<GeoName> geoNamesList = this.geoLocationFetcher.fetch(url);
				if (Precondition.checkNotEmpty(geoNamesList)) {
					if (Precondition.checkNotEmpty(hierarchyToken)) {
						argHierarchyBuilder.append(KeyBoardConstants.DOT);
					}
					argHierarchyBuilder.append(geoName.getName());
					buildGeoLocationInformationFiles(geoNamesList,
							argBufferedWriter, argHierarchyBuilder);
				} else {
					if (Precondition.checkNotEmpty(hierarchyToken)) {
						try {
							argHierarchyBuilder.append(KeyBoardConstants.DOT);
							argHierarchyBuilder.append(geoName.getName());
							hierarchyToken = argHierarchyBuilder.toString();
							argBufferedWriter.write(hierarchyToken);
							argBufferedWriter.write(KeyBoardConstants.NEW_LINE);
						} catch (IOException e) {
							throw new BaseUncheckedException(
									"Exception while writing the content into a writer: ",
									e);
						}
						hierarchyToken = buildHierarchy(argHierarchyBuilder,
								hierarchyToken);
					}
				}
			}
			hierarchyToken = buildHierarchy(argHierarchyBuilder, hierarchyToken);
		}
	}

	protected String buildHierarchy(StringBuilder argHierarchyBuilder,
			String hierarchyToken) {
		int index = StringUtils.lastIndexOf(hierarchyToken,
				KeyBoardConstants.DOT);
		if (Precondition.checkNonNegative(index)) {
			argHierarchyBuilder.delete(index, hierarchyToken.length());
			hierarchyToken = StringUtils.substring(hierarchyToken, 0, index);
		} else {
			argHierarchyBuilder.delete(0, hierarchyToken.length());
		}
		return hierarchyToken;
	}

	public void buildCountryGeoNamesIdFile(String argOutputFileName) {
		String earthGeoNameUrl = this.propertiesHelper
				.constructGeoNameUrl(GeoNameConstants.EARTH_GEO_NAME_ID);
		buildCountryGeoNamesIdFile(earthGeoNameUrl, argOutputFileName);
	}

	public void buildCountryGeoNamesIdFile(final String argEarthUrl,
			final String argOutputFileName) {
		if (Precondition.checkNotEmpty(argEarthUrl)) {
			new AutoFileCloser() {

				@Override
				protected void doWork() throws Throwable {
					BufferedWriter bufferedWriter = autoClose(new BufferedWriter(
							new FileWriter(argOutputFileName)));
					List<GeoName> geoNamesContinentList = fetcher
							.fetch(argEarthUrl);
					if (Precondition.checkNotEmpty(geoNamesContinentList)) {
						for (GeoName continentGeoName : geoNamesContinentList) {
							String continentUrl = propertiesHelper
									.constructGeoNameUrl(continentGeoName
											.getGeonameId());
							List<GeoName> countriesGeoNamesList = fetcher
									.fetch(continentUrl);
							if (Precondition
									.checkNotEmpty(countriesGeoNamesList)) {
								for (GeoName geoName : countriesGeoNamesList) {
									bufferedWriter
											.write(geoName.getGeonameId());
									bufferedWriter.newLine();
								}
							}
						}
					}
				}
			};
		}
	}

	public List<String> getCountriesGeoIdsList() {
		return this.countriesGeoIdsList;
	}

}
