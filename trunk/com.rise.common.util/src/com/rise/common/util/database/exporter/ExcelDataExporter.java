package com.rise.common.util.database.exporter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.common.util.database.DataExporter;
import com.rise.common.util.database.DatabaseUtil;
import com.rise.common.util.database.TableDataExporter;
import com.rise.common.util.exception.DatabaseException;

public class ExcelDataExporter extends TableDataExporter implements
		DataExporter {

	public ExcelDataExporter(String argTableName, String argDataFileName,
			String argFieldDelimiter, String argRecordDelimiter) {
		super(argTableName, argDataFileName, argFieldDelimiter,
				argRecordDelimiter);
	}

	@Override
	public void exportData() throws DatabaseException {

	}

	@Override
	public void exportData(String argTableName) throws DatabaseException {
		XSSFWorkbook xssfWorkbook = getWorkbook();

		XSSFSheet xssfSheet = createSheet(xssfWorkbook, argTableName);
	}

	private XSSFSheet createSheet(XSSFWorkbook argXssfWorkbook,
			String argTableName) {
		String tableName = Precondition.ensureNotEmpty(argTableName,
				HibernateHelperConstants.TABLE_NAME);
		return argXssfWorkbook.createSheet(tableName);
	}

	protected XSSFWorkbook getWorkbook() {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		return xssfWorkbook;
	}

	@Override
	protected void write(BufferedWriter argBufferedWriter,
			ResultSet argResultSet, ResultSetMetaData argResultSetMetaData)
			throws IOException, SQLException {
		super.write(argBufferedWriter, argResultSet, argResultSetMetaData);
	}

	@Override
	public void exportData(String argTableName, List<String> argColumnNames)
			throws DatabaseException {
		XSSFWorkbook xssfWorkbook = getWorkbook();
		XSSFSheet xssfSheet = createSheet(xssfWorkbook, argTableName);
		List<String> columnNamesList = prepareCompleteColumnList(argTableName,
				argColumnNames);
		addHeadersToSheet(columnNamesList, xssfSheet);
		addValuesToSheet(argColumnNames, xssfSheet);
		super.exportData(argTableName, argColumnNames);
	}

	private void addValuesToSheet(List<String> argColumnNames,
			XSSFSheet argXssfSheet) {
		export(argColumnNames, argXssfSheet, this.getTableName());
	}

	private void export(List<String> argColumnNames, XSSFSheet argXssfSheet,
			String argTableName) {
		BufferedWriter bufferedWriter = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseUtil.getInstance().getConnection();
			String sqlQuery = SELECT_FROM + argTableName + APOSTROPHE;
			preparedStatement = connection.prepareStatement(sqlQuery);
			resultSet = preparedStatement.executeQuery();
			int rowNum = 0;
			while (resultSet.next()) {
				Row row = argXssfSheet.createRow(rowNum++);
				prepareRow(argColumnNames, resultSet, row);
				prepareReferencedFieldRow(argColumnNames, resultSet, row);
			}
			DatabaseUtil.getInstance().commit(connection);
		} catch (Exception e) {
			DatabaseUtil.getInstance().rollback(connection);
			throw new DatabaseException("Exception while exporting table: "
					+ this.getTableName() + " to file:"
					+ this.getDataFileName(), e);
		} finally {
			DatabaseUtil.getInstance().close(resultSet);
			DatabaseUtil.getInstance().close(preparedStatement);
			DatabaseUtil.getInstance().close(connection);
			if (bufferedWriter != null) {
				try {
					bufferedWriter.close();
				} catch (Exception ignore) {
					// ignore
				}
			}
		}
	}

	private void prepareReferencedFieldRow(List<String> argColumnNames,
			ResultSet argResultSet, Row argRow) {
		Map<String, List<String>> modelClassNameVsReferenceFieldNamesMap = TenantConfigHelper
				.getInstance().getModelNameVsRefereceFieldNamesMap();
		List<String> fieldNamesList = modelClassNameVsReferenceFieldNamesMap
				.get(this.getTableName());
		if (Precondition.checkNotEmpty(fieldNamesList)) {
			for (String fieldName : fieldNamesList) {
				
			}
		}
	}

	protected void prepareRow(List<String> argColumnNames, ResultSet resultSet,
			Row row) throws SQLException {
		for (int i = 0; i < argColumnNames.size(); i++) {
			Cell cell = row.createCell(i);
			Object object = resultSet.getObject(argColumnNames.get(i));
			setCellValue(cell, object);

		}
	}

	private void setCellValue(Cell argCell, Object argObject) {
		if (argObject instanceof String) {
			argCell.setCellValue((String) argObject);
		} else if (argObject instanceof Integer) {
			argCell.setCellValue((Integer) argObject);
		} else if (argObject instanceof Date) {
			argCell.setCellValue((Date) argObject);
		} else if (argObject instanceof Float) {
			argCell.setCellValue((Float) argObject);
		} else if (argObject instanceof Double) {
			argCell.setCellValue((Double) argObject);
		}
	}

	private List<String> prepareCompleteColumnList(String argTableName,
			List<String> argColumnNames) {
		List<String> columnNamesList = (List<String>) Precondition
				.ensureNotEmpty(argColumnNames,
						HibernateHelperConstants.HEADER_COLUMN_NAMES_LIST);
		String tableName = Precondition.ensureNotEmpty(argTableName,
				HibernateHelperConstants.TABLE_NAME);
		List<Field> referenceFieldsList = TenantConfigHelper.getInstance()
				.getReferenceFieldsListByModelName(argTableName);
		Map<String, List<String>> referenceModelNameVsColumnNamesList = TenantConfigHelper
				.getInstance()
				.getModelNameVsRefereceFieldNamePrefixVsColumnNamesListMap()
				.get(tableName);
		if (Precondition.checkNotNull(referenceModelNameVsColumnNamesList)) {
			for (int i = 0; i < referenceFieldsList.size(); i++) {
				Field referenceField = referenceFieldsList.get(i);
				List<String> fieldNamesList = referenceModelNameVsColumnNamesList
						.get(referenceField.getName());
				if (Precondition.checkNotEmpty(fieldNamesList)) {
					columnNamesList.addAll(fieldNamesList);
				}
			}
		}
		return columnNamesList;
	}

	private void addHeadersToSheet(List<String> argColumnNames,
			XSSFSheet argXssfSheet) {
		List<String> columnNamesList = (List<String>) Precondition
				.ensureNotEmpty(argColumnNames,
						HibernateHelperConstants.HEADER_COLUMN_NAMES_LIST);
		XSSFSheet xssfSheet = Precondition.ensureNotNull(argXssfSheet,
				HibernateHelperConstants.XSSF_SHEET);
		Row row = xssfSheet.createRow(HibernateHelperConstants.HEADER_INDEX);
		for (int i = 0; i < columnNamesList.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNamesList.get(i));
		}
	}

}
