package com.data.generator.google.place.api.impl;

import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

import com.data.generator.geo.names.api.Fetcher;
import com.data.generator.google.place.api.exception.BaseUncheckedException;
import com.data.generator.google.place.api.http.HttpClientImpl;
import com.data.generator.google.place.api.http.HttpUtil;
import com.data.generator.util.Precondition;
import com.generator.data.xmlns.geo.names.api.v1.GeoName;
import com.generator.data.xmlns.geo.names.api.v1.GeoNames;

/**
 * This will fetch all the continents in the world. Here I am using Geo Names
 * API.
 * 
 * @author Amar
 * 
 */
public class ContinentFetcher implements Fetcher {

	String url = "http://api.geonames.org/children?geonameId=6295630&username=shivaprasad.amar";

	@Override
	public void fetch() {
		HttpClient httpClient = HttpClientImpl.getInstance().getHttpClient();
		Precondition.ensureNotNull(httpClient, "Http Client");
		HttpRequest httpRequest = HttpClientImpl.getInstance().createRequest(
				"GET", url);

		// add request header
		httpRequest.addHeader("User-Agent", "User-Agent");
		HttpResponse response = null;
		try {
			response = httpClient.execute((HttpUriRequest) httpRequest);
			Parse<GeoNames> parse = new GeoNamesParser();
			String packageName = com.generator.data.xmlns.geo.names.api.v1.GeoNames.class
					.getPackage().getName();
			GeoNames geoNames = parse.unmarshal(new GeoNames(),
					HttpUtil.getContent(response),
					"http://xmlns.data.generator.com/geo/names/api/v1",
					packageName);
			if (Precondition.checkNotNull(geoNames)) {
				int count = geoNames.getTotalResultsCount();
				if (count > 0) {
					List<GeoName> geoNamesList = geoNames.getGeoname();
					for (GeoName geoName : geoNamesList) {
						System.out.println("Name: " + geoName.getName()
								+ " GeoNameId: " + geoName.getGeonameId());
					}
				}
			}
		} catch (Exception e) {
			throw new BaseUncheckedException(
					"Exception while making a HTTP Request to the origin server: ",
					e);
		}
	}

}
