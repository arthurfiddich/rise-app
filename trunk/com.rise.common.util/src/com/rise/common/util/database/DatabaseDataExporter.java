package com.rise.common.util.database;

import java.io.File;

import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.common.util.exception.DatabaseException;
import com.rise.common.util.file.FileExtesions;

public class DatabaseDataExporter extends AbstractDatabaseDataImporterExporter
		implements DataExporter {

	public DatabaseDataExporter(String argDirectoryName) {
		super(argDirectoryName);
	}

	@Override
	public void exportData() throws DatabaseException {

	}

	@Override
	public void exportData(String argTableName) throws DatabaseException {
		String tableName = Precondition.ensureNotEmpty(argTableName,
				HibernateHelperConstants.TABLE_NAME);
		try {
			TableDataExporter tableDataExporter = new TableDataExporter(
					tableName, FileExtesions.TXT_EXTN);
			String dataFilePath = this.getDirectoryName() + File.separator
					+ tableDataExporter.getDataFileName();
			tableDataExporter.setDataFileName(dataFilePath);
			tableDataExporter.exportData();
		} catch (Exception e) {
			throw new DatabaseException("Error while exporting data for table "
					+ tableName, e);
		}
	}
}
