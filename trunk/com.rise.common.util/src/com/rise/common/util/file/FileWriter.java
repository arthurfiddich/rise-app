package com.rise.common.util.file;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rise.common.util.checker.Precondition;
import com.rise.common.util.controller.components.Entity;
import com.rise.common.util.controller.components.Export;
import com.rise.common.util.controller.components.Field;
import com.rise.common.util.reader.ConfigReader;
import com.rise.common.util.reader.FileSystemConfigReader;

public class FileWriter implements Writer<Export, String> {

	private XSSFWorkbook workBook;

	private XSSFSheet sheet;

	private String fileName;

	private String sheetName;

	private FileWriter() {

	}

	private FileWriter(String argSheetName) {
		super();
		this.sheetName = argSheetName;
	}

	@Override
	public void write(Export argExport) {
		collectHeaders(argExport);
		createWorkBook();
		createSheet(this.getSheetName());
	}

	private void collectHeaders(Export argExport) {
//		Export export = Precondition.ensureNotNull(argExport, "Export Object");
//		String entityName = getEntityName(export);
//		entityName = Precondition.ensureNotEmpty(entityName,
//				"Entity/Sheet Name");
//		this.setSheetName(entityName);
//		List<String> fieldNamesList = getSelectedFields(export);
//		fieldNamesList = (List<String>) Precondition.ensureNotEmpty(fieldNamesList,
//				"Fields Name List");
	}

//	private List<String> getSelectedFields(Export argExport) {
//		List<Field> fieldsList = argExport.getFieldList();
//		return getSelectedFields(fieldsList);
//	}

	private List<String> getSelectedFields(List<Field> argFieldsList) {
		List<Field> fieldsList = (List<Field>) Precondition.ensureNotEmpty(
				argFieldsList, "Fields List");
		List<String> fieldNamesList = new ArrayList<String>();
		for (Field field : fieldsList) {
			if (Precondition.checkNotNull(field)) {
				if (field.isChecked()) {
					fieldNamesList.add(field.getFieldName());
				}
			}
		}
		return fieldNamesList;
	}

//	private String getEntityName(Export export) {
//		List<Entity> entityList = export.getEntityList();
//		return getSelectedEntityName(entityList);
//	}

	private String getSelectedEntityName(List<Entity> argEntityList) {
		List<Entity> entityList = (List<Entity>) Precondition.ensureNotEmpty(
				argEntityList, "Entity List");
		for (Entity entity : entityList) {
			if (Precondition.checkNotNull(entity)) {
				if (entity.isChecked()) {
					return entity.getEntityName();
				}
			}
		}
		return null;
	}

	/**
	 * Creates the work book.
	 */
	protected void createWorkBook() {
		this.workBook = new XSSFWorkbook();
	}

	/**
	 * Creates the sheet.
	 * 
	 * @param argSheetName
	 *            the sheet name
	 */
	protected void createSheet(String argSheetName) {
		this.sheet = this.workBook.createSheet(argSheetName);
	}

	@Override
	public Properties loadProperties(String argFileName) {
		ConfigReader configReader = new FileSystemConfigReader();
		return configReader.readPropertyFile(argFileName);
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * @return the workBook
	 */
	public XSSFWorkbook getWorkBook() {
		return this.workBook;
	}

	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return this.sheetName;
	}

	/**
	 * @param argSheetName
	 *            the sheetName to set
	 */
	public void setSheetName(String argSheetName) {
		this.sheetName = argSheetName;
	}

}
