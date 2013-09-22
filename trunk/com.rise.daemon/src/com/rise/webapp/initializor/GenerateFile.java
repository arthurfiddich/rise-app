package com.rise.webapp.initializor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rise.common.util.file.ReflectionUtil;
import com.rise.common.util.writer.FileSystemConfigWriter;
import com.rise.common.util.writer.FileWriter;

public class GenerateFile {

	private static final Logger logger = LoggerFactory
			.getLogger(GenerateFile.class);

	public static void prepareClassesListFile(String argPackageName,
			String argOutputFileName) {
		List<String> simpleClassNamesList = ReflectionUtil
				.getClassNames(argPackageName);
		FileWriter<String, String> fileWriter = new FileSystemConfigWriter();
		fileWriter.writeTextFile(argOutputFileName, simpleClassNamesList);
	}
}
