package com.rise.common.util.Helper;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MultiMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rise.common.util.hibernate.ResourceUtil;
import com.rise.xmlns.common.v1.Field;
import com.rise.xmlns.common.v1.QueryConfig;

public class TenantConfigHelper {

	private static Logger logger = LogManager
			.getLogger(TenantConfigHelper.class);
	private static final String QUERY_CONFIG_FILE_LOCATION = "com.rise.query.config.xml";
	private static TenantConfigHelper instance = createInstance();
	private QueryConfigHelper queryConfigHelper;
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
		this.setQueryConfigHelper(QueryConfigHelper.createInstance(
				this.getTenantId(), QUERY_CONFIG_FILE_LOCATION));
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

}
