package com.rise.webapp.initializor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.annotation.InHouse;
import com.rise.common.util.annotation.processor.AnnotationProcessor;
import com.rise.common.util.checker.Precondition;
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

	public static void prepareUiComponentsFile(String argPackageName,
			String argOutputFileName) {
		List<String> simpleClassNamesList = ReflectionUtil
				.getSimpleClassNames(argPackageName);
		TenantConfigHelper tenantConfigHelper = TenantConfigHelper
				.getInstance();
		if (Precondition.checkNotNull(tenantConfigHelper)) {
			for (String className : simpleClassNamesList) {
				List<Field> fieldsList = AnnotationProcessor
						.getAnnoatatedFields(className, InHouse.class);
			}
		}
	}
}
