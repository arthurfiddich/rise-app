package com.rise.common.util.database.exporter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

public class ExcelDataExporter extends TableDataExporter implements
		DataExporter {

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
		List<String> referencedColumnsList = prepareCompleteColumnList(argTableName);
		if(Precondition.checkNotEmpty(referencedColumnsList)){
			columnNamesList.addAll(referencedColumnsList);
		}
		addHeadersToSheet(columnNamesList, xssfSheet);
		addValuesToSheet(argColumnNames, xssfSheet);
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
			int rowNum = 1;
			while (resultSet.next()) {
				Row row = argXssfSheet.createRow(rowNum++);
				prepareRow(argColumnNames, resultSet, row);
				prepareReferencedFieldRow(argColumnNames, resultSet, rowNum,
						argXssfSheet);
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
			ResultSet argResultSet, int argRowNum, XSSFSheet argXssfSheet) {
		Map<String, List<Reference>> modelNameVsReferenceMap = TenantConfigHelper
				.getInstance().getModelNameVsReferecesMap();
		if (Precondition.checkNotNull(modelNameVsReferenceMap)) {
			List<Reference> referencesList = modelNameVsReferenceMap.get(this
					.getTableName());
			if (Precondition.checkNotEmpty(referencesList)) {
				for (Reference reference : referencesList) {
					String query = buildReferencedFieldQuery(reference,
							argResultSet);
					if (Precondition.checkNotEmpty(query)) {
						String referencedtableName = getReferencedTableName(reference);
						List<String> columnNamesList = getColumnsList(referencedtableName);
						if (Precondition.checkNotEmpty(columnNamesList)) {
							exportReferencedData(columnNamesList, query,
									argRowNum, argXssfSheet);
						}
					}
				}
			}
		}
	}

	private void exportReferencedData(List<String> argColumnNamesList,
			String argSqlQuery, int argRowNum, XSSFSheet argXssfSheet) {
		BufferedWriter bufferedWriter = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DatabaseUtil.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(argSqlQuery);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Row row = argXssfSheet.getRow(argRowNum);
				if (row == null) {
					row = argXssfSheet.createRow(argRowNum);
				}
				prepareRow(argColumnNamesList, resultSet, row);
				argRowNum++;
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

	protected String buildConditionalQuery(String referencedtableName,
			ResultSet argResultSet) {
		try {
			StringBuilder queryBuilder = new StringBuilder();
			String primaryKeyColumnName = argResultSet.getString("");
			queryBuilder.append(SELECT_FROM);
			queryBuilder.append(referencedtableName);
			queryBuilder.append(APOSTROPHE);
			queryBuilder.append("WHERE");
			queryBuilder.append(HibernateHelperConstants.SPACE);
			queryBuilder.append(primaryKeyColumnName);
			queryBuilder.append(HibernateHelperConstants.EQUALS);
			queryBuilder.append((String) argResultSet
					.getObject(primaryKeyColumnName));
			return queryBuilder.toString();
		} catch (SQLException e) {
			throw new DatabaseException(
					"Exception while getting a primary column :", e);
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
		Row row = xssfSheet.createRow(HibernateHelperConstants.HEADER_INDEX);
		for (int i = 0; i < columnNamesList.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNamesList.get(i));
		}
	}

}
