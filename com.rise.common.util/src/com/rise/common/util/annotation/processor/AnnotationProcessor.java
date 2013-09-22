package com.rise.common.util.annotation.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.rise.common.util.Helper.TenantConfigHelper;
import com.rise.common.util.annotation.Validation;
import com.rise.common.util.checker.Precondition;

public class AnnotationProcessor {

	/*
	 * It will give a list of annotated fields for a given class instance
	 */
	public static List<Field> getAnnotatedFields(Class<?> argClassInstance,
			Class<? extends Annotation> argAnnotationType) {
		if (Precondition.checkNotNull(argClassInstance)
				&& Precondition.checkNotNull(argAnnotationType)) {
			Field[] fieldArray = argClassInstance.getDeclaredFields();
			if (Precondition.checkNotEmpty(fieldArray)) {
				List<Field> annotatedFieldsList = new ArrayList<Field>();
				for (Field field : fieldArray) {
					if (Precondition.checkNotNull(field
							.getAnnotation(argAnnotationType))) {
						annotatedFieldsList.add(field);
					}
				}
				return annotatedFieldsList;
			}
		}
		return null;
	}

	/*
	 * It will give a list of annotated fields for a given class name. Class
	 * Name means FULLY QUALIFIED CLASS NAME. Say for example
	 * com.rise.common.model.Person.
	 */
	public static List<Field> getAnnoatatedFields(
			String argFullyClassifiedClassName,
			Class<? extends Annotation> argAnnotationType) {
		if (Precondition.checkNotEmpty(argFullyClassifiedClassName)
				&& Precondition.checkNotNull(argAnnotationType)) {
			Map<String, Class<?>> modelNameVsClassInstnaceMap = TenantConfigHelper
					.getInstance().getModelNameVsClassObjectMap();
			if (Precondition.checkNotNull(modelNameVsClassInstnaceMap)) {
				Class<?> classInstance = modelNameVsClassInstnaceMap
						.get(argFullyClassifiedClassName);
				if (Precondition.checkNotNull(classInstance)) {
					return getAnnotatedFields(classInstance, argAnnotationType);
				}
			}
		}
		return null;
	}

	/*
	 * Its a map and it has class instance vs annotated fields list.
	 */

	public static Map<Class<?>, List<Field>> getClassInstanceVsAnnotatedFieldsList(
			List<Class<?>> argClassInstancesList,
			Class<? extends Annotation> argAnnotationType) {
		if (Precondition.checkNotEmpty(argClassInstancesList)
				&& Precondition.checkNotNull(argAnnotationType)) {
			Map<Class<?>, List<Field>> classInstanceVsAnnotatedFiledsList = Collections
					.emptyMap();
			for (Class<?> clazz : argClassInstancesList) {
				List<Field> annotatedFieldsList = getAnnotatedFields(clazz,
						argAnnotationType);
				if (Precondition.checkNotEmpty(annotatedFieldsList)) {
					classInstanceVsAnnotatedFiledsList.put(clazz,
							annotatedFieldsList);
				}
			}
			return classInstanceVsAnnotatedFiledsList;
		}
		return null;
	}

	/*
	 * Its a map and it has Class Name Vs Annotated Fields List.
	 * 
	 * Ex: Class Name -- Person --> Annotated Fields List.
	 */

	public static Map<String, List<Field>> getClassNameVsAnnotatedFieldsList(
			List<Class<?>> argClassInstancesList,
			Class<? extends Annotation> argAnnotationType) {
		if (Precondition.checkNotEmpty(argClassInstancesList)
				&& Precondition.checkNotNull(argAnnotationType)) {
			Map<Class<?>, List<Field>> classInstanceVsAnnotatedFiledsMap = getClassInstanceVsAnnotatedFieldsList(
					argClassInstancesList, argAnnotationType);
			if (Precondition.checkNotNull(classInstanceVsAnnotatedFiledsMap)) {
				Set<Entry<Class<?>, List<Field>>> entrySet = classInstanceVsAnnotatedFiledsMap
						.entrySet();
				if (!entrySet.isEmpty()) {
					Iterator<Entry<Class<?>, List<Field>>> iterator = entrySet
							.iterator();
					Map<String, List<Field>> classNameVsAnnotatedFieldsList = Collections
							.emptyMap();
					while (iterator.hasNext()) {
						Entry<Class<?>, List<Field>> entry = iterator.next();
						Class<?> key = entry.getKey();
						List<Field> value = entry.getValue();
						if (Precondition.checkNotNull(key)
								&& Precondition.checkNotEmpty(value)) {
							classNameVsAnnotatedFieldsList.put(
									key.getSimpleName(), value);
						}
					}
					return classNameVsAnnotatedFieldsList;
				}
			}
		}
		return null;
	}

