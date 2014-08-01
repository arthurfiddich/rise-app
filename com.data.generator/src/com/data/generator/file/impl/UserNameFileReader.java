package com.data.generator.file.impl;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.data.generator.file.Reader;
import com.data.generator.util.Precondition;

public class UserNameFileReader implements Reader<String, Map<String, Boolean>> {

	private Reader<String, List<String>> textFileReader = new TextFileReader();

	@Override
	public Map<String, Boolean> read(String argFileName) {
		Precondition.ensureNotEmpty(argFileName, "Input FileName");
		List<String> tokensList = this.textFileReader.read(argFileName);
		if (Precondition.checkNotEmpty(tokensList)) {
			Map<String, Boolean> userNameVsUsedFlagMap = new LinkedHashMap<String, Boolean>();
			for (String userName : tokensList) {
				userNameVsUsedFlagMap.put(userName, new Boolean(false));
			}
			return userNameVsUsedFlagMap;
		}
		return null;
	}

	@Override
	public Map<String, Boolean> read(File argFile) {
		throw new RuntimeException("Not Implemented");
	}
}