package com.data.generator.file.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.data.generator.constants.KeyBoardConstants;
import com.data.generator.file.AutoFileCloser;
import com.data.generator.file.Writer;
import com.data.generator.util.Precondition;

/**
 * This Class will accept a directory name through constructor. The write method
 * will get all the files in that directory and it will iterate on each and
 * every file and write that each file into an excel sheet.
 * 
 * @author Amar
 * 
 */
public class ExcelWriter implements Writer<String, String> {

	public static void main(String[] args) {
		try {
			FileOutputStream fileOut = new FileOutputStream("poi-test.xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("POI Worksheet");

			// index from 0,0... cell A1 is cell(0,0)
			HSSFRow row1 = worksheet.createRow((short) 0);

			HSSFCell cellA1 = row1.createCell(0);
			cellA1.setCellValue("Hello");
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.GOLD.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellA1.setCellStyle(cellStyle);

			HSSFCell cellB1 = row1.createCell(1);
			cellB1.setCellValue("Goodbye");
			cellStyle = workbook.createCellStyle();
			cellStyle
					.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellB1.setCellStyle(cellStyle);

			HSSFCell cellC1 = row1.createCell(2);
			cellC1.setCellValue(true);

			HSSFCell cellD1 = row1.createCell(3);
			cellD1.setCellValue(new Date());
			cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(HSSFDataFormat
					.getBuiltinFormat("m/d/yy h:mm"));
			cellD1.setCellStyle(cellStyle);

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private HSSFSheet sheet = null;
	private HSSFWorkbook workBook = null;
	private HSSFRow row = null;
	private FileOutputStream fileOutputStream = null;
	private String inputDirectoryPath;
	private int indexValue = 0;

	private ExcelWriter(String argInputDirectoryPath) {
		super();
		this.inputDirectoryPath = argInputDirectoryPath;
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

	public void addHeaders(String[] argTokens) {
		if (Precondition.checkNotEmpty(argTokens)) {
			for (int i = 0; i < argTokens.length; i++) {
				this.row.createCell(i).setCellValue(argTokens[i]);
			}
		}
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
	public void write(String argOutputFileName) {
		String path = Precondition.ensureNotEmpty(this.inputDirectoryPath,
				"Input Directory Path");
		String outputFileName = Precondition.ensureNotEmpty(argOutputFileName,
				"Output File Name");
		this.initializaFileOutputStream(outputFileName);
		this.createWorkBook();
		File filePath = new File(path);
		if (filePath.isDirectory()) {
			File[] filesArray = filePath.listFiles();
			Precondition.ensureNotEmpty(filesArray, "Files Array");
			for (int i = 0; i < filesArray.length; i++) {
				final File file = filesArray[i];
				this.createSheet(file.getName());
				new AutoFileCloser() {

					@Override
					protected void doWork() throws Throwable {
						BufferedReader bufferedReader = new BufferedReader(
								new InputStreamReader(
										new FileInputStream(file),
										Charset.forName("UTF-8")));
						String line = null;
						int i = 0;
						while (Precondition.checkNotEmpty(line = bufferedReader
								.readLine())) {
							String[] tokens = line
									.split(KeyBoardConstants.COMMA);
							createRow(i);
							if (i != 0) {

							} else {
								addHeaders(tokens);
							}
							++i;
						}
					}
				};
			}
		}
	}

}
