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
	double latitude = 32.212851;
	double longitude = 34.816867;
	
	
	// telenet
	// double latitude = 51.034823;
	// double longitude = 4.483774;

	// home
	// double latitude = 50.963062;
	// double longitude = 3.522255;

	public static void main(String[] args) throws Exception {
		GooglePlacesSample sample = new GooglePlacesSample();
		sample.performSearch();
//		 sample.performDetails("CoQBfwAAAPMtdQY196cvkxp7gONf1iIQljExRGNYRvalPbDFaOMj7E0NpuevRKWRFo1rlWrlPz7q09v4_FRphkmvtkR8Qs8_H8V-nW0CDax6OMbKWC__eiYJEDdnfvNubOisChOzIlAYudnB5MDGuQU_Da3m5hf4cnR2NTguBgj6GYIncskgEhAO1rJASc8zJs80izRjic5tGhTO2N7k0j_G6jjDDPJKeNNW9Y38pw");
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
			request.url
					.put("pagetoken",
							"CvQB7AAAAIPjtUPhssvv9iIXstH3wqKFDCcIufx3rtqtt6Rqfw_HizCqVHtSok08TPP0-B_-UDywWN_eX5t6-iMC6Rzv19P538sC5vHAUw-qUOc05zF-nZL-T7tbUld8IAUMWcLdiCCiBnBPic1fdsRNAz5iJvZ6vNzx24qNnKKbVAMUmCo3K3KF05ENsv5_e1iQwsSBYwZYKIBEmctPLPKwbGJnsBdPSHD9IV2b9B5UVBaiNS1RDbccev9hbSxVOaSWyUPXNKb1huh_SjD4u70xeXFNmiMK5sLyi3waKIhMdlPlQzNaQrQ1DyLF63D7uOC9s-15uBIQjSe8VrdCxzytnZAIWVCe5hoUNf5cAlnnJnC8_cuXDXwioY7UA3c");

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
