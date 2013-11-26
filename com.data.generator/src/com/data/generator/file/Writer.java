package com.data.generator.file;

import java.util.Properties;

public interface Writer<T, S> {

	public Properties loadProperties(S argName);

	public void write(T argParam);
}
