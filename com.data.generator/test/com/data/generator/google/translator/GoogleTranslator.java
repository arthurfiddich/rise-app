package com.data.generator.google.translator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.data.generator.constants.KeyBoardConstants;
import com.data.generator.file.Reader;
import com.data.generator.file.impl.CsvReader;
import com.data.generator.util.Precondition;
import com.gtranslate.Translator;

public class GoogleTranslator implements LanguageTranslator {

	private final Reader<String, Map<String[], List<List<String>>>> reader;
	private final Translator translator;

	public GoogleTranslator() {
		this.reader = new CsvReader();
		this.translator = Translator.getInstance();
	}

	@Override
	public Map<String[], List<List<String>>> translate(File argTokenFile,
			String argSourceLanguage, String argDestinationLanguage) {
		File tokenFile = Precondition
				.ensureNotNull(argTokenFile, "Tokens File");
		String sourceLanguage = Precondition.ensureNotEmpty(argSourceLanguage,
				"Source Language");
		String destinationLanguage = Precondition.ensureNotEmpty(
				argDestinationLanguage, "Destination Language");
		Map<String[], List<List<String>>> headerArrayVsTokensListMap = this
				.getReader().read(tokenFile);
		return process(headerArrayVsTokensListMap, sourceLanguage,
				destinationLanguage);
	}

	@SuppressWarnings("unchecked")
	private Map<String[], List<List<String>>> process(
			Map<String[], List<List<String>>> argHeaderArrayVsTokensListMap,
			String argSourceLanguage, String argDestinationLanguage) {
		Map<String[], List<List<String>>> headerArrayVsTokensListMap = (Map<String[], List<List<String>>>) Precondition
				.ensureNotEmpty(argHeaderArrayVsTokensListMap,
						"HeaderArrayVsTokensListMap");
		Iterator<Entry<String[], List<List<String>>>> iterator = headerArrayVsTokensListMap
				.entrySet().iterator();
		Map<String[], List<List<String>>> map = new HashMap<String[], List<List<String>>>();
		while (iterator.hasNext()) {
			Entry<String[], List<List<String>>> entry = iterator.next();
			List<List<String>> value = entry.getValue();
			List<List<String>> tokensList = process(value, argSourceLanguage,
					argDestinationLanguage);
			map.put(entry.getKey(), tokensList);
		}
		return map;
	}

	private List<List<String>> process(List<List<String>> argValue,
			String argSourceLanguage, String argDestinationLanguage) {
		List<List<String>> tokensList = (List<List<String>>) Precondition
				.ensureNotEmpty(argValue, "Tokens List");
		List<List<String>> tokens = new ArrayList<List<String>>();
		for (List<String> list : tokensList) {
			List<String> translatedTokensList = translate(list,
					argSourceLanguage, argDestinationLanguage);
			if (Precondition.checkNotEmpty(translatedTokensList)) {
				tokens.add(translatedTokensList);
			}
		}
		return tokens;
	}

	private List<String> translate(List<String> argTokensList,
			String argSourceLanguage, String argDestinationLanguage) {
		List<String> tokensList = (List<String>) Precondition.ensureNotEmpty(
				argTokensList, "Tokens List");
		List<String> translatedTokensList = new ArrayList<String>();
		for (int i = 0; i < tokensList.size(); i++) {
			String token = tokensList.get(i);
			if (Precondition.checkNotEmpty(token)) {
				String translatedToken = this.getTranslator().translate(token,
						argSourceLanguage, argDestinationLanguage);
				if (Precondition.checkEmpty(translatedToken)) {
					translatedToken = KeyBoardConstants.SPACE;
				}
				if (!token.equals(translatedToken)) {
					System.out.println("Plain Token: " + token
							+ "\t Translated Token: " + translatedToken);
					translatedTokensList.add(translatedToken);
				} else {
					i = tokensList.size();
				}
			}
		}
		return translatedTokensList;
	}

	@Override
	public Map<String[], List<List<String>>> translate(String argInputFileName,
			String argSourceLanguage, String argDestinationLanguage) {
		String inputFileName = Precondition.ensureNotEmpty(argInputFileName,
				"Input File Name");
		File inputFile = new File(inputFileName);
		return translate(inputFile, argSourceLanguage, argDestinationLanguage);
	}

	public Reader<String, Map<String[], List<List<String>>>> getReader() {
		return this.reader;
	}

	public Translator getTranslator() {
		return this.translator;
	}

}
