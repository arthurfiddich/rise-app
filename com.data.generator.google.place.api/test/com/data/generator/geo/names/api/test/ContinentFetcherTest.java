package com.data.generator.geo.names.api.test;

import java.util.List;

import com.data.generator.constants.GeoNameConstants;
import com.data.generator.geo.names.api.Fetcher;
import com.data.generator.geo.names.api.impl.GeoLocationFetcher;
import com.data.generator.google.place.api.http.HttpClientImpl;
import com.data.generator.helper.TenantConfigHelper;
import com.data.generator.util.Precondition;
import com.generator.data.xmlns.geo.names.api.v1.GeoName;

public class ContinentFetcherTest {
	public static void main(String[] args) {
		TenantConfigHelper.getInstance();
		HttpClientImpl.getInstance();
		Fetcher<List<GeoName>> fetcher = new GeoLocationFetcher();
		List<GeoName> geoNamesList = fetcher.fetch(TenantConfigHelper
				.getInstance().getPropertiesHelper()
				.constructGeoNameUrl(GeoNameConstants.EARTH_GEO_NAME_ID));
		if (Precondition.checkNotEmpty(geoNamesList)) {
			for (GeoName geoName : geoNamesList) {
				System.out.println("Name: " + geoName.getName()
						+ " GeoNameId: " + geoName.getGeonameId());
			}
		}
	}
}
