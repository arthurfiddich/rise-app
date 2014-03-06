package com.data.generator.service;

import java.util.List;
import java.util.Map;

import com.generator.data.xmlns.configuration.v1.AdditionalDataSource;
import com.generator.data.xmlns.configuration.v1.DataSource;
import com.generator.data.xmlns.configuration.v1.DataSourceConfiguration;
import com.generator.data.xmlns.configuration.v1.DataSourceFieldName;
import com.generator.data.xmlns.configuration.v1.Entity;
import com.generator.data.xmlns.configuration.v1.Field;

public interface ConfigurationService {

	DataSourceConfiguration getDataSourceConfiguration();

	DataSource getDataSource();

	List<AdditionalDataSource> getAdditionalDataSourceList();

	String getDataSourceName();

	List<String> getAdditionalDataSourceNameList();

	DataSource getDataSource(String argDataSourceName);

	List<Entity> getEntities(String argDataSourceName);

	Entity getEntity(String argDataSourceName, String argEntityName);

	List<Field> getFields(String argDataSourceName, String argEntityName);

	Field getField(String argDataSourceName, String argEntityName,
			String argFieldName);

	DataSourceFieldName getDataSourceName(String argDataSourceName,
			String argEntityName, String argFieldName);

	Map<String, Field> getDataSourceFieldNameVsFieldMap(
			String argDataSourceName, String argEntityName);

}
