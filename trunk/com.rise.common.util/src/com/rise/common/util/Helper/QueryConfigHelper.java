package com.rise.common.util.Helper;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.common.util.reader.FileSystemConfigReader;
import com.rise.xmlns.common.v1.Component;
import com.rise.xmlns.common.v1.Field;
import com.rise.xmlns.common.v1.QueryConfig;
import com.rise.xmlns.common.v1.QueryConfigs;
import com.rise.xmlns.common.v1.SuperClass;

public class QueryConfigHelper {

	private static final Logger logger = LoggerFactory
			.getLogger(QueryConfigHelper.class);
	public static final String PACKAGE_NAME = com.rise.xmlns.common.v1.ObjectFactory.class
			.getPackage().getName();
	private static final String XSD_FILE_PATH = "./xsd/com.rise.query.config.xsd";
	private String tenantId;
	private String configurationFile;
	private QueryConfigs queryConfigs;
	private Map<String, QueryConfig> classNameVsQueryConfigMap;
	private MultiMap classNameVsComponentsMap;
	private MultiMap classNameVsSuperClassesMap;

	public QueryConfigHelper() {
		super();
	}

	public static QueryConfigHelper createInstance(String argTenantId,
			String argConfigurationFile) {
		QueryConfigHelper queryConfigHelper = new QueryConfigHelper();
		queryConfigHelper.setConfigurationFile(argConfigurationFile);
		queryConfigHelper.setTenantId(argTenantId);
		queryConfigHelper.initialize();
		return queryConfigHelper;
	}

