package com.data.generator.geo.names.api.test;

import com.data.generator.geo.names.api.Fetcher;
import com.data.generator.google.place.api.http.HttpClientImpl;
import com.data.generator.google.place.api.impl.ContinentFetcher;

public class ContinentFetcherTest {
	public static void main(String[] args) {
		HttpClientImpl.getInstance();
		Fetcher fetcher = new ContinentFetcher();
		fetcher.fetch();
	}
}
