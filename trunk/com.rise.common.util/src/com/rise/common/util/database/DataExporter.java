package com.rise.common.util.database;

import com.rise.common.util.exception.DatabaseException;

public interface DataExporter {

	public void exportData() throws DatabaseException;

	public void exportData(String argTableName) throws DatabaseException;
}
