package com.data.generator.file.impl;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.data.generator.file.Writer;

public class CopyOfExcelWriter implements Writer<String, String> {

	private HSSFSheet sheet = null;
	private HSSFWorkbook workBook = null;
	private HSSFRow row = null;
	private FileOutputStream fileOutputStream = null;
	private String outputFileName = null;
	private int indexValue = 0;

	private CopyOfExcelWriter() {
		super();
	}

	public void initializaFileOutputStream(String argOutputFileName) {
		try {
			this.fileOutputStream = new FileOutputStream(argOutputFileName);
		} catch (Exception e) {
			throw new RuntimeException(
					"Error Occured When Initializing The FileOutputStream", e);
		}
	}

	public void createWorkBook() {
		this.workBook = new HSSFWorkbook();
	}

	public void createSheet(String argSheetName) {
		this.sheet = this.workBook.createSheet(argSheetName);
	}

	public void createRow(int argRowNum) {
		this.row = this.sheet.createRow(argRowNum);
	}

	public void getHeaders(Cell argCell, String argString) {
		List<String> headersList = new ArrayList<String>();
		initializaFileOutputStream(argString);
		String result = argCell.getStringCellValue();
		int columnIndex = argCell.getColumnIndex();
		this.row.createCell(columnIndex).setCellValue(result);
		writeOutput();
		String elementName = getValue(result);
		headersList.add(elementName);
	}

	public String getValue(String argString) {
		return null;
	}

	private void writeOutput() {
		try {
			this.workBook.write(this.fileOutputStream);
			this.fileOutputStream.flush();
		} catch (Exception e) {
			throw new RuntimeException(
					"Error occured when the data is loded/write into a file", e);
		} finally {
			if (this.fileOutputStream != null) {
				try {
					this.fileOutputStream.close();
				} catch (Exception ignore) {
					// ignore
				}
			}
		}
	}

	public String getOutputFileName() {
		return this.outputFileName;
	}

	public void setOutputFileName(String argOutputFileName) {
		this.outputFileName = argOutputFileName;
	}

	public int getIndexValue() {
		return this.indexValue;
	}

	public void setIndexValue(int argIndexValue) {
		this.indexValue = argIndexValue;
	}

	@Override
	public Properties loadProperties(String argName) {
		return null;
	}

	@Override
	public void write(String argParam) {

	}

}
