package com.rise.common.util.database.exporter;

import java.beans.Introspector;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.annotation.Reference;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.common.util.database.DataExporter;
import com.rise.common.util.database.DatabaseUtil;
import com.rise.common.util.database.TableDataExporter;
import com.rise.common.util.exception.DatabaseException;
import com.rise.common.util.file.AutoFileCloser;

public class ExcelDataExporter extends TableDataExporter implements
		DataExporter {

	private int originalColumnsLength;
	private String primaryKey;

	public ExcelDataExporter(String argTableName, String argFileExtension) {
		super(argTableName, argFileExtension);
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
		List<String> columnNamesList = (List<String>) Precondition
				.ensureNotEmpty(argColumnNames,
						HibernateHelperConstants.HEADER_COLUMN_NAMES_LIST);
		XSSFWorkbook xssfWorkbook = getWorkbook();
		XSSFSheet xssfSheet = createSheet(xssfWorkbook, argTableName);
		getPrimaryKey(this.getTableName());
		List<String> referencedColumnsList = prepareCompleteColumnList(argTableName);
		this.setOriginalColumnsLength(columnNamesList.size());
		addHeadersToSheet(columnNamesList, xssfSheet);
		addHeadersToSheet(referencedColumnsList, xssfSheet);
		addValuesToSheet(columnNamesList, xssfSheet);
		writeToFile(xssfWorkbook);
	}

	private void getPrimaryKey(String argTableName) {
		Connection connection = null;
		DatabaseMetaData databaseMetaData = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseUtil.getInstance().getConnection();
			databaseMetaData = connection.getMetaData();
			resultSet = databaseMetaData.getExportedKeys("", "", argTableName);
			resultSet.next();
			String primaryKey = resultSet
					.getString(HibernateHelperConstants.PKCOLUMN_NAME);
			this.setPrimaryKey(primaryKey);
			System.out.println("Primary Key: " + primaryKey);
		} catch (SQLException e) {
			DatabaseUtil.getInstance().rollback(connection);
			throw new DatabaseException(
					"Exception while getting the primary key: "
							+ this.getTableName(), e);
		} finally {
			DatabaseUtil.getInstance().close(resultSet);
			DatabaseUtil.getInstance().close(connection);
		}

	}

	private void writeToFile(XSSFWorkbook argXssfWorkbook) {
		final XSSFWorkbook xssfWorkbook = Precondition.ensureNotNull(
				argXssfWorkbook, HibernateHelperConstants.XSSF_WORK_BOOK);
		new AutoFileCloser() {

			@Override
			protected void doWork() throws Throwable {
				String dataFilePath = "database" + File.separator
						+ getDataFileName();
				FileOutputStream fileOutputStream = autoClose(fileOutputStream = new FileOutputStream(
						dataFilePath));
				xssfWorkbook.write(fileOutputStream);
			}
		};
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
			String className = Introspector.decapitalize(this.getTableName());
			while (resultSet.next()) {
				int lastRowNum = argXssfSheet.getLastRowNum();
				int rowNumber = lastRowNum + 1;
				Row row = argXssfSheet.getRow(rowNumber);
				if (row == null) {
					row = argXssfSheet.createRow(rowNumber);
				}
				prepareRow(argColumnNames, resultSet, row, className);
				prepareReferencedFieldRow(resultSet, argXssfSheet);
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

	private void prepareReferencedFieldRow(ResultSet argResultSet,
			XSSFSheet argXssfSheet) {
		Map<String, List<Reference>> modelNameVsReferenceMap = TenantConfigHelper
				.getInstance().getModelNameVsReferecesMap();
		if (Precondition.checkNotNull(modelNameVsReferenceMap)) {
			List<Reference> referencesList = modelNameVsReferenceMap.get(this
					.getTableName());
			if (Precondition.checkNotEmpty(referencesList)) {
				int columnCount = this.getOriginalColumnsLength();
				for (Reference reference : referencesList) {
					String query = buildReferencedFieldQuery(reference,
							argResultSet);
					if (Precondition.checkNotEmpty(query)) {
						String referencedClassName = getReferencedClassName(reference);
						List<String> columnNamesList = TenantConfigHelper
								.getInstance().getModelNameVsFieldNamesMap()
								.get(referencedClassName);
						if (Precondition.checkNotEmpty(columnNamesList)) {
							exportReferencedData(columnNamesList, query,
									argXssfSheet, referencedClassName,
									columnCount);
							columnCount += columnNamesList.size();
						}
					}
				}
			}
		}
	}

	private void exportReferencedData(List<String> argColumnNamesList,
			String argSqlQuery, XSSFSheet argXssfSheet,
			String argReferencedClassName, int argColumnCount) {
		BufferedWriter bufferedWriter = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int rowNumber = argXssfSheet.getLastRowNum();
		try {
			connection = DatabaseUtil.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(argSqlQuery);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				Row row = argXssfSheet.getRow(rowNumber);
				if (row == null) {
					row = argXssfSheet.createRow(rowNumber);
				}
				addEmptyValuesToTheRow(row, argColumnNamesList.size());
			}
			resultSet.previous();
			while (resultSet.next()) {
				Row row = argXssfSheet.getRow(rowNumber);
				if (row == null) {
					row = argXssfSheet.createRow(rowNumber);
					addEmptyValuesToTheRow(row, argColumnCount, "");
				}
				prepareRow(argColumnNamesList, resultSet, row,
						argReferencedClassName);
				rowNumber++;
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

	private void addEmptyValuesToTheRow(Row argRow, int argJ) {
		if (Precondition.checkNotNull(argRow)) {
			int lastCellNumber = argRow.getLastCellNum();
			for (int i = 0; i < argJ; i++) {
				Cell cell = argRow.createCell(i + lastCellNumber);
				cell.setCellValue(HibernateHelperConstants.EMPTY);
			}
		}
	}

	private void addEmptyValuesToTheRow(Row argRow, int argJ, String argDummy) {
		if (Precondition.checkNotNull(argRow)) {
			for (int i = 0; i < argJ; i++) {
				Cell cell = argRow.createCell(i);
				cell.setCellValue(HibernateHelperConstants.EMPTY);
			}
		}
	}

	private String buildReferencedFieldQuery(Reference argReference,
			ResultSet argResultSet) {
		String referencedtableName = getReferencedTableName(argReference);
		if (Precondition.checkNotEmpty(referencedtableName)) {
			return buildConditionalQuery(referencedtableName, argResultSet);
		}
		return null;
	}

	protected String getReferencedTableName(Reference argReference) {
		Map<String, String> classNameVsTableNameMap = TenantConfigHelper
				.getInstance().getClassNameVsTableNameMap();
		if (Precondition.checkNotNull(classNameVsTableNameMap)) {
			return classNameVsTableNameMap.get(argReference.className());
		}
		return null;
	}

	protected String getReferencedClassName(Reference argReference) {
		if (Precondition.checkNotNull(argReference)) {
			return argReference.name();
		}
		return HibernateHelperConstants.EMPTY;
	}

	protected String buildConditionalQuery(String referencedtableName,
			ResultSet argResultSet) {
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append(SELECT_FROM);
			queryBuilder.append(referencedtableName);
			queryBuilder.append(APOSTROPHE);
			queryBuilder.append(HibernateHelperConstants.SPACE);
			queryBuilder.append(HibernateHelperConstants.WHERE);
			queryBuilder.append(HibernateHelperConstants.SPACE);
			queryBuilder.append(this.getPrimaryKey());
			queryBuilder.append(HibernateHelperConstants.EQUALS);
			queryBuilder.append(argResultSet.getString(this.getPrimaryKey()));
			return queryBuilder.toString();
		} catch (SQLException e) {
			throw new DatabaseException(
					"Exception while getting a primary column :", e);
		}
	}

	protected void prepareRow(List<String> argColumnNames, ResultSet resultSet,
			Row argRow, String argReferencedClassName) throws SQLException {
		int lastCellNum = argRow.getLastCellNum();
		if (lastCellNum == -1) {
			lastCellNum = 0;
		}
		Map<String, String> fieldNameVsDbColumnNameMap = TenantConfigHelper
				.getInstance().getClassNameVsDbColumnNameMap()
				.get(argReferencedClassName);
		for (int i = lastCellNum; i < argColumnNames.size() + lastCellNum; i++) {
			Cell cell = argRow.createCell(i);
			String columnLabel = fieldNameVsDbColumnNameMap.get(argColumnNames
					.get(i - lastCellNum));
			if (Precondition.checkNotEmpty(columnLabel)) {
				Object object = resultSet.getObject(columnLabel);
				setCellValue(cell, object);
			} else {
				setCellValue(cell, HibernateHelperConstants.EMPTY);
			}
		}
	}

	private void setCellValue(Cell argCell, Object argObject) {
		if (argObject instanceof String) {
			argCell.setCellValue((String) argObject);
			System.out.println((String) argObject);
		} else if (argObject instanceof Integer) {
			argCell.setCellValue((Integer) argObject);
			System.out.println((Integer) argObject);
		} else if (argObject instanceof Date) {
			argCell.setCellValue((Date) argObject);
			System.out.println((Date) argObject);
		} else if (argObject instanceof Float) {
			argCell.setCellValue((Float) argObject);
			System.out.println((Float) argObject);
		} else if (argObject instanceof Double) {
			argCell.setCellValue((Double) argObject);
			System.out.println((Double) argObject);
		}
	}

	private List<String> prepareCompleteColumnList(String argTableName) {
		return getColumnsList(argTableName);
	}

	protected List<String> getColumnsList(String argTableName) {
		String tableName = Precondition.ensureNotEmpty(argTableName,
				HibernateHelperConstants.TABLE_NAME);
		List<Field> referenceFieldsList = TenantConfigHelper.getInstance()
				.getReferenceFieldsListByModelName(tableName);
		Map<String, List<String>> referenceModelNameVsColumnNamesList = TenantConfigHelper
				.getInstance()
				.getModelNameVsRefereceFieldNamePrefixVsColumnNamesListMap()
				.get(tableName);
		if (Precondition.checkNotNull(referenceModelNameVsColumnNamesList)) {
			List<String> columnNamesList = new ArrayList<String>();
			for (int i = 0; i < referenceFieldsList.size(); i++) {
				Field referenceField = referenceFieldsList.get(i);
				List<String> fieldNamesList = referenceModelNameVsColumnNamesList
						.get(referenceField.getName());
				if (Precondition.checkNotEmpty(fieldNamesList)) {
					columnNamesList.addAll(fieldNamesList);
				}
			}
			return columnNamesList;
		}
		return null;
	}

	private void addHeadersToSheet(List<String> argColumnNames,
			XSSFSheet argXssfSheet) {
		List<String> columnNamesList = (List<String>) Precondition
				.ensureNotEmpty(argColumnNames,
						HibernateHelperConstants.HEADER_COLUMN_NAMES_LIST);
		XSSFSheet xssfSheet = Precondition.ensureNotNull(argXssfSheet,
				HibernateHelperConstants.XSSF_SHEET);
		Row row = xssfSheet.getRow(HibernateHelperConstants.HEADER_INDEX);
		if (row == null) {
			row = xssfSheet.createRow(HibernateHelperConstants.HEADER_INDEX);
		}
		int cellNumber = (row.getLastCellNum() != -1) ? row.getLastCellNum()
				: 0;
		for (int i = 0; i < columnNamesList.size(); i++) {
			Cell cell = row.createCell(i + cellNumber);
			cell.setCellValue(columnNamesList.get(i));
		}
	}

	public int getOriginalColumnsLength() {
		return this.originalColumnsLength;
	}

	public void setOriginalColumnsLength(int argOriginalColumnsLength) {
		this.originalColumnsLength = argOriginalColumnsLength;
	}

	public String getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(String argPrimaryKey) {
		this.primaryKey = argPrimaryKey;
	}

}
