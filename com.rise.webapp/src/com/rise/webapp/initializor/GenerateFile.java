package com.rise.webapp.initializor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;

import com.rise.common.util.writer.FileSystemConfigWriter;
import com.rise.common.util.writer.FileWriter;

public class GenerateFile {

	private static final Logger logger = LogManager
			.getLogger(GenerateFile.class);

	public static void prepareClassesListFile(String argPackageName,
			String argOutputFileName) {
		Reflections reflections = new Reflections(argPackageName);
		List<String> simpleClassNamesList = new ArrayList<String>();
		Set<Class<? extends Serializable>> allClasses = reflections
				.getSubTypesOf(Serializable.class);
		Iterator<Class<? extends Serializable>> s = allClasses.iterator();
		logger.info("Loading Class Names: ");
		while (s.hasNext()) {
			Class<? extends Serializable> st = s.next();
			String simpleClassName = st.getName();
			simpleClassNamesList.add(simpleClassName);
			logger.info("Class Names: " + simpleClassName);
		}
		FileWriter<String, String> fileWriter = new FileSystemConfigWriter();
		fileWriter.writeTextFile(argOutputFileName, simpleClassNamesList);
	}
}
