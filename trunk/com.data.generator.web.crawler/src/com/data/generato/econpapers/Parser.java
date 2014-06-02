package com.data.generato.econpapers;

import java.io.File;

public interface Parser<T> {

	/*
	 * This method will accepts the HTML(Input) file name. Based on this file
	 * name it will construct the file object and process.
	 */
	public T getSource(String argFileName);

	public T getSource(File argInputFile);
}
