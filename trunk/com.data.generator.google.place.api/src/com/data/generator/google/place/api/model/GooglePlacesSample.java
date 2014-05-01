package com.data.generator.google.place.api.model;

import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.json.JsonHttpParser;
import com.google.api.client.json.jackson.JacksonFactory;

public class GooglePlacesSample {

	// Create our transport.
	private static final HttpTransport transport = new ApacheHttpTransport();

	// Fill in the API key you want to use.
	private static final String API_KEY = "AIzaSyBA0Hu3is9qIJ5v6NEuofigk0y-aQwqiP0";

	// The different Places API endpoints.
	//https://maps.googleapis.com/maps/api/place/nearbysearch/json
	private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/xml?";
	private static final String PLACES_AUTOCOMPLETE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
	private static final String PLACES_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/xml?";

	private static final boolean PRINT_AS_STRING = true;

	// Moscone Center, Howard Street, San Francisco, CA, United States
	// double latitude = 37.784147;
	// double longitude = -122.402115;
	double latitude = 16.41667;
	double longitude = 80.25;
	
	// telenet
	// double latitude = 51.034823;
	// double longitude = 4.483774;

	// home
	// double latitude = 50.963062;
	// double longitude = 3.522255;

	public static void main(String[] args) throws Exception {
		GooglePlacesSample sample = new GooglePlacesSample();
//		sample.performSearch();
		 sample.performDetails("CoQBdQAAAI2cBcYJLW5_t5wy9ThEuJWk3anFRjsx_CYaVbbp5xtDUwKT_xSJ4f_rCUaGyzcsFeaNwJtDNZimlz1J3ceaUXssjbTR-KdP3Be6nWfdKKsQyTF7m10OX2an7PS0JhaHzPiUWIAlMNezeTX2LI36goezaL0tTwJJrFGr1P35xC1tEhCfRUN7YGnWsbAIG0v2LfvuGhSwVpkjZBEh-0mUilWVOZLfvEeAbw");
		// sample.performAutoComplete();
	}

	public void performSearch() throws Exception {
		try {
			System.out.println("Perform Search ....");
			System.out.println("-------------------");
			HttpRequestFactory httpRequestFactory = createRequestFactory(transport);
			HttpRequest request = httpRequestFactory
					.buildGetRequest(new GenericUrl(PLACES_SEARCH_URL));
			request.url.put("key", API_KEY);
			request.url.put("location", latitude + "," + longitude);
			request.url.put("radius", 2000);
			request.url.put("sensor", "false");
//			request.url
//					.put("pagetoken",
//							"CpQDjAEAAD6e3UQvCJRRvOK2mlh45NHUVWy44nx9VBdOtoYcSd6q9MHvo44-F6Dw-uXZXCbc-amuqACVrrfrf7KZ8Csorf18PKyarO8VqL0Mje7V-auoT39tC_h1OHioW_zkExNCd1wE7TA8GjY464jl2NU9suoNdQhnIPMnCwiqoiGyBwvTMl7u5dUOS4ikNUBc7G3yZoD-2OdKVdDUkCUrlFgdYpFJFhZfsnDn23aqsQbyI4rRpCvN0eP2uyeWbc-PAjc43wE_eoZQDHQscXJn3WhbH04R4mVAq1-gn4EKzPqDwz1MtPIEnMUSZI3PTeHcqbS2gVwRAyrClDOl48IJf4waePr3uucbE-RHd5n4HELetBXJyldN3T0rXHkAwsdXr8fGEbSxLyzUYnse__VaQdAaBtv9RoUyP9ESwQwgfxBpRJapfEW8vivY7NdHUKvqj8fR0tH-KBpzO9A9gPj2AwsMXx7CN1MIn-qVr1YKXM16XvbpqsCka4LSiXTWGyOk7ZU3f5cXqwdM33L4OsHcyNt6Is4SEJFzorO6BcM4QRwmPoUsEfkaFB-jbCG4lYrHCqrPqLhB_53Y-AZ5");

			if (PRINT_AS_STRING) {
				System.out.println(request.execute().parseAsString());
			} else {

				PlacesList places = request.execute().parseAs(PlacesList.class);
				System.out.println("STATUS = " + places.status);
				for (Place place : places.results) {
					System.out.println(place);
				}
			}

		} catch (HttpResponseException e) {
			System.err.println(e.response.parseAsString());
			throw e;
		}
	}

	public void performDetails(String reference) throws Exception {
		try {
			System.out.println("Perform Place Detail....");
			System.out.println("-------------------");
			HttpRequestFactory httpRequestFactory = createRequestFactory(transport);
			HttpRequest request = httpRequestFactory
					.buildGetRequest(new GenericUrl(PLACES_DETAILS_URL));
			request.url.put("key", API_KEY);
			request.url.put("reference", reference);
			request.url.put("sensor", "false");

			HttpResponse execute = request.execute();
			if (PRINT_AS_STRING) {
				System.out.println(execute.parseAsString());
			} else {
				PlaceDetail place = execute
						.parseAs(PlaceDetail.class);
				System.out.println(place);
			}

		} catch (HttpResponseException e) {
			System.err.println(e.response.parseAsString());
			throw e;
		}
	}

	public void performAutoComplete() throws Exception {
		try {
			System.out.println("Perform Autocomplete ....");
			System.out.println("-------------------------");

			HttpRequestFactory httpRequestFactory = createRequestFactory(transport);
			HttpRequest request = httpRequestFactory
					.buildGetRequest(new GenericUrl(PLACES_AUTOCOMPLETE_URL));
			request.url.put("key", API_KEY);
			request.url.put("input", "mos");
			request.url.put("location", latitude + "," + longitude);
			request.url.put("radius", 500);
			request.url.put("sensor", "false");
			PlacesAutocompleteList places = request.execute().parseAs(
					PlacesAutocompleteList.class);
			if (PRINT_AS_STRING) {
				System.out.println(request.execute().parseAsString());
			} else {
				for (PlaceAutoComplete place : places.predictions) {
					System.out.println(place);
				}
			}

		} catch (HttpResponseException e) {
			System.err.println(e.response.parseAsString());
			throw e;
		}
	}

	public static HttpRequestFactory createRequestFactory(
			final HttpTransport transport) {

		return transport.createRequestFactory(new HttpRequestInitializer() {
			public void initialize(HttpRequest request) {
				GoogleHeaders headers = new GoogleHeaders();
				headers.setApplicationName("Google-Places-DemoApp");
				request.headers = headers;
				JsonHttpParser parser = new JsonHttpParser();
				parser.jsonFactory = new JacksonFactory();
				request.addParser(parser);
			}
		});
	}
}
