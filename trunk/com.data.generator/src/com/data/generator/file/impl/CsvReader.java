package com.data.generator.file.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import com.data.generator.constants.CommonConstants;
import com.data.generator.exceptions.BaseUncheckedException;
import com.data.generator.file.Reader;
import com.data.generator.util.Precondition;

public class CsvReader implements
		Reader<String, Map<String[], List<List<String>>>> {

	private ICsvListReader readerInitialization(File argInputFile) {
		try {
			return new CsvListReader(new InputStreamReader(new FileInputStream(
					argInputFile), Charset.forName(CommonConstants.UTF_8)),
					CsvPreference.STANDARD_PREFERENCE);
		} catch (IOException e) {
			throw new BaseUncheckedException(
					"Exception while initializing the reader", e);
		}
	}

	@Override
	public Map<String[], List<List<String>>> read(String argInputFileName) {
		String inputFileName = Precondition.ensureNotEmpty(argInputFileName,
				"Input File Name");
		File file = new File(inputFileName);
		return read(file);
	}

	@Override
	public Map<String[], List<List<String>>> read(File argInputFile) {
		File file = Precondition.ensureNotNull(argInputFile, "Input File");
		ICsvListReader iCsvListReader = readerInitialization(file);
		String[] headersArray = getHeaders(iCsvListReader);
		Precondition.ensureNotEmpty(headersArray, "Headers Array");
		List<List<String>> tokens = new ArrayList<List<String>>();
		boolean completed = false;
		while (!completed) {
			List<String> tokensList = null;
			try {
				tokensList = iCsvListReader.read();
			} catch (IOException e) {
				throw new BaseUncheckedException(
						"Exception while reading the content from a file: "
								+ argInputFile.getName(), e);
			}
			if (Precondition.checkNotEmpty(tokensList)
					&& headersArray.length == tokensList.size()) {
				tokens.add(tokensList);
			} else {
				completed = true;
			}
		}
		Map<String[], List<List<String>>> headerArrayVsTokensListMap = new HashMap<String[], List<List<String>>>();
		headerArrayVsTokensListMap.put(headersArray, tokens);
		return headerArrayVsTokensListMap;
	}

	private String[] getHeaders(ICsvListReader argICsvListReader) {
		try {
			return argICsvListReader.getHeader(true);
		} catch (IOException e) {
			throw new BaseUncheckedException(
					"Exception while getting the headers from a file: ", e);
		}
	}
}
