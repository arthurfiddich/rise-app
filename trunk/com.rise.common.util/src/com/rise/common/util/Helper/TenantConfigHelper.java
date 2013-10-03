package com.rise.common.util.Helper;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MultiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rise.common.util.annotation.FieldType.Type;
import com.rise.common.util.annotation.Reference;
import com.rise.common.util.file.UiComponentFileReader;
import com.rise.common.util.hibernate.ResourceUtil;
import com.rise.xmlns.common.v1.Field;
import com.rise.xmlns.common.v1.QueryConfig;

public class TenantConfigHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(TenantConfigHelper.class);
	private static final String QUERY_CONFIG_FILE_LOCATION = "com.rise.query.config.xml";
	private static final String MODEL_CLASS_PROPERTY_FILE_LOCATION = "classNames.txt";
	private static final String UI_COMPONENT_PROPERTY_FILE_LOCATION = "uiComponent.txt";
	private static TenantConfigHelper instance = createInstance();
	private QueryConfigHelper queryConfigHelper;
	private QueryBuilderHelper queryBuilderHelper;
	private UiComponentFileReader uiComponentFileReader;

	private String tenantId;

	public static TenantConfigHelper getInstance() {
		return instance;
	}

	public static TenantConfigHelper createInstance() {
		logger.debug("Initialize method........");
		TenantConfigHelper tenantConfigHelper = new TenantConfigHelper();
		tenantConfigHelper.initialize();
		return tenantConfigHelper;
	}

	private void initialize() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing TenantConfig:");
		}
		URL url = ResourceUtil.getResource(QUERY_CONFIG_FILE_LOCATION);
		System.out.println(url.toString());
		System.out.println(url.toExternalForm());
		System.out.println(url.getPath());
		// this.setQueryConfigHelper(QueryConfigHelper.createInstance(
		// this.getTenantId(), QUERY_CONFIG_FILE_LOCATION));

		this.setQueryBuilderHelper(QueryBuilderHelper.createInstance(
				this.getTenantId(), MODEL_CLASS_PROPERTY_FILE_LOCATION));

		this.setUiComponentFileReader(UiComponentFileReader.createInstance(
				this.getTenantId(), UI_COMPONENT_PROPERTY_FILE_LOCATION));
	}

	public List<Field> getFields(String argClassName) {
		return this.queryConfigHelper.getFields(argClassName);
	}

	public QueryConfig getQueryConfig(String argFullyQualifiedClassName) {
		return this.queryConfigHelper
				.getQueryConfig(argFullyQualifiedClassName);
	}

	public boolean isComponent(String argClassName, String argComponentName) {
		return this.queryConfigHelper.isComponent(argClassName,
				argComponentName);
	}

	public boolean isSuperClass(String argClassName, String argSuperClass) {
		return this.queryConfigHelper.isSuperClass(argClassName, argSuperClass);
	}

	public Map<String, QueryConfig> getClassNameVsQueryConfigMap() {
		return this.queryConfigHelper.getClassNameVsQueryConfigMap();
	}

	public MultiMap getClassNameVsComponentsMap() {
		return this.queryConfigHelper.getClassNameVsComponentsMap();
	}

	public MultiMap getClassNameVsSuperClassesMap() {
		return this.queryConfigHelper.getClassNameVsSuperClassesMap();
	}

	public String getTenantId() {
		return this.tenantId;
	}

	public void setTenantId(String argTenantId) {
		this.tenantId = argTenantId;
	}

	public QueryConfigHelper getQueryConfigHelper() {
		return this.queryConfigHelper;
	}

	public void setQueryConfigHelper(QueryConfigHelper argQueryConfigHelper) {
		this.queryConfigHelper = argQueryConfigHelper;
	}

	public QueryBuilderHelper getQueryBuilderHelper() {
		return this.queryBuilderHelper;
	}

	public void setQueryBuilderHelper(QueryBuilderHelper argQueryBuilderHelper) {
		this.queryBuilderHelper = argQueryBuilderHelper;
	}

	public String buildQuery(Class argClass, List<Class> argComponentClassList,
			List<Class> argSuperClassList) {
		return this.queryBuilderHelper.buildQuery(argClass,
				argComponentClassList, argSuperClassList);
	}

	/**
	 * @return
	 * @see com.rise.common.util.Helper.QueryBuilderHelper#getModelClassesList()
	 */
	public List<Class<?>> getModelClassesList() {
		return this.queryBuilderHelper.getModelClassesList();
	}

	/**
	 * @return
	 * @see com.rise.common.util.Helper.QueryBuilderHelper#getEntityNameVsFieldPojoListMap()
	 */
	public Map<String, List<com.rise.common.util.controller.components.Field>> getEntityNameVsFieldPojoListMap() {
		return this.queryBuilderHelper.getEntityNameVsFieldPojoListMap();
	}

	public Map<String, String> getModelNameVsQueryPartMap() {
		return this.queryBuilderHelper.getModelNameVsQueryPartMap();
	}

	public Map<String, String> getComponentModelClassMap() {
		return this.queryBuilderHelper.getComponentModelClassMap();
	}

	public Map<String, List<java.lang.reflect.Field>> getModelNameVsFieldsMap() {
		return this.queryBuilderHelper.getModelNameVsFieldsMap();
	}

	public Map<String, Class<?>> getModelNameVsClassObjectMap() {
		return this.queryBuilderHelper.getModelNameVsClassObjectMap();
	}

	public List<String> getFieldsBasedOnClass(String argClassName) {
		return this.queryBuilderHelper.getFieldsBasedOnClass(argClassName);
	}

	/**
	 * @return
	 * @see com.rise.common.util.Helper.QueryBuilderHelper#getModelNameVsRefereceFieldsMap()
	 */
	public Map<String, List<java.lang.reflect.Field>> getModelNameVsRefereceFieldsMap() {
		return this.queryBuilderHelper.getModelNameVsRefereceFieldsMap();
	}

	/**
	 * @return
	 * @see com.rise.common.util.Helper.QueryBuilderHelper#getModelNameVsComponentFieldsMap()
	 */
	public Map<String, List<java.lang.reflect.Field>> getModelNameVsComponentFieldsMap() {
		return this.queryBuilderHelper.getModelNameVsComponentFieldsMap();
	}

	/**
	 * @return
	 * @see com.rise.common.util.Helper.QueryBuilderHelper#getModelNameVsRefereceFieldNamesMap()
	 */
	public Map<String, List<String>> getModelNameVsRefereceFieldNamesMap() {
		return this.queryBuilderHelper.getModelNameVsRefereceFieldNamesMap();
	}

	/**
	 * @param argModelClassName
	 * @return
	 * @see com.rise.common.util.Helper.QueryBuilderHelper#getReferenceFieldsListByModelName(java.lang.String)
	 */
	public List<java.lang.reflect.Field> getReferenceFieldsListByModelName(
			String argModelClassName) {
		return this.queryBuilderHelper
				.getReferenceFieldsListByModelName(argModelClassName);
	}

	/**
	 * @return
	 * @see com.rise.common.util.Helper.QueryBuilderHelper#getModelNameVsRefereceFieldNamePrefixVsColumnNamesListMap()
	 */
	public Map<String, Map<String, List<String>>> getModelNameVsRefereceFieldNamePrefixVsColumnNamesListMap() {
		return this.queryBuilderHelper
				.getModelNameVsRefereceFieldNamePrefixVsColumnNamesListMap();
	}

	/**
	 * @return
	 * @see com.rise.common.util.Helper.QueryBuilderHelper#getModelNameVsFieldNamesMap()
	 */
	public Map<String, List<String>> getModelNameVsFieldNamesMap() {
		return this.queryBuilderHelper.getModelNameVsFieldNamesMap();
	}

	/**
	 * @param argTableName
	 * @param argFieldName
	 * @return
	 * @see com.rise.common.util.Helper.QueryBuilderHelper#getTypeBasedOnFieldNameAndEntityName(java.lang.String,
	 *      java.lang.String)
	 */
	public Type getTypeBasedOnFieldNameAndEntityName(String argTableName,
			String argFieldName) {
		return this.queryBuilderHelper.getTypeBasedOnFieldNameAndEntityName(
				argTableName, argFieldName);
	}

	/**
	 * @return
	 * @see com.rise.common.util.Helper.QueryBuilderHelper#getModelNameVsReferecesMap()
	 */
	public Map<String, List<Reference>> getModelNameVsReferecesMap() {
		return this.queryBuilderHelper.getModelNameVsReferecesMap();
	}

	/**
	 * @return
	 * @see com.rise.common.util.Helper.QueryBuilderHelper#getClassNameVsTableNameMap()
	 */
	public Map<String, String> getClassNameVsTableNameMap() {
		return this.queryBuilderHelper.getClassNameVsTableNameMap();
	}

	public Map<String, Map<String, String>> getClassNameVsDbColumnNameMap() {
		return this.queryBuilderHelper.getClassNameVsDbColumnNameMap();
	}

	public Map<String, Object> getFullyQualifiedClassNameVsValidatorInstance() {
		return this.queryBuilderHelper
				.getFullyQualifiedClassNameVsValidatorInstance();
	}

	public UiComponentFileReader getUiComponentFileReader() {
		return this.uiComponentFileReader;
	}

	public void setUiComponentFileReader(
			UiComponentFileReader argUiComponentFileReader) {
		this.uiComponentFileReader = argUiComponentFileReader;
	}

	public Map<String, String> getModelNameVsUiComponentsMap() {
		return this.uiComponentFileReader.getModelNameVsUiComponentsMap();
	}

	public Map<String, List<String>> getModelNameVsUiComponentListMap() {
		return this.uiComponentFileReader.getModelNameVsUiComponentListMap();
	}

}
