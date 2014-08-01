package com.data.generator.google.translator;

import java.util.List;
import java.util.Map;

import com.data.generator.file.Writer;
import com.data.generator.file.impl.CsvWriter;
import com.gtranslate.Language;

public class GoogleTranslatorTest {

	public static void main(String[] args) {
		LanguageTranslator languageTranslator = new GoogleTranslator();
		Map<String[], List<List<String>>> map = languageTranslator.translate(
				"./output/translate/input.csv", Language.ENGLISH, Language.HEBREW);
		Writer<Map<String[], List<List<String>>>, String> writer = new CsvWriter(
				"./output/translate/db-1.csv");
		writer.write(map);

	}
}
