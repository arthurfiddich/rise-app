package com.data.generator.geo.names.api;

public interface Fetcher<T> {

	public T fetch(String argUrl);
}
