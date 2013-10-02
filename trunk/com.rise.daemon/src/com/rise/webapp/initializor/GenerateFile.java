package com.rise.webapp.initializor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.annotation.InHouse;
import com.rise.common.util.annotation.processor.AnnotationProcessor;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.common.util.file.ReflectionUtil;
import com.rise.common.util.writer.FileSystemConfigWriter;

public class GenerateFile {

	private static final Logger logger = LoggerFactory
			.getLogger(GenerateFile.class);

	public static void prepareClassesListFile(String argPackageName,
			String argOutputFileName) {
		if (Precondition.checkNotEmpty(argPackageName)
				&& Precondition.checkNotEmpty(argOutputFileName)) {
			logger.debug("Prepare a list of classes from a given package: "
					+ argPackageName + " into an output file: "
					+ argOutputFileName);
			List<String> simpleClassNamesList = ReflectionUtil
					.getClassNames(argPackageName);
			FileSystemConfigWriter fileWriter = new FileSystemConfigWriter();
			fileWriter.writeTextFile(argOutputFileName, simpleClassNamesList);
		}
	}

	public static void prepareUiComponentsFile(String argPackageName,
			String argOutputFileName) {
		if (Precondition.checkNotEmpty(argPackageName)
				&& Precondition.checkNotEmpty(argOutputFileName)) {
			List<String> fullyQualifiedClassNameList = ReflectionUtil
					.getClassNames(argPackageName);
			TenantConfigHelper tenantConfigHelper = TenantConfigHelper
					.getInstance();
			if (Precondition.checkNotNull(tenantConfigHelper)) {
				prepareFile(fullyQualifiedClassNameList, argPackageName,
						argOutputFileName);
			}
		}
	}

	protected static void prepareFile(List<String> fullyQualifiedClassNameList,
			String argPackageName, String argOutputFileName) {
		logger.debug("Prepare a map of className Vs list of InHouse annotation classes from a given package: "
				+ argPackageName + " into an output file: " + argOutputFileName);
		Map<String, List<String>> fullyQualifiedClassNameVsInHouseFieldsMap = new HashMap<String, List<String>>();
		for (String fullyQualifiedClassName : fullyQualifiedClassNameList) {
			List<String> fullyQualifiedFieldNames = new ArrayList<String>();
			StringBuilder stringBuilder = new StringBuilder();
			prepareValue(fullyQualifiedClassName, fullyQualifiedClassName,
					argPackageName, fullyQualifiedFieldNames, stringBuilder);
			fullyQualifiedClassNameVsInHouseFieldsMap.put(
					fullyQualifiedClassName, fullyQualifiedFieldNames);
		}
		FileSystemConfigWriter fileWriter = new FileSystemConfigWriter();
		fileWriter.writePropertyFile(argOutputFileName,
				fullyQualifiedClassNameVsInHouseFieldsMap, false);

	}

	protected static void prepareValue(String argFullyQualifiedClassName,
			String argParentClass, String argPackageName,
			List<String> argFullyQualifiedFieldNames,
			StringBuilder argStringBuilder) {
		String fullyQualifiedClassName = argFullyQualifiedClassName;
		List<Field> fieldsList = AnnotationProcessor.getAnnoatatedFields(
				fullyQualifiedClassName, InHouse.class);
		String simpleName = getSimpleName(argFullyQualifiedClassName);
		argStringBuilder.append(simpleName);
		argStringBuilder.append(HibernateHelperConstants.DOT);
		if (Precondition.checkNotEmpty(fieldsList)) {
			for (Field field : fieldsList) {
				String fieldName = field.getName();
				InHouse inHouseAnnotation = (InHouse) AnnotationProcessor
						.checkAnnotation(fullyQualifiedClassName, fieldName,
								InHouse.class);
				if (Precondition.checkNotNull(inHouseAnnotation)) {
					String[] parentClassNamesArray = inHouseAnnotation
							.parentClassNames();
					if (Precondition.checkNotEmpty(parentClassNamesArray)) {
						for (String parentClassName : parentClassNamesArray) {
							if (parentClassName.equals(argParentClass)) {
								StringBuilder append = getFullyQualifiedFieldName(
										argPackageName, fieldName);
								prepareValue(append.toString(), argParentClass,
										argPackageName,
										argFullyQualifiedFieldNames,
										argStringBuilder);
							}
						}
					}
				}
			}
		}
		buildTokenBuilder(argFullyQualifiedFieldNames, argStringBuilder,
				simpleName, argParentClass);
	}

	protected static void buildTokenBuilder(
			List<String> argFullyQualifiedFieldNames,
			StringBuilder argStringBuilder, String argSimpleName,
			String argParentClass) {
		String finalToken = argStringBuilder.toString();
		int index = finalToken.lastIndexOf(argSimpleName
				+ HibernateHelperConstants.DOT);
		if (index != -1) {
			finalToken = StringUtils.chop(finalToken);
			if (!finalToken.equals(getSimpleName(argParentClass))) {
				argFullyQualifiedFieldNames.add(finalToken);
			}
			finalToken = finalToken.substring(0, index);
			argStringBuilder.delete(0, argStringBuilder.toString().length());
			argStringBuilder.append(finalToken);
		}
	}

	private static String getSimpleName(String argFullyQualifiedClassName) {
		int index = argFullyQualifiedClassName
				.lastIndexOf(HibernateHelperConstants.DOT);
		if (index != -1) {
			return argFullyQualifiedClassName.substring(index + 1);
		}
		return argFullyQualifiedClassName;
	}

	protected static StringBuilder getFullyQualifiedFieldName(
			String argPackageName, String fieldName) {
		StringBuilder append = new StringBuilder().append(argPackageName)
				.append(HibernateHelperConstants.DOT)
				.append(WordUtils.capitalize(fieldName));
		return append;
	}
}
