package com.data.generator.ideas.repec.org;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.data.generato.econpapers.HtmlExtractorUtil;
import com.data.generator.custom.annotation.Reference;
import com.data.generator.custom.annotation.Variable;
import com.data.generator.util.Precondition;

public class ObjectWriter {

	private final String outputFilePath;

	public ObjectWriter(String argOutputFilePath) {
		super();
		this.outputFilePath = argOutputFilePath;
	}

	public void write(List<Class<?>> argClassInstanceList,
			Class<? extends Annotation> argAnnotationClass) {
		List<Class<?>> clazzList = Precondition.ensureNotNull(
				argClassInstanceList, "Object");
		Class<? extends Annotation> annotationClass = Precondition
				.ensureNotNull(argAnnotationClass, "AnnotationClass");

		XSSFWorkbook xssfWorkbook = getWorkbook();
		Class<?> class1 = argClassInstanceList.get(0);
		String name = class1.getName();
		XSSFSheet xssfSheet = createSheet(xssfWorkbook, name);
		List<String> actualColumnsList = prepareCompleteColumnList(class1,
				Variable.class);
		List<String> referencedColumnsList = prepareCompleteColumnList(class1,
				Reference.class);
		addHeadersToSheet(actualColumnsList, xssfSheet);
		addHeadersToSheet(referencedColumnsList, xssfSheet);
		addValuesToSheet(actualColumnsList, xssfSheet, name, clazzList);

	}

	private void addHeadersToSheet(List<String> argColumnNames,
			XSSFSheet argXssfSheet) {
		List<String> columnNamesList = (List<String>) Precondition
				.ensureNotEmpty(argColumnNames, "Column Name Headers");
		XSSFSheet xssfSheet = Precondition.ensureNotNull(argXssfSheet,
				"XSSF Sheet");
		Row row = xssfSheet.getRow(0);
		if (row == null) {
			row = xssfSheet.createRow(0);
		}
		int cellNumber = (row.getLastCellNum() != -1) ? row.getLastCellNum()
				: 0;
		for (int i = 0; i < columnNamesList.size(); i++) {
			Cell cell = row.createCell(i + cellNumber);
			cell.setCellValue(columnNamesList.get(i));
		}
	}

	private void addValuesToSheet(List<String> argColumnNames,
			XSSFSheet argXssfSheet, String argName, List<Class<?>> argClazzList) {
		export(argColumnNames, argXssfSheet, argName, argClazzList);
	}

	private void export(List<String> argColumnNames, XSSFSheet argXssfSheet,
			String argTableName, List<Class<?>> argClazzList) {
		for (int i = 0; i < argClazzList.size(); i++) {
			int lastRowNum = argXssfSheet.getLastRowNum();
			int rowNumber = lastRowNum + 1;
			Row row = argXssfSheet.getRow(rowNumber);
			if (row == null) {
				row = argXssfSheet.createRow(rowNumber);
			}
//			prepareRow(argColumnNames, resultSet, row, argTableName);
//			prepareReferencedFieldRow(resultSet, argXssfSheet);
		}
	}

	private List<String> prepareCompleteColumnList(Class<?> argClassInstance,
			Class<? extends Annotation> argAnnotationClass) {
		List<Field> fieldsList = HtmlExtractorUtil.getAnnotatedFields(
				argClassInstance, argAnnotationClass);
		Precondition.ensureNotEmpty(fieldsList, "Fields List");
		List<String> actualColumnsList = new ArrayList<String>();
		for (Field field : fieldsList) {
			actualColumnsList.add(field.getName());
		}
		return actualColumnsList;
	}

	protected XSSFWorkbook getWorkbook() {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		return xssfWorkbook;
	}

	private XSSFSheet createSheet(XSSFWorkbook argXssfWorkbook, String argName) {
		String name = Precondition.ensureNotEmpty(argName, "Instance Name");
		return argXssfWorkbook.createSheet(name);
	}

	public String getOutputFilePath() {
		return this.outputFilePath;
	}

}