	private void initialize() {
		this.setClassNameVsQueryConfigMap(new HashMap<String, QueryConfig>());
		this.setClassNameVsComponentsMap(new MultiValueMap());
		this.setClassNameVsSuperClassesMap(new MultiValueMap());
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing :"
					+ QueryConfigHelper.class.getSimpleName());
		}
		try {
			FileSystemConfigReader fileSystemConfigReader = new FileSystemConfigReader();
			QueryConfigs queryConfigs = (QueryConfigs) fileSystemConfigReader
					.readXmlFile(this.getConfigurationFile(), XSD_FILE_PATH,
							PACKAGE_NAME, false);
			// QueryConfigs queryConfigs = GenericJaxbHelper
			// .<QueryConfigs> unmarshalWithXsdValidation(
			// this.getConfigurationFile(), XSD_FILE_PATH,
			// PACKAGE_NAME);
			this.setQueryConfigs(queryConfigs);
			this.populateClassNameVsQueryConfigMap(this.getQueryConfigs());
			this.populateClassNameVsComponentsMap(this.getQueryConfigs()
					.getQueryConfig());
			this.populateClassNameVsSuperClassesMap(this.getQueryConfigs()
					.getQueryConfig());
		} catch (Exception e) {
			throw new RuntimeException(
					"Error while unmarshalling the config file : "
							+ this.getConfigurationFile() + " with Package :"
							+ PACKAGE_NAME, e);
		}
	}

	private void populateClassNameVsComponentsMap(
			List<QueryConfig> argQueryConfig) {
		List<QueryConfig> queryConfigs = (List<QueryConfig>) Precondition
				.ensureNotEmpty(argQueryConfig, QueryConfig.class.getName());
		for (QueryConfig queryConfig : queryConfigs) {
			String className = queryConfig.getClassName();
			className = Precondition.ensureNotEmpty(className,
					HibernateHelperConstants.CLASS_NAME);
			List<String> components = queryConfig.getComponent().getName();
			components = (List<String>) Precondition.ensureNotEmpty(components,
					Component.class.getName());
			for (String component : components) {
				component = Precondition.ensureNotNull(component,
						Component.class.getName());
				this.getClassNameVsComponentsMap().put(className, component);
			}
		}
	}

	private void populateClassNameVsSuperClassesMap(
			List<QueryConfig> argQueryConfig) {
		List<QueryConfig> queryConfigs = (List<QueryConfig>) Precondition
				.ensureNotEmpty(argQueryConfig, QueryConfig.class.getName());
		for (QueryConfig queryConfig : queryConfigs) {
			String className = queryConfig.getClassName();
			className = Precondition.ensureNotEmpty(className,
					HibernateHelperConstants.CLASS_NAME);
			List<String> superClassList = queryConfig.getSuperClass().getName();
			superClassList = (List<String>) Precondition.ensureNotEmpty(
					superClassList, SuperClass.class.getName());
			for (String superClass : superClassList) {
				superClass = Precondition.ensureNotNull(superClass,
						SuperClass.class.getName());
				this.getClassNameVsSuperClassesMap().put(className, superClass);
			}
		}
	}

	public void populateClassNameVsQueryConfigMap(QueryConfigs argQueryConfigs) {
		QueryConfigs queryConfigs = argQueryConfigs;
		queryConfigs = Precondition.ensureNotNull(queryConfigs,
				QueryConfigs.class.getName());
		List<QueryConfig> queryConfigList = queryConfigs.getQueryConfig();
		queryConfigList = (List<QueryConfig>) Precondition.ensureNotEmpty(
				queryConfigList, QueryConfig.class.getName());
		for (QueryConfig queryConfig : queryConfigList) {
			String className = queryConfig.getClassName();
			this.getClassNameVsQueryConfigMap().put(className, queryConfig);
		}
	}

	public QueryConfig getQueryConfig(String argFullyQualifiedClassName) {
		String className = Precondition
				.ensureNotEmpty(argFullyQualifiedClassName,
						HibernateHelperConstants.CLASS_NAME);
		Precondition.ensureNotNull(this.getClassNameVsQueryConfigMap(),
				HibernateHelperConstants.CLASS_NAME_VS_QUERY_CONFIG_MAP);
		return this.getClassNameVsQueryConfigMap().get(className);
	}

	@SuppressWarnings("unchecked")
	public boolean isComponent(String argClassName, String argComponentName) {
		String className = Precondition.ensureNotEmpty(argClassName,
				HibernateHelperConstants.CLASS_NAME);
		String componentName = Precondition.ensureNotEmpty(argComponentName,
				HibernateHelperConstants.COMPONENT_NAME);
		Collection<Component> componentList = (Collection<Component>) this
				.getClassNameVsComponentsMap().get(className);
		if (componentList == null) {
			return false;
		} else {
			componentList = Precondition.ensureNotEmpty(componentList,
					HibernateHelperConstants.COMPONENT_LIST);
			return componentList.contains(componentName);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean isSuperClass(String argClassName, String argSuperClass) {
		String className = Precondition.ensureNotEmpty(argClassName,
				HibernateHelperConstants.CLASS_NAME);
		String superClassName = Precondition.ensureNotEmpty(argSuperClass,
				HibernateHelperConstants.SUPER_CLASS_NAME);
		Collection<SuperClass> superClassList = (Collection<SuperClass>) this
				.getClassNameVsSuperClassesMap().get(className);
		superClassList = Precondition.ensureNotEmpty(superClassList,
				HibernateHelperConstants.SUPER_CLASS_LIST);
		return superClassList.contains(superClassName);
	}

	public List<Field> getFields(String argClassName) {
		String className = Precondition.ensureNotEmpty(argClassName,
				HibernateHelperConstants.CLASS_NAME);
		QueryConfig queryConfig = this.getClassNameVsQueryConfigMap().get(
				className);
		queryConfig = Precondition.ensureNotNull(queryConfig, "QueryConfig");
		return queryConfig.getField();
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

	public QueryConfigs getQueryConfigs() {
		return this.queryConfigs;
	}

	public void setQueryConfigs(QueryConfigs argQueryConfigs) {
		this.queryConfigs = argQueryConfigs;
	}

	public Map<String, QueryConfig> getClassNameVsQueryConfigMap() {
		return this.classNameVsQueryConfigMap;
	}

	public void setClassNameVsQueryConfigMap(
			Map<String, QueryConfig> argClassNameVsQueryConfigMap) {
		this.classNameVsQueryConfigMap = argClassNameVsQueryConfigMap;
	}

	public MultiMap getClassNameVsComponentsMap() {
		return this.classNameVsComponentsMap;
	}

	public void setClassNameVsComponentsMap(MultiMap argClassNameVsComponentsMap) {
		this.classNameVsComponentsMap = argClassNameVsComponentsMap;
	}

	public MultiMap getClassNameVsSuperClassesMap() {
		return this.classNameVsSuperClassesMap;
	}

	public void setClassNameVsSuperClassesMap(
			MultiMap argClassNameVsSuperClassesMap) {
		this.classNameVsSuperClassesMap = argClassNameVsSuperClassesMap;
	}

}
