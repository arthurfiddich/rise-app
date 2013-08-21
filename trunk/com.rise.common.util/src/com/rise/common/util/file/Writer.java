package com.rise.common.util.file;

import java.util.Properties;

public interface Writer<T, S> {

	public Properties loadProperties(S argName);

	public void write(T argParam);
}
