package com.rise.validaton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.rise.validaton.GeocodeResponse.Result;

public class GeoCoder {
	private Gson gson = new Gson();

	private volatile long lastRequest = 0L;

	public GeocodeResponse getLocation(String... addressElements)
			throws JsonSyntaxException, JsonIOException, MalformedURLException,
			IOException {
		StringBuilder sb = new StringBuilder();
		for (String string : addressElements) {
			if (sb.length() > 0) {
				sb.append('+');
			}
			sb.append(URLEncoder.encode(string.replace(' ', '+'), "UTF-8"));
		}
		String url = "http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address="
				+ sb.toString();
		// Google limits this web service to 2500/day and 10 requests/s
		synchronized (this) {
			try {
				long elapsed = System.currentTimeMillis() - lastRequest;
				if (elapsed < 100) {
					try {
						Thread.sleep(100 - elapsed);
					} catch (InterruptedException e) {
					}
				}
				return gson.fromJson(new BufferedReader(new InputStreamReader(
						new URL(url).openStream())), GeocodeResponse.class);
			} finally {
				lastRequest = System.currentTimeMillis();
			}
		}
	}

	public static void main(String[] args) throws JsonSyntaxException,
			JsonIOException, MalformedURLException, IOException {
		GeoCoder geoCoder = new GeoCoder();
		GeocodeResponse geocodeResponse = geoCoder
				.getLocation("lkfjlsdjflsjflflnjdl");
		System.out.println("Response Status:" + geocodeResponse.getStatus());
		List<Result> resultsList = geocodeResponse.getResults();
		for (Result result : resultsList) {
			System.out.println(result.getFormatted_address());
		}
		System.out.println(geocodeResponse.toString());
	}
}
