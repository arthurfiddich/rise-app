package com.data.generator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import com.data.generator.util.GenericJaxbHelper;
import com.data.generator.util.Precondition;
import com.generator.data.xmlns.configuration.v1.AdditionalDataSource;
import com.generator.data.xmlns.configuration.v1.DataSource;
import com.generator.data.xmlns.configuration.v1.DataSourceConfiguration;
import com.generator.data.xmlns.configuration.v1.DataSourceFieldName;
import com.generator.data.xmlns.configuration.v1.Entity;
import com.generator.data.xmlns.configuration.v1.Field;

public class XmlFileConfigurationService implements ConfigurationService {

	private static final String PACKAGE_NAME = com.generator.data.xmlns.configuration.v1.ObjectFactory.class
			.getPackage().getName();

	private GenericJaxbHelper<DataSourceConfiguration> jaxbHelper;

	private DataSourceConfiguration dataSourceConfiguration;

	private DataSource dataSource;

	private List<AdditionalDataSource> additionalDataSourceList;

	public XmlFileConfigurationService(String argConfigurationXmlFile) {
		this.jaxbHelper = new GenericJaxbHelper<DataSourceConfiguration>(
				PACKAGE_NAME);

		try {
			JAXBElement<DataSourceConfiguration> dataSourceConfigurationElement = this.jaxbHelper
					.unmarshal(this.getClass().getResourceAsStream(
							argConfigurationXmlFile));
			this.dataSourceConfiguration = dataSourceConfigurationElement
					.getValue();
			this.dataSource = this.dataSourceConfiguration.getMainDataSource();
			this.additionalDataSourceList = this.dataSourceConfiguration
					.getAdditionalDataSource();
		} catch (Exception e) {
			throw new RuntimeException(
					"error while unmarshalling configuration xml file: "
							+ argConfigurationXmlFile, e);
		}
	}

	@Override
	public DataSourceConfiguration getDataSourceConfiguration() {
		return this.dataSourceConfiguration;
	}

	@Override
	public List<AdditionalDataSource> getAdditionalDataSourceList() {
		return this.additionalDataSourceList;
	}

	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Override
	public String getDataSourceName() {
		if (Precondition.checkNotNull(this.getDataSource())) {
			return this.getDataSource().getName();
		}
		return null;
	}

	@Override
	public List<String> getAdditionalDataSourceNameList() {
		if (Precondition.checkNotEmpty(this.getAdditionalDataSourceList())) {
			List<String> additionalDataSourceNameList = new ArrayList<String>();
			for (AdditionalDataSource additionalDataSource : this
					.getAdditionalDataSourceList()) {
				additionalDataSourceNameList
						.add(additionalDataSource.getName());
			}
			return additionalDataSourceNameList;
		}
		return null;
	}

	@Override
	public DataSource getDataSource(String argDataSourceName) {
		if (Precondition.checkNotEmpty(argDataSourceName)) {
			if (argDataSourceName.equals(this.getDataSourceName())) {
				return this.dataSource;
			}
		}
		return null;
	}

	@Override
	public List<Entity> getEntities(String argDataSourceName) {
		if (Precondition.checkNotEmpty(argDataSourceName)) {
			DataSource dataSource = this.getDataSource(argDataSourceName);
			if (Precondition.checkNotNull(dataSource)) {
				return dataSource.getEntity();
			}
		}
		return null;
	}

	@Override
	public Entity getEntity(String argDataSourceName, String argEntityName) {
		if (Precondition.checkNotEmpty(argEntityName)) {
			List<Entity> entitiesList = this.getEntities(argDataSourceName);
			if (Precondition.checkNotEmpty(entitiesList)) {
				for (Entity entity : entitiesList) {
					String entityName = entity.getName();
					if (entityName.equals(argEntityName)) {
						return entity;
					}
				}
			}
		}
		return null;
	}

	@Override
	public List<Field> getFields(String argDataSourceName, String argEntityName) {
		Entity entity = this.getEntity(argDataSourceName, argEntityName);
		if (Precondition.checkNotNull(entity)) {
			return entity.getField();
		}
		return null;
	}

	@Override
	public Field getField(String argDataSourceName, String argEntityName,
			String argFieldName) {
		if (Precondition.checkNotEmpty(argFieldName)) {
			List<Field> fieldsList = this.getFields(argDataSourceName,
					argEntityName);
			if (Precondition.checkNotEmpty(fieldsList)) {
				for (Field field : fieldsList) {
					if (field.getName().equals(argFieldName)) {
						return field;
					}
				}
			}
		}
		return null;
	}

	@Override
	public DataSourceFieldName getDataSourceName(String argDataSourceName,
			String argEntityName, String argFieldName) {
		Field field = this.getField(argDataSourceName, argEntityName,
				argFieldName);
		if (Precondition.checkNotNull(field)) {
			return field.getDataSourceFieldName();
		}
		return null;
	}

	@Override
	public Map<String, Field> getDataSourceFieldNameVsFieldMap(
			String argDataSourceName, String argEntityName) {
		List<Field> fieldList = this
				.getFields(argDataSourceName, argEntityName);
		if (Precondition.checkNotEmpty(fieldList)) {
			Map<String, Field> dataSourceFieldNameVsFieldMap = new HashMap<String, Field>();
			for (Field field : fieldList) {
				DataSourceFieldName dataSourceFieldName = field
						.getDataSourceFieldName();
				if (Precondition.checkNotNull(dataSourceFieldName)) {
					dataSourceFieldNameVsFieldMap.put(field.getName(), field);
				}
			}
			return dataSourceFieldNameVsFieldMap;
		}
		return null;
	}

}