	/*
	 * This method will accept fully qualified class names list and the
	 * annotation type. It will return a map and the class name (Simple Name) as
	 * a key and the list of annotated fields as a value.
	 */
	public static Map<String, List<Field>> getModelNameVsAnnotatedFieldsList(
			List<String> argFullyQualifiedClassNamesList,
			Class<? extends Annotation> argAnnotationType) {
		if (Precondition.checkNotEmpty(argFullyQualifiedClassNamesList)
				&& Precondition.checkNotNull(argAnnotationType)) {
			List<Class<?>> classInstancesList = new ArrayList<Class<?>>();
			for (String fulluQualifiedClassName : argFullyQualifiedClassNamesList) {
				Map<String, Class<?>> modelNameVsClassInstnaceMap = TenantConfigHelper
						.getInstance().getModelNameVsClassObjectMap();
				if (Precondition.checkNotNull(modelNameVsClassInstnaceMap)) {
					Class<?> classInstance = modelNameVsClassInstnaceMap
							.get(fulluQualifiedClassName);
					if (Precondition.checkNotNull(classInstance)) {
						classInstancesList.add(classInstance);
					}
				}
			}
			return getClassNameVsAnnotatedFieldsList(classInstancesList,
					argAnnotationType);
		}
		return null;
	}

	/*
	 * This method will tell weather this field has the specified annotation
	 * type or not. If the field has the specified annotation type then it will
	 * return that annotation. You just need to specify the entity/model name,
	 * field name and the "Annotation Type".
	 */
	public static Annotation checkAnnotation(String argFullyQualifiedClassName,
			String argFieldName, Class<? extends Annotation> argAnnotationType) {
		if (Precondition.checkNotEmpty(argFullyQualifiedClassName)
				&& Precondition.checkNotEmpty(argFieldName)
				&& Precondition.checkNotNull(argAnnotationType)) {
			List<Field> annotatedFieldsList = getAnnoatatedFields(
					argFullyQualifiedClassName, argAnnotationType);
			if (Precondition.checkNotEmpty(annotatedFieldsList)) {
				for (Field field : annotatedFieldsList) {
					if (field.getName().equals(argFieldName)) {
						return field.getAnnotation(argAnnotationType);
					}
				}
			}
		}
		return null;
	}

	/*
	 * This method will provide the validation type.
	 */
	public static String getValidationType(String argFullyQualifiedClassName,
			String argFieldName, Class<? extends Annotation> argAnnotationType) {
		if (Precondition.checkNotEmpty(argFullyQualifiedClassName)
				&& Precondition.checkNotEmpty(argFieldName)
				&& Precondition.checkNotNull(argAnnotationType)) {
			Annotation annotation = checkAnnotation(argFullyQualifiedClassName,
					argFieldName, argAnnotationType);
			if (Precondition.checkNotNull(annotation)) {
				Validation validation = (Validation) annotation;
				return validation.validationType().validationType;
			}
		}
		return null;
	}
	
	/*
	 * This method will provide the validation type.
	 */
	public static String getPackageName(String argFullyQualifiedClassName,
			String argFieldName, Class<? extends Annotation> argAnnotationType) {
		if (Precondition.checkNotEmpty(argFullyQualifiedClassName)
				&& Precondition.checkNotEmpty(argFieldName)
				&& Precondition.checkNotNull(argAnnotationType)) {
			Annotation annotation = checkAnnotation(argFullyQualifiedClassName,
					argFieldName, argAnnotationType);
			if (Precondition.checkNotNull(annotation)) {
				Validation validation = (Validation) annotation;
				return validation.annotationPackage();
			}
		}
		return null;
	}

}
