package com.data.generator.file.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import com.data.generator.file.Writer;
import com.data.generator.util.Precondition;

public class CsvListWriter implements
		Writer<Map<List<String>, List<List<String>>>, String> {

	private ICsvListWriter iCsvListWriter;
	private String outputFileName;

	public CsvListWriter(String outputFileName) {
		super();
		this.outputFileName = outputFileName;
		writerInitialization();
	}

	public void writerInitialization() {
		File outputFile = new File(this.outputFileName);
		try {
			this.iCsvListWriter = new org.supercsv.io.CsvListWriter(
					new OutputStreamWriter(new FileOutputStream(outputFile),
							Charset.forName("UTF-8")),
					CsvPreference.EXCEL_PREFERENCE);
		} catch (IOException e) {
			throw new RuntimeException(
					"Exception while initializing the writer", e);
		}
	}

	@Override
	public Properties loadProperties(String argName) {
		return null;
	}

	@Override
	public void write(Map<List<String>, List<List<String>>> argParam) {
		Set<Entry<List<String>, List<List<String>>>> entrySet = argParam
				.entrySet();
		for (Entry<List<String>, List<List<String>>> entry : entrySet) {
			List<String> headersList = entry.getKey();
			String[] headerArray = headersList.toArray(new String[headersList
					.size()]);
			try {
				this.iCsvListWriter.writeHeader(headerArray);
				List<List<String>> tokens = entry.getValue();
				for (List<String> list : tokens) {
					iCsvListWriter.write(list);
				}
			} catch (IOException e) {
				throw new RuntimeException(
						"Exception while writing the content into a file: ", e);
			} finally {
				if (Precondition.checkNotNull(this.iCsvListWriter)) {
					try {
						this.iCsvListWriter.close();
					} catch (Exception ignore) {
						// ignore
					}
				}
			}
		}
	}
}
