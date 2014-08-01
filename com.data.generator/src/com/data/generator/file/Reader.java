package com.data.generator.file;

import java.io.File;

public interface Reader<T, S> {

	public S read(T argParam);

	public S read(File argFile);
}
