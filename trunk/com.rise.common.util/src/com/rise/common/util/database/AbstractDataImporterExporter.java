package com.rise.common.util.database;

public abstract class AbstractDataImporterExporter {

	private static final String DEFAULT_FIELD_DELIMITER = "\t";
	private static final String DEFAULT_RECORD_DELIMITER = "\r\n";

	private String tableName;
	private String dataFileName;
	private String fieldDelimiter;
	private String recordDelimiter;
	private String fileExtension;

	public AbstractDataImporterExporter() {
		super();
	}

	public AbstractDataImporterExporter(String argTableName,
			String argFileExtension) {
		this(argTableName, argTableName + argFileExtension,
				DEFAULT_FIELD_DELIMITER, DEFAULT_RECORD_DELIMITER);
	}

	public AbstractDataImporterExporter(String argTableName,
			String argDataFileName, String argFieldDelimiter,
			String argRecordDelimiter) {
		super();
		this.tableName = argTableName;
		this.dataFileName = argDataFileName;
		this.fieldDelimiter = argFieldDelimiter;
		this.recordDelimiter = argRecordDelimiter;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return this.tableName;
	}

	/**
	 * @param argTableName
	 *            the tableName to set
	 */
	public void setTableName(String argTableName) {
		this.tableName = argTableName;
	}

	/**
	 * @return the dataFileName
	 */
	public String getDataFileName() {
		return this.dataFileName;
	}

	/**
	 * @param argDataFileName
	 *            the dataFileName to set
	 */
	public void setDataFileName(String argDataFileName) {
		this.dataFileName = argDataFileName;
	}

	/**
	 * @return the fieldDelimiter
	 */
	public String getFieldDelimiter() {
		return this.fieldDelimiter;
	}

	/**
	 * @param argFieldDelimiter
	 *            the fieldDelimiter to set
	 */
	public void setFieldDelimiter(String argFieldDelimiter) {
		this.fieldDelimiter = argFieldDelimiter;
	}

	/**
	 * @return the recordDelimiter
	 */
	public String getRecordDelimiter() {
		return this.recordDelimiter;
	}

	/**
	 * @param argRecordDelimiter
	 *            the recordDelimiter to set
	 */
	public void setRecordDelimiter(String argRecordDelimiter) {
		this.recordDelimiter = argRecordDelimiter;
	}

	/**
	 * @return the fileExtension
	 */
	public String getFileExtension() {
		return this.fileExtension;
	}

	/**
	 * @param argFileExtension
	 *            the fileExtension to set
	 */
	public void setFileExtension(String argFileExtension) {
		this.fileExtension = argFileExtension;
	}

}
