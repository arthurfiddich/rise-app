package com.data.generator.geo.names.api.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.data.generator.constants.GeoNameConstants;
import com.data.generator.geo.names.api.Fetcher;
import com.data.generator.geo.names.api.impl.GeoLocationFetcher;
import com.data.generator.geo.names.api.impl.GeoLocation;
import com.data.generator.google.place.api.http.HttpClientImpl;
import com.data.generator.helper.TenantConfigHelper;
import com.data.generator.util.Precondition;
import com.generator.data.xmlns.geo.names.api.v1.GeoName;

public class GeoLocationTest {
	public static void main(String[] args) {
		TenantConfigHelper.getInstance();
		HttpClientImpl.getInstance();
//		Fetcher<List<GeoName>> fetcher = new GeoLocationFetcher();
//		build(fetcher);
//		buildCountriesGeoNameIdFile();
		GeoLocation geoLocation = new GeoLocation();
		geoLocation.buildGeoLocationInformationFiles();
	}
	
	public static void buildCountriesGeoNameIdFile(){
		String outputFilePath = "./conf/countriesGeoIds.txt";
		new GeoLocation().buildCountryGeoNamesIdFile(outputFilePath);
	}

	protected static void build(Fetcher<List<GeoName>> fetcher) {
		BufferedWriter bufferedWriter = null;
		List<GeoName> geoNamesList = fetcher.fetch(TenantConfigHelper
				.getInstance().getPropertiesHelper()
				.constructGeoNameUrl(GeoNameConstants.EARTH_GEO_NAME_ID));
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(
					"./output/output.txt"));
			if (Precondition.checkNotEmpty(geoNamesList)) {
				for (GeoName geoName : geoNamesList) {
					List<GeoName> geoNamesList1 = fetcher
							.fetch(TenantConfigHelper
									.getInstance()
									.getPropertiesHelper()
									.constructGeoNameUrl(geoName.getGeonameId()));
					for (GeoName geoName2 : geoNamesList1) {
						System.out.println("Name: " + geoName2.getName()
								+ " GeoNameId: " + geoName2.getGeonameId());
					}
				}
			}
			// GeoLocationUtil.buildGeoLocationInformationFiles(geoNamesList,
			// bufferedWriter, new StringBuilder());
		} catch (IOException e) {
			throw new RuntimeException(
					"Exception while writing the content into a file: ", e);
		} finally {
			if (Precondition.checkNotNull(bufferedWriter)) {
				try {
					bufferedWriter.close();
				} catch (Exception ignore) {
					// ignore
				}
			}
		}
	}
}
