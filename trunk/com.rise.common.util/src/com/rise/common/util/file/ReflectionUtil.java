package com.rise.common.util.file;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rise.common.util.checker.Precondition;

public class ReflectionUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(ReflectionUtil.class);

	public static List<String> getClassNames(String argPackageName) {
		Reflections reflections = new Reflections(argPackageName);
		List<String> fullyQualifiedClassNamesList = new ArrayList<String>();
		Set<Class<? extends Serializable>> allClasses = reflections
				.getSubTypesOf(Serializable.class);
		Iterator<Class<? extends Serializable>> s = allClasses.iterator();
		logger.info("Loading Class Names: ");
		while (s.hasNext()) {
			Class<? extends Serializable> st = s.next();
			String fullyQualifiedClassName = st.getName();
			fullyQualifiedClassNamesList.add(fullyQualifiedClassName);
			logger.info("Class Names: " + fullyQualifiedClassName);
		}
		return fullyQualifiedClassNamesList;
	}
	
	public static List<String> getSimpleClassNames(String argPackageName) {
		Reflections reflections = new Reflections(argPackageName);
		List<String> simpleClassNamesList = new ArrayList<String>();
		Set<Class<? extends Serializable>> allClasses = reflections
				.getSubTypesOf(Serializable.class);
		Iterator<Class<? extends Serializable>> s = allClasses.iterator();
		logger.info("Loading Class Names: ");
		while (s.hasNext()) {
			Class<? extends Serializable> st = s.next();
			String simpleClassName = st.getSimpleName();
			simpleClassNamesList.add(simpleClassName);
			logger.info("Class Names: " + simpleClassName);
		}
		return simpleClassNamesList;
	}

	/*
	 * This method will return the classes list from the specified package and
	 * as well as the classes from the extrenal libs if we enable this property
	 * "argIterateInnerClasses" or else it will just return the classes from the
	 * specified package name.
	 * 
	 * Reference: https://code.google.com/p/reflections/issues/detail?id=122
	 */

	public static List<String> getClassNamesFromPackage(String argPackageName,
			boolean argIterateInnerClasses) {
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());
		ConfigurationBuilder configurationBuilder = null;
		if (argIterateInnerClasses) {
			configurationBuilder = new ConfigurationBuilder().setScanners(
					new SubTypesScanner(false), new ResourcesScanner())
					.setUrls(
							ClasspathHelper.forClassLoader(classLoadersList
									.toArray(new ClassLoader[0])));
		} else {
			configurationBuilder = new ConfigurationBuilder().setScanners(
					new SubTypesScanner(false), new ResourcesScanner())
					.setUrls(
							ClasspathHelper.forPackage(argPackageName,
									new ClassLoader[0]));
		}
		Reflections reflections = new Reflections(configurationBuilder);
		Set<Class<? extends Object>> classes = reflections
				.getSubTypesOf(Object.class);
		if (Precondition.checkNotEmpty(classes)) {
			List<String> simpleClassNamesList = new ArrayList<String>();
			Iterator<Class<? extends Object>> s = classes.iterator();
			logger.info("Loading Class Names: ");
			while (s.hasNext()) {
				Class<? extends Object> st = s.next();
				if (!st.isInterface()) {
					String simpleClassName = st.getName();
					simpleClassNamesList.add(simpleClassName);
					logger.info("Class Names: " + simpleClassName);
				}
			}
			return simpleClassNamesList;
		}
		return null;
	}
}
