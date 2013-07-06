package com.rise.common.util.converters;

public interface Converter<T, S> {

	public T convert(S argToken);
}
