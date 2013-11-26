package com.data.generator.file.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import com.data.generator.file.Writer;
import com.data.generator.util.Precondition;

public class CsvWriter implements
		Writer<Map<String[], List<List<String>>>, String> {

	private ICsvMapWriter iCsvMapWriter;

	private String outputFilePath;

	public CsvWriter(String outputFilePath) {
		super();
		this.outputFilePath = outputFilePath;
		this.writerInitialization();
	}

	public void writerInitialization() {
		File outputFile = new File(this.outputFilePath);
		try {
			this.iCsvMapWriter = new CsvMapWriter(
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
	public void write(
			Map<String[], List<List<String>>> argHeaderArrayVsValuesListMap) {
		if (Precondition.checkNotNull(argHeaderArrayVsValuesListMap)) {
			Iterator<Entry<String[], List<List<String>>>> iterator = argHeaderArrayVsValuesListMap
					.entrySet().iterator();
			try {
				while (iterator.hasNext()) {
					Entry<String[], List<List<String>>> entry = iterator.next();
					String[] headers = entry.getKey();
					if (this.iCsvMapWriter.getLineNumber() == 0) {
						this.iCsvMapWriter.writeHeader(headers);
					}
					List<List<String>> tokensList = entry.getValue();
					if (Precondition.checkNotEmpty(tokensList)) {
						for (List<String> list : tokensList) {
							Map<String, String> headerVsPlainTokenMap = buildMap(headers, list);
							if (headerVsPlainTokenMap != null) {
								this.iCsvMapWriter.write(headerVsPlainTokenMap,
										headers);
							}
						}
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(
						"Exception while writing the file content", e);
			} finally {
				if (this.iCsvMapWriter != null) {
					try {
						this.iCsvMapWriter.close();
					} catch (Exception ignore) {
						// ignore
					}
				}
			}
		}
	}
	
	private Map<String, String> buildMap(String[] argHeaders,
			List<String> argPlainTokensList) {
		if (argHeaders != null && argHeaders.length > 0
				&& argPlainTokensList != null && argPlainTokensList.size() > 0) {
			Map<String, String> headerVsPlainTokenMap = new LinkedHashMap<String, String>();
			for (int i = 0; i < argHeaders.length; i++) {
				headerVsPlainTokenMap.put(argHeaders[i],
						argPlainTokensList.get(i));
			}
			return headerVsPlainTokenMap;
		}
		return null;
	}

}
