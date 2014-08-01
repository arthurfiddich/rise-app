package com.data.generator.google.translator;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface LanguageTranslator {

	public Map<String[], List<List<String>>> translate(File argTokenFile, String argSourceLanguage,
			String argDestinationLanguage);

	public Map<String[], List<List<String>>> translate(String argInput, String argSourceLanguage,
			String argDestinationLanguage);
}
