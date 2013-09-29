package com.rise.validaton;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocoderRequest;

public class Test {
	public static void main(String[] args) {
		final Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
				.setAddress("Paris, France").setLanguage("en")
				.getGeocoderRequest();
		com.google.code.geocoder.model.GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
		System.out.println(geocoderResponse.getResults().get(0).getFormattedAddress());
	}
}
