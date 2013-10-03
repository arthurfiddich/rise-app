package com.rise.common.util.Helper;

import java.beans.Introspector;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rise.common.util.annotation.Component;
import com.rise.common.util.annotation.DesiredField;
import com.rise.common.util.annotation.FieldType.Type;
import com.rise.common.util.annotation.Reference;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.checker.PreconditionException;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.common.util.file.ReflectionUtil;
import com.rise.common.util.reader.ConfigReader;
import com.rise.common.util.reader.FileSystemConfigReader;

public class QueryBuilderHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(QueryBuilderHelper.class);

	private String tenantId;
	private String configurationFile;
	private List<Class<?>> modelClassesList = new ArrayList<Class<?>>();
	private Map<String, String> modelNameVsQueryPartMap = new HashMap<String, String>();
	private Map<String, String> componentModelClassMap = new HashMap<String, String>();
	private Map<String, List<Field>> modelNameVsFieldsMap = new HashMap<String, List<Field>>();
	private Map<String, List<String>> modelNameVsFieldNamesMap = new HashMap<String, List<String>>();
	private Map<String, Class<?>> modelNameVsClassObjectMap = new HashMap<String, Class<?>>();
	private Map<String, List<com.rise.common.util.controller.components.Field>> entityNameVsFieldPojoListMap = new HashMap<String, List<com.rise.common.util.controller.components.Field>>();
	private Map<String, List<Field>> modelNameVsRefereceFieldsMap = new HashMap<String, List<Field>>();
	private Map<String, List<Field>> modelNameVsComponentFieldsMap = new HashMap<String, List<Field>>();
	private Map<String, List<String>> modelNameVsRefereceFieldNamesMap = new HashMap<String, List<String>>();
	private Map<String, Map<String, List<String>>> modelNameVsRefereceFieldNamePrefixVsColumnNamesListMap = new HashMap<String, Map<String, List<String>>>();
	private Map<String, List<Reference>> modelNameVsReferecesMap = new HashMap<String, List<Reference>>();
	private Map<String, String> classNameVsTableNameMap = new HashMap<String, String>();
	private Map<String, Map<String, String>> classNameVsDbColumnNameMap = new HashMap<String, Map<String, String>>();

	/*
	 * This map has "fully qualified class name vs validator inctsnces"
	 */
	private Map<String, Object> fullyQualifiedClassNameVsValidatorInstance = new HashMap<String, Object>();

	public static QueryBuilderHelper createInstance(String argTenantId,
			String argConfigurationFile) {
		logger.debug("Creating/Initializing an instance for this class: "
				+ QueryBuilderHelper.class.getSimpleName());
		QueryBuilderHelper queryBuilderHelper = new QueryBuilderHelper();
		queryBuilderHelper.setConfigurationFile(argConfigurationFile);
		queryBuilderHelper.setTenantId(argTenantId);
		queryBuilderHelper.initialize();
		return queryBuilderHelper;
	}

	private void initialize() {
		ConfigReader configReader = new FileSystemConfigReader();
		List<String> tokens = configReader.readTextFile(this
				.getConfigurationFile());
		buildMaps(tokens);
		buildEntityNameVsFieldsList();
		collectAllReferenceAndComponentFields();
		buildClassNameVsFieldNamesMap();
		prepareModelClassNameVsCollectionOfReferenceFieldsMap();
		buildClassNameVsTableNameMap();
		buildDbColumnsMap();
		Map<String, Object> fullyQualifiedClassNamVsValidatorInstanceMap = buildClassNameVsValidatorInstances();
		if (Precondition
				.checkNotNull(fullyQualifiedClassNamVsValidatorInstanceMap)) {
			this.getFullyQualifiedClassNameVsValidatorInstance().putAll(
					fullyQualifiedClassNamVsValidatorInstanceMap);
		}
	}

	/*
	 * This class will hold the
	 * "Fully Qualified Class Name Vs Instance Of Validator".
	 */
	public Map<String, Object> buildClassNameVsValidatorInstances() {
		List<String> fullyQualifiedClassNames = ReflectionUtil
				.getClassNamesFromPackage(
						HibernateHelperConstants.VALIDATORS_PACKAGE_NAME, false);
		if (Precondition.checkNotEmpty(fullyQualifiedClassNames)) {
			Map<String, Object> fullyQualifiedClassNameVsObject = new HashMap<String, Object>();
			for (String fullyQualifiedClassName : fullyQualifiedClassNames) {
				try {
					Class<?> classInstance = Class
							.forName(fullyQualifiedClassName);
					Object newInstance = classInstance.newInstance();
					fullyQualifiedClassNameVsObject.put(
							fullyQualifiedClassName, newInstance);
				} catch (Exception e) {
					throw new PreconditionException(
							"Class not found exception/Exception while creating a new instance: "
									+ fullyQualifiedClassName, e);
				}
			}
			return fullyQualifiedClassNameVsObject;
		}
		return null;
	}

	private void buildDbColumnsMap() {
		Map<String, List<String>> modelNameVsFieldNamesMap = this
				.getModelNameVsFieldNamesMap();
		if (Precondition.checkNotNull(modelNameVsFieldNamesMap)) {
			Set<Entry<String, List<String>>> mapEntry = modelNameVsFieldNamesMap
					.entrySet();
			Iterator<Map.Entry<String, List<String>>> iterator = mapEntry
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, List<String>> entry = iterator.next();
				List<String> FieldList = entry.getValue();
				if (Precondition.checkNotEmpty(FieldList)) {
					Map<String, String> fieldNameVsDbColumnNameMap = new HashMap<String, String>();
					for (String fieldName : FieldList) {
						String dbColumnName = buildTableName(fieldName);
						if (Precondition.checkNotEmpty(dbColumnName)) {
							fieldNameVsDbColumnNameMap.put(fieldName,
									dbColumnName);
						}
					}
					this.classNameVsDbColumnNameMap.put(entry.getKey(),
							fieldNameVsDbColumnNameMap);
				}
			}
		}
	}

	private void buildClassNameVsTableNameMap() {
		List<Class<?>> modelClassesList = this.getModelClassesList();
		if (Precondition.checkNotEmpty(modelClassesList)) {
			for (Class<?> clazz : modelClassesList) {
				String className = clazz.getSimpleName();
				String tableName = buildTableName(className);
				this.getClassNameVsTableNameMap().put(clazz.getName(),
						tableName);
			}
		}
	}

	private String buildTableName(String argClassName) {
		String tableName = argClassName;
		if (Precondition.checkNotEmpty(tableName)) {
			StringBuilder tableNameBuilder = new StringBuilder();
			for (int i = 0; i < tableName.length(); i++) {
				char ch = tableName.charAt(i);
				if (Character.isUpperCase(ch)) {
					if (i > 0) {
						tableNameBuilder
								.append(HibernateHelperConstants.UNDER_SCORE);
					}
				}
				tableNameBuilder.append(ch);
			}
			tableName = tableNameBuilder.toString().toUpperCase();
		}
		return tableName;
	}

	private void buildClassNameVsFieldNamesMap() {
		Map<String, List<Field>> modelNameVsFieldsMap = this
				.getModelNameVsFieldsMap();
		if (Precondition.checkNotNull(modelNameVsFieldsMap)) {
			Iterator<Map.Entry<String, List<Field>>> iterator = modelNameVsFieldsMap
					.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, List<Field>> entry = iterator.next();
				List<String> fieldNamesList = new ArrayList<String>();
				for (Field field : entry.getValue()) {
					Annotation[] annotationsArray = field.getAnnotations();
					for (Annotation annotation : annotationsArray) {
						if (annotation instanceof DesiredField) {
							fieldNamesList.add(field.getName());
						}
					}
				}
				this.getModelNameVsFieldNamesMap().put(entry.getKey(),
						Collections.unmodifiableList(fieldNamesList));
			}
		}
	}

	private void prepareModelClassNameVsCollectionOfReferenceFieldsMap() {
		Map<String, List<Field>> modelClassNameVsReferenceFieldsMap = this
				.getModelNameVsRefereceFieldsMap();
		if (Precondition.checkNotNull(modelClassNameVsReferenceFieldsMap)) {
			Iterator<Map.Entry<String, List<Field>>> iterator = modelClassNameVsReferenceFieldsMap
					.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, List<Field>> entry = iterator.next();
				prepareFieldNamePrefixVsColumnNameMap(entry);
			}
		}
	}

	private void prepareFieldNamePrefixVsColumnNameMap(
			Entry<String, List<Field>> argEntry) {
		if (Precondition.checkNotNull(argEntry)) {
			Map<String, List<String>> refereceFieldNamePrefixVsColumnNameMap = new HashMap<String, List<String>>();
			for (Field field : argEntry.getValue()) {
				Annotation[] annotationsArray = field.getAnnotations();
				for (Annotation annotation : annotationsArray) {
					if (annotation instanceof Reference) {
						Reference reference = (Reference) annotation;
						List<String> value = this.getModelNameVsFieldNamesMap()
								.get(reference.name());
						value = getFieldNamesList(value, reference);
						refereceFieldNamePrefixVsColumnNameMap.put(
								reference.variableName(), value);
					}
				}
			}
			this.getModelNameVsRefereceFieldNamePrefixVsColumnNamesListMap()
					.put(argEntry.getKey(),
							refereceFieldNamePrefixVsColumnNameMap);
		}
	}

	private List<String> getFieldNamesList(List<String> argValue,
			Reference argReference) {
		if (Precondition.checkNotEmpty(argValue)
				&& Precondition.checkNotNull(argReference)) {
			List<String> columnNamesList = new ArrayList<String>();
			for (String fieldName : argValue) {
				StringBuilder fieldNameBuilder = new StringBuilder();
				fieldNameBuilder.append(argReference.prefix());
				fieldNameBuilder.append(HibernateHelperConstants.HYPHEN);
				fieldNameBuilder.append(fieldName);
				columnNamesList.add(fieldNameBuilder.toString());
			}
			return columnNamesList;
		}
		return argValue;
	}

	private void collectAllReferenceAndComponentFields() {
		List<Class<?>> modelClassList = this.getModelClassesList();
		if (Precondition.checkNotEmpty(modelClassList)) {
			for (Class<?> clazz : modelClassList) {
				getAllReferecedFields(clazz);
				getAllComponentsFields(clazz);
			}
		}
	}

	private void getAllComponentsFields(Class<?> argClazz) {
		Field[] fields = argClazz.getDeclaredFields();
		if (Precondition.checkNotNull(fields) && fields.length > 0) {
			List<Field> componentFieldsList = new ArrayList<Field>();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (Precondition.checkNotNull(field)) {
					Component reference = field.getAnnotation(Component.class);
					if (Precondition.checkNotNull(reference)) {
						componentFieldsList.add(field);
					}
				}
			}
			this.getModelNameVsComponentFieldsMap().put(
					argClazz.getSimpleName(), componentFieldsList);
		}
	}

	private void getAllReferecedFields(Class<?> argClass) {
		Field[] fields = argClass.getDeclaredFields();
		if (Precondition.checkNotNull(fields) && fields.length > 0) {
			List<Field> referencedFieldsList = new ArrayList<Field>();
			List<String> referencedFieldNamesList = new ArrayList<String>();
			List<Reference> referenceAnnotationsList = new ArrayList<Reference>();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (Precondition.checkNotNull(field)) {
					Reference reference = field.getAnnotation(Reference.class);
					if (Precondition.checkNotNull(reference)) {
						referencedFieldsList.add(field);
						referencedFieldNamesList.add(field.getName());
						referenceAnnotationsList.add(reference);
					}
				}
			}
			this.getModelNameVsRefereceFieldsMap().put(
					argClass.getSimpleName(), referencedFieldsList);
			this.getModelNameVsRefereceFieldNamesMap().put(
					argClass.getSimpleName(), referencedFieldNamesList);
			this.getModelNameVsReferecesMap().put(argClass.getSimpleName(),
					referenceAnnotationsList);
		}
	}

	private void buildEntityNameVsFieldsList() {
		if (Precondition.checkNotEmpty(this.getModelClassesList())) {
			Map<String, List<com.rise.common.util.controller.components.Field>> entityNameVsFieldPojoListMap = new HashMap<String, List<com.rise.common.util.controller.components.Field>>();
			for (Class<?> clazz : this.getModelClassesList()) {
				List<Field> fieldsList = getFields(clazz.getDeclaredFields());
				fieldsList.addAll(getFields(clazz));
				List<com.rise.common.util.controller.components.Field> fieldPojoList = buildControllerComponentFields(fieldsList);
				entityNameVsFieldPojoListMap.put(clazz.getSimpleName(),
						fieldPojoList);
			}
			this.setEntityNameVsFieldPojoListMap(entityNameVsFieldPojoListMap);
		}
	}

	private List<com.rise.common.util.controller.components.Field> buildControllerComponentFields(
			List<Field> argFieldsList) {
		if (Precondition.checkNotEmpty(argFieldsList)) {
			List<com.rise.common.util.controller.components.Field> fieldPojoList = new ArrayList<com.rise.common.util.controller.components.Field>();
			for (Field field : argFieldsList) {
				com.rise.common.util.controller.components.Field fieldPojo = new com.rise.common.util.controller.components.Field();
				fieldPojo.setFieldName(field.getName());
				fieldPojoList.add(fieldPojo);
			}
			return fieldPojoList;
		}
		return new ArrayList<com.rise.common.util.controller.components.Field>();
	}

	private List<Field> getFields(Class argClass) {
		Field[] fields = argClass.getDeclaredFields();
		List<Field> fieldList = new ArrayList<Field>();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (Precondition.checkNotNull(field)) {
				Component componentField = field.getAnnotation(Component.class);
				if (componentField != null) {
					fieldList.addAll(this.getModelNameVsFieldsMap().get(
							Introspector.decapitalize(field.getName())));
				}
			}
		}
		return fieldList;
	}

	private void buildMaps(List<String> argFullyQualifiedClassNames) {
		List<String> fullyQualifiedClassNames = (List<String>) Precondition
				.ensureNotEmpty(argFullyQualifiedClassNames, "Tokens");
		for (String fullyQualifiedClassName : fullyQualifiedClassNames) {
			try {
				Class clazz = Class.forName(fullyQualifiedClassName);
				this.getModelNameVsClassObjectMap().put(
						fullyQualifiedClassName, clazz);
				this.modelClassesList.add(clazz);
				build(clazz);
			} catch (ClassNotFoundException e) {
				throw new PreconditionException("Class not found exception: "
						+ fullyQualifiedClassName, e);
			}
		}

	}

	private void build(Class argClazz) {
		Field[] fields = argClazz.getDeclaredFields();
		String paramName = Introspector.decapitalize(argClazz.getSimpleName());
		List<Field> fieldList = getFields(fields);
		buildQuery(paramName, fieldList);
		this.getModelNameVsFieldsMap().put(paramName, fieldList);
	}

	private List<Field> getFields(Field[] fields) {
		List<Field> fieldList = new ArrayList<Field>();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (Precondition.checkNotNull(field)) {
				DesiredField desiredField = field
						.getAnnotation(DesiredField.class);
				if (desiredField != null) {
					fieldList.add(field);
				}
			}
		}
		return fieldList;
	}

	private void buildQuery(String argParamName, List<Field> argFieldList) {
		if (Precondition.checkNotEmpty(argParamName)
				&& Precondition.checkNotEmpty(argFieldList)) {
			StringBuilder normalQueryBuilder = new StringBuilder();
			StringBuilder aliasNameQueryBuilder = new StringBuilder();
			for (int i = 0; i < argFieldList.size(); i++) {
				String fieldName = argFieldList.get(i).getName();
				String aliasparamName = buildParamName(argParamName, fieldName);
				if (Precondition.checkNotEmpty(fieldName)) {
					normalQueryBuilder.append(fieldName);
					appendComma(argFieldList.size(), normalQueryBuilder, i);
				}
				if (Precondition.checkNotEmpty(aliasparamName)) {
					aliasNameQueryBuilder.append(aliasparamName);
					appendComma(argFieldList.size(), aliasNameQueryBuilder, i);
				}
			}
			this.getModelNameVsQueryPartMap().put(argParamName,
					normalQueryBuilder.toString());
			this.getComponentModelClassMap().put(argParamName,
					aliasNameQueryBuilder.toString());
		}
	}

	private void appendComma(int argSize, StringBuilder queryBuilder, int i) {
		if (i < argSize - 1) {
			queryBuilder.append(HibernateHelperConstants.COMMA);
		}
	}

	private String buildParamName(String argParamName, String argFieldName) {
		if (Precondition.checkNotEmpty(argParamName)
				&& Precondition.checkNotEmpty(argFieldName)) {
			StringBuilder paramBuilder = new StringBuilder();
			paramBuilder.append(argParamName);
			paramBuilder.append(HibernateHelperConstants.DOT);
			paramBuilder.append(argFieldName);
			return paramBuilder.toString();
		}
		return null;
	}

	public String buildQuery(Class argClass, List<Class> argComponentClassList,
			List<Class> argSuperClassList) {
		Class clazz = Precondition.ensureNotNull(argClass, "Class");
		List<String> queryParts = new ArrayList<String>();
		String parentQueryPart = execute(clazz);
		parentQueryPart = Precondition.ensureNotEmpty(parentQueryPart,
				"Parent Query Part");
		queryParts.add(parentQueryPart);
		if (Precondition.checkNotEmpty(argComponentClassList)) {
			queryParts.addAll(execute(argComponentClassList));
		}
		if (Precondition.checkNotEmpty(argSuperClassList)) {
			for (Class superClass : argSuperClassList) {
				queryParts.add(execute(superClass));
			}
		}
		return appendAllParts(queryParts);
	}

	private String appendAllParts(List<String> argQueryParts) {
		List<String> queryParts = (List<String>) Precondition.ensureNotEmpty(
				argQueryParts, "Query Parts");
		StringBuilder queryBuilder = new StringBuilder();
		for (int i = 0; i < queryParts.size(); i++) {
			queryBuilder.append(queryParts.get(i));
			append(queryParts.size(), queryBuilder, i);
		}
		return queryBuilder.toString();
	}

	private String execute(Class clazz) {
		return this.getModelNameVsQueryPartMap().get(
				Introspector.decapitalize(clazz.getSimpleName()));
	}

	private List<String> execute(List<Class> argClassList) {
		List<String> queryPartsList = new ArrayList<String>();
		for (int i = 0; i < argClassList.size(); i++) {
			Class componentClass = argClassList.get(i);
			String queryPart = this.getComponentModelClassMap().get(
					Introspector.decapitalize(componentClass.getSimpleName()));
			if (Precondition.checkNotEmpty(queryPart)) {
				queryPartsList.add(queryPart);
			}
		}
		return queryPartsList;
	}

	public List<String> getFieldsBasedOnClass(String argClassName) {
		String className = Precondition.ensureNotEmpty(argClassName,
				"Class Name");
		List<String> fieldNamesList = new ArrayList<String>();
		List<Field> list = this.getModelNameVsFieldsMap().get(className);
		for (Field field : list) {
			fieldNamesList.add(field.getName());
		}
		return fieldNamesList;
	}

	public List<Field> getReferenceFieldsListByModelName(
			String argModelClassName) {
		String modelCassName = Precondition.ensureNotEmpty(argModelClassName,
				HibernateHelperConstants.CLASS_NAME);
		return this.getModelNameVsRefereceFieldsMap().get(modelCassName);
	}

	private void append(int argSize, StringBuilder queryBuilder, int argCount) {
		appendComma(argSize, queryBuilder, argCount);
	}

	public Type getTypeBasedOnFieldNameAndEntityName(String argTableName,
			String argFieldName) {
		if (Precondition.checkNotEmpty(argFieldName)
				&& Precondition.checkNotEmpty(argTableName)) {
			List<Field> fieldMap = this.getModelNameVsFieldsMap().get(
					argTableName);
			if (Precondition.checkNotEmpty(fieldMap)) {
				for (Field field : fieldMap) {
					Annotation[] annotationsArray = field.getAnnotations();
					if (annotationsArray.length > 0) {
						for (Annotation annotation : annotationsArray) {
							if (annotation instanceof Reference) {
								Reference reference = (Reference) annotation;
								if (reference.variableName().equals(
										argFieldName)) {
									return reference.type();
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String argTenantId) {
		this.tenantId = argTenantId;
	}

	public String getConfigurationFile() {
		return this.configurationFile;
	}

	public void setConfigurationFile(String argConfigurationFile) {
		this.configurationFile = argConfigurationFile;
	}

	/**
	 * @return the modelClassesList
	 */
	public List<Class<?>> getModelClassesList() {
		return this.modelClassesList;
	}

	/**
	 * @param argModelClassesList
	 *            the modelClassesList to set
	 */
	public void setModelClassesList(List<Class<?>> argModelClassesList) {
		this.modelClassesList = argModelClassesList;
	}

	public Map<String, String> getModelNameVsQueryPartMap() {
		return this.modelNameVsQueryPartMap;
	}

	public void setModelNameVsQueryPartMap(
			Map<String, String> argModelNameVsQueryPartMap) {
		this.modelNameVsQueryPartMap = argModelNameVsQueryPartMap;
	}

	public Map<String, String> getComponentModelClassMap() {
		return this.componentModelClassMap;
	}

	public void setComponentModelClassMap(
			Map<String, String> argComponentModelClassMap) {
		this.componentModelClassMap = argComponentModelClassMap;
	}

	public Map<String, List<Field>> getModelNameVsFieldsMap() {
		return this.modelNameVsFieldsMap;
	}

	public void setModelNameVsFieldsMap(
			Map<String, List<Field>> argModelNameVsFieldsMap) {
		this.modelNameVsFieldsMap = argModelNameVsFieldsMap;
	}

	public Map<String, Class<?>> getModelNameVsClassObjectMap() {
		return this.modelNameVsClassObjectMap;
	}

	public void setModelNameVsClassObjectMap(
			Map<String, Class<?>> argModelNameVsClassObjectMap) {
		this.modelNameVsClassObjectMap = argModelNameVsClassObjectMap;
	}

	/**
	 * @return the entityNameVsFieldPojoListMap
	 */
	public Map<String, List<com.rise.common.util.controller.components.Field>> getEntityNameVsFieldPojoListMap() {
		return this.entityNameVsFieldPojoListMap;
	}

	/**
	 * @param argEntityNameVsFieldPojoListMap
	 *            the entityNameVsFieldPojoListMap to set
	 */
	public void setEntityNameVsFieldPojoListMap(
			Map<String, List<com.rise.common.util.controller.components.Field>> argEntityNameVsFieldPojoListMap) {
		this.entityNameVsFieldPojoListMap = argEntityNameVsFieldPojoListMap;
	}

	/**
	 * @return the modelNameVsRefereceFieldsMap
	 */
	public Map<String, List<Field>> getModelNameVsRefereceFieldsMap() {
		return this.modelNameVsRefereceFieldsMap;
	}

	/**
	 * @param argModelNameVsRefereceFieldsMap
	 *            the modelNameVsRefereceFieldsMap to set
	 */
	public void setModelNameVsRefereceFieldsMap(
			Map<String, List<Field>> argModelNameVsRefereceFieldsMap) {
		this.modelNameVsRefereceFieldsMap = argModelNameVsRefereceFieldsMap;
	}

	/**
	 * @return the modelNameVsComponentFieldsMap
	 */
	public Map<String, List<Field>> getModelNameVsComponentFieldsMap() {
		return this.modelNameVsComponentFieldsMap;
	}

	/**
	 * @param argModelNameVsComponentFieldsMap
	 *            the modelNameVsComponentFieldsMap to set
	 */
	public void setModelNameVsComponentFieldsMap(
			Map<String, List<Field>> argModelNameVsComponentFieldsMap) {
		this.modelNameVsComponentFieldsMap = argModelNameVsComponentFieldsMap;
	}

	/**
	 * @return the modelNameVsRefereceFieldNamesMap
	 */
	public Map<String, List<String>> getModelNameVsRefereceFieldNamesMap() {
		return this.modelNameVsRefereceFieldNamesMap;
	}

	/**
	 * @param argModelNameVsRefereceFieldNamesMap
	 *            the modelNameVsRefereceFieldNamesMap to set
	 */
	public void setModelNameVsRefereceFieldNamesMap(
			Map<String, List<String>> argModelNameVsRefereceFieldNamesMap) {
		this.modelNameVsRefereceFieldNamesMap = argModelNameVsRefereceFieldNamesMap;
	}

	/**
	 * @return the modelNameVsRefereceFieldNamePrefixVsColumnNamesListMap
	 */
	public Map<String, Map<String, List<String>>> getModelNameVsRefereceFieldNamePrefixVsColumnNamesListMap() {
		return this.modelNameVsRefereceFieldNamePrefixVsColumnNamesListMap;
	}

	/**
	 * @param argModelNameVsRefereceFieldNamePrefixVsColumnNamesListMap
	 *            the modelNameVsRefereceFieldNamePrefixVsColumnNamesListMap to
	 *            set
	 */
	public void setModelNameVsRefereceFieldNamePrefixVsColumnNamesListMap(
			Map<String, Map<String, List<String>>> argModelNameVsRefereceFieldNamePrefixVsColumnNamesListMap) {
		this.modelNameVsRefereceFieldNamePrefixVsColumnNamesListMap = argModelNameVsRefereceFieldNamePrefixVsColumnNamesListMap;
	}

	/**
	 * @return the modelNameVsFieldNamesMap
	 */
	public Map<String, List<String>> getModelNameVsFieldNamesMap() {
		return this.modelNameVsFieldNamesMap;
	}

	/**
	 * @param argModelNameVsFieldNamesMap
	 *            the modelNameVsFieldNamesMap to set
	 */
	public void setModelNameVsFieldNamesMap(
			Map<String, List<String>> argModelNameVsFieldNamesMap) {
		this.modelNameVsFieldNamesMap = argModelNameVsFieldNamesMap;
	}

	/**
	 * @return the modelNameVsReferecesMap
	 */
	public Map<String, List<Reference>> getModelNameVsReferecesMap() {
		return this.modelNameVsReferecesMap;
	}

	/**
	 * @param argModelNameVsReferecesMap
	 *            the modelNameVsReferecesMap to set
	 */
	public void setModelNameVsReferecesMap(
			Map<String, List<Reference>> argModelNameVsReferecesMap) {
		this.modelNameVsReferecesMap = argModelNameVsReferecesMap;
	}

	/**
	 * @return the classNameVsTableNameMap
	 */
	public Map<String, String> getClassNameVsTableNameMap() {
		return this.classNameVsTableNameMap;
	}

	/**
	 * @param argClassNameVsTableNameMap
	 *            the classNameVsTableNameMap to set
	 */
	public void setClassNameVsTableNameMap(
			Map<String, String> argClassNameVsTableNameMap) {
		this.classNameVsTableNameMap = argClassNameVsTableNameMap;
	}

	public Map<String, Map<String, String>> getClassNameVsDbColumnNameMap() {
		return this.classNameVsDbColumnNameMap;
	}

	public void setClassNameVsDbColumnNameMap(
			Map<String, Map<String, String>> argClassNameVsDbColumnNameMap) {
		this.classNameVsDbColumnNameMap = argClassNameVsDbColumnNameMap;
	}

	public Map<String, Object> getFullyQualifiedClassNameVsValidatorInstance() {
		return this.fullyQualifiedClassNameVsValidatorInstance;
	}

	public void setFullyQualifiedClassNameVsValidatorInstance(
			Map<String, Object> argFullyQualifiedClassNameVsValidatorInstance) {
		this.fullyQualifiedClassNameVsValidatorInstance = argFullyQualifiedClassNameVsValidatorInstance;
	}

}
