package com.rise.validation;

public interface Validation<T, S> {

	public boolean validate(T argToken) throws Exception;

	public boolean validate(T argToken, S argRegion) throws Exception;
}
