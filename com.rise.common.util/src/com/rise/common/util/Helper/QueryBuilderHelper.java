package com.rise.common.util.Helper;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rise.common.util.annotation.DesiredField;
import com.rise.common.util.checker.Precondition;
import com.rise.common.util.checker.PreconditionException;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.common.util.reader.ConfigReader;
import com.rise.common.util.reader.FileSystemConfigReader;

public class QueryBuilderHelper {

	private String tenantId;
	private String configurationFile;
	private Map<String, String> modelNameVsQueryPartMap = new HashMap<String, String>();
	private Map<String, String> componentModelClassMap = new HashMap<String, String>();
	private Map<String, List<String>> modelNameVsFieldsMap = new HashMap<String, List<String>>();

	public static QueryBuilderHelper createInstance(String argTenantId,
			String argConfigurationFile) {
		QueryBuilderHelper queryBuilderHelper = new QueryBuilderHelper();
		queryBuilderHelper.setConfigurationFile(argConfigurationFile);
		queryBuilderHelper.setTenantId(argTenantId);
		queryBuilderHelper.initialize();
		return queryBuilderHelper;
	}

	private void initialize() {
		ConfigReader configReader = new FileSystemConfigReader();
		buildMaps(configReader.readTextFile(this.getConfigurationFile()));
	}

	private void buildMaps(List<String> argTokens) {
		List<String> tokens = (List<String>) Precondition.ensureNotEmpty(
				argTokens, "Tokens");
		for (String modelClassName : tokens) {
			try {
				Class clazz = Class.forName(modelClassName);
				build(clazz);
			} catch (ClassNotFoundException e) {
				throw new PreconditionException("Class not found exception: "
						+ modelClassName, e);
			}
		}

	}

	private void build(Class argClazz) {
		Field[] fields = argClazz.getDeclaredFields();
		List<String> fieldList = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (Precondition.checkNotNull(field)) {
				DesiredField desiredField = field
						.getAnnotation(DesiredField.class);
				if (desiredField != null) {
					fieldList.add(field.getName());
				}
			}
		}
		buildQuery(argClazz.getSimpleName(), fieldList);
	}

	private void buildQuery(String argSimpleName, List<String> argFieldList) {
		if (Precondition.checkNotEmpty(argSimpleName)
				&& Precondition.checkNotEmpty(argFieldList)) {
			StringBuilder normalQueryBuilder = new StringBuilder();
			StringBuilder aliasNameQueryBuilder = new StringBuilder();
			String paramName = Introspector.decapitalize(argSimpleName);
			for (int i = 0; i < argFieldList.size(); i++) {
				String fieldName = argFieldList.get(i);
				String aliasparamName = buildParamName(paramName, fieldName);
				if (Precondition.checkNotEmpty(fieldName)) {
					normalQueryBuilder.append(fieldName);
					appendComma(argFieldList.size(), normalQueryBuilder, i);
				}
				if (Precondition.checkNotEmpty(aliasparamName)) {
					aliasNameQueryBuilder.append(aliasparamName);
					appendComma(argFieldList.size(), aliasNameQueryBuilder, i);
				}
			}
			this.getModelNameVsQueryPartMap().put(paramName,
					normalQueryBuilder.toString());
			this.getComponentModelClassMap().put(paramName,
					aliasNameQueryBuilder.toString());
			this.getModelNameVsFieldsMap().put(paramName, argFieldList);
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
		// List<Class> componentClassList = (List<Class>) Precondition
		// .ensureNotEmpty(argComponentClassList, "Component List");
		// List<Class> superClassList = (List<Class>)
		// Precondition.ensureNotEmpty(
		// argSuperClassList, "Super Class List");
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

	private void append(int argSize, StringBuilder queryBuilder, int argCount) {
		appendComma(argSize, queryBuilder, argCount);
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

	public Map<String, List<String>> getModelNameVsFieldsMap() {
		return this.modelNameVsFieldsMap;
	}

	public void setModelNameVsFieldsMap(
			Map<String, List<String>> argModelNameVsFieldsMap) {
		this.modelNameVsFieldsMap = argModelNameVsFieldsMap;
	}

}
