package com.rise.common.util.database;

public class AbstractDatabaseDataImporterExporter {

	private String directoryName;

	public AbstractDatabaseDataImporterExporter() {
		super();
	}

	public AbstractDatabaseDataImporterExporter(String argDirectoryName) {
		super();
		this.directoryName = argDirectoryName;
	}

	/**
	 * @return the directoryName
	 */
	public String getDirectoryName() {
		return this.directoryName;
	}

	/**
	 * @param argDirectoryName
	 *            the directoryName to set
	 */
	public void setDirectoryName(String argDirectoryName) {
		this.directoryName = argDirectoryName;
	}

}
