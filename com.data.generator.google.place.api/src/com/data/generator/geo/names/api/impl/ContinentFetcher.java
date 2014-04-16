package com.data.generator.geo.names.api.impl;

import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

import com.data.generator.constants.GeoNameConstants;
import com.data.generator.exceptions.BaseUncheckedException;
import com.data.generator.geo.names.api.Fetcher;
import com.data.generator.geo.names.api.Parse;
import com.data.generator.google.place.api.http.HttpClientImpl;
import com.data.generator.google.place.api.http.HttpUtil;
import com.data.generator.helper.TenantConfigHelper;
import com.data.generator.util.Precondition;
import com.data.generator.util.PropertiesHelper;
import com.generator.data.xmlns.geo.names.api.v1.GeoName;
import com.generator.data.xmlns.geo.names.api.v1.GeoNames;
import com.google.api.client.http.HttpMethod;

/**
 * This will fetch all the continents in the world. Here I am using GeoNames
 * API.
 * 
 * @author Amar
 * 
 */
public class ContinentFetcher implements Fetcher {

	private Parse<GeoNames> parse = new GeoNamesParser();

	@Override
	public void fetch() {
		PropertiesHelper propertiesHelper = TenantConfigHelper.getInstance()
				.getPropertiesHelper();
		Precondition.ensureNotNull(propertiesHelper, "Properties Helper");
		String url = propertiesHelper.constructGeoNameEarthUrl();
		HttpClient httpClient = HttpClientImpl.getInstance().getHttpClient();
		Precondition.ensureNotNull(httpClient, "Http Client");
		HttpRequest httpRequest = HttpClientImpl.getInstance().createRequest(
				HttpMethod.GET.name(), url);

		// add request header
		httpRequest.addHeader("User-Agent", "User-Agent");
		HttpResponse response = null;
		try {
			response = httpClient.execute((HttpUriRequest) httpRequest);
			String packageName = com.generator.data.xmlns.geo.names.api.v1.GeoNames.class
					.getPackage().getName();
			GeoNames geoNames = this.parse.unmarshal(new GeoNames(),
					HttpUtil.getContent(response),
					GeoNameConstants.GEO_NAME_NAMESPACE, packageName);
			if (Precondition.checkNotNull(geoNames)) {
				List<GeoName> geoNamesList = geoNames.getGeoname();
				if (Precondition.checkNotEmpty(geoNamesList)) {
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
