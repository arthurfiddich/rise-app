package com.data.geneator.metadata.extraction;

import java.util.Arrays;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.constants.KeyBoardConstants;
import com.data.generator.util.Precondition;
import com.generator.data.xmlns.configuration.v1.DataSource;
import com.generator.data.xmlns.configuration.v1.DataSourceConfiguration;
import com.generator.data.xmlns.configuration.v1.ObjectFactory;
import com.generator.data.xmlns.configuration.v1.PicklistItem;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.PicklistEntry;

public class SfdcMetaDataConfigurationBuilder {

	private static Logger logger = LoggerFactory
			.getLogger(SfdcMetaDataConfigurationBuilder.class);
	private com.generator.data.xmlns.configuration.v1.ObjectFactory objectFactory;
	private com.generator.data.xmlns.configuration.v1.DataSourceConfiguration dataSourceConfiguration;
	private com.generator.data.xmlns.configuration.v1.DataSource dataSource;
	private com.generator.data.xmlns.configuration.v1.Entity entity;
	private XmlConfigurationHelper xmlConfigurationHelper = new XmlConfigurationHelper();

	public SfdcMetaDataConfigurationBuilder() {
		super();
		this.objectFactory = new com.generator.data.xmlns.configuration.v1.ObjectFactory();
	}

	public JAXBElement<DataSourceConfiguration> getDataSourceConfigurationElement() {
		return this.objectFactory
				.createDataSourceConfiguration(this.dataSourceConfiguration);

	}

	public DataSourceConfiguration buildDataSourceConfiguration() {
		this.dataSourceConfiguration = this.objectFactory
				.createDataSourceConfiguration();
		this.xmlConfigurationHelper
				.populateBaseObjectElements(this.dataSourceConfiguration);
		return this.dataSourceConfiguration;
	}

	public DataSource buildDataSource(String argDataSourceName) {
		if (logger.isInfoEnabled()) {
			logger.info("Building xml schema for data source name: "
					+ argDataSourceName);
		}
		this.dataSource = this.objectFactory.createDataSource();
		this.xmlConfigurationHelper.populateBaseObjectElements(this.dataSource);
		this.dataSource.setName(argDataSourceName);
		this.dataSource.setUniqueId(argDataSourceName);
		this.dataSourceConfiguration.setMainDataSource(dataSource);
		return this.dataSource;
	}

	public void buildEntity(DescribeSObjectResult argDescribeSObjectResult) {
		if (Precondition.checkNotNull(argDescribeSObjectResult)) {
			this.entity = this.objectFactory.createEntity();
			this.xmlConfigurationHelper.populateBaseObjectElements(this.entity);
			this.entity.setName(argDescribeSObjectResult.getName());
			this.entity.setUniqueId(this.dataSource.getUniqueId()
					+ KeyBoardConstants.DOT + this.entity.getName());
			this.entity.setLabel(argDescribeSObjectResult.getLabel());
			this.entity.setKeyPrefix(argDescribeSObjectResult.getKeyPrefix());
			this.entity.setDataSourceUniqueId(this.dataSource.getUniqueId());
			this.dataSource.getEntity().add(this.entity);
		}
	}

	public void buildFields(DescribeSObjectResult argDescribeSObjectResult) {
		if (Precondition.checkNotNull(argDescribeSObjectResult)) {
			Field[] fieldArray = argDescribeSObjectResult.getFields();
			for (Field field : fieldArray) {
				Integer fieldByteLength = field.getByteLength();
				String fieldCalculatedFormula = field.getCalculatedFormula();
				String fieldControllerName = field.getControllerName();
				Integer fieldDigits = field.getDigits();
				Boolean fieldExternalId = field.getExternalId();
				Boolean fieldHtmlFormatted = field.getHtmlFormatted();
				Boolean fieldNamePointing = field.getNamePointing();
				PicklistEntry[] pickListValues = field.getPicklistValues();
				String[] referenceTo = field.getReferenceTo();
				String fieldSoapType = null;
				if (Precondition.checkNotNull(field.getSoapType())) {
					fieldSoapType = field.getSoapType().toString();
				}
				Boolean fieldSortable = field.getSortable();
				Boolean fieldCalculated = field.isCalculated();
				Boolean fieldCreateable = field.isCreateable();
				Boolean fieldCustom = field.isCustom();
				Boolean fieldDefaultedOnCreate = field.isDefaultedOnCreate();
				Boolean fieldNameField = field.isNameField();
				Boolean fieldRestrictedPickList = field.isRestrictedPicklist();
				Boolean fieldUnique = field.isUnique();

				Boolean idLookup = field.isIdLookup();
				Boolean deprecatedAndHidden = field.isDeprecatedAndHidden();
				Boolean groupable = field.isGroupable();
				String inlineHelpText = field.getInlineHelpText();
				Integer relationshipOrder = field.getRelationshipOrder();
				Boolean writeRequiresMasterRead = field
						.getWriteRequiresMasterRead();

				com.generator.data.xmlns.configuration.v1.Field xmlField = this.objectFactory
						.createField();
				this.xmlConfigurationHelper
						.populateBaseObjectElements(xmlField);
				xmlField.setName(field.getName());
				xmlField.setUniqueId(this.entity.getUniqueId() + "."
						+ xmlField.getName());
				xmlField.setLabel(field.getLabel());
				xmlField.setDefaultValue(field.getDefaultValueFormula());
				xmlField.setType(field.getType() != null ? field.getType()
						.name() : null);
				xmlField.setLength((double) field.getLength());
				xmlField.setPrecision((long) field.getPrecision());
				xmlField.setScale((long) field.getScale());
				xmlField.setUpdatable(field.isUpdateable());
				xmlField.setNullable(field.isNillable());
				xmlField.setAutoNumber(field.isAutoNumber());
				xmlField.setCaseSensitive(field.isCaseSensitive());
				xmlField.setFilterable(field.isFilterable());
				// xmlField.setHasDependentPickLists(argField.getDependentPicklist()
				// !=
				// null
				// ? argField.getDependentPicklist() : false);
				xmlField.setHasDependentPickLists(field.getDependentPicklist());
				xmlField.setRelationship(field.getRelationshipName());
				//
				xmlField.setByteLength((double) fieldByteLength);
				xmlField.setCalculatedFormula(fieldCalculatedFormula);
				xmlField.setControllerName(fieldControllerName);
				xmlField.setDigits((double) fieldDigits);
				xmlField.setExternalId(fieldExternalId);
				xmlField.setHtmlFormatted(fieldHtmlFormatted);
				xmlField.setNamePointing(fieldNamePointing);
				xmlField.setSoapType(fieldSoapType);
				xmlField.setSortable(fieldSortable);
				xmlField.setCalculated(fieldCalculated);
				xmlField.setCreateable(fieldCreateable);
				xmlField.setCustom(fieldCustom);
				xmlField.setDefaultedOnCreate(fieldDefaultedOnCreate);
				xmlField.setNameField(fieldNameField);
				xmlField.setRestrictedPickList(fieldRestrictedPickList);
				xmlField.setUnique(fieldUnique);
				if (pickListValues != null && pickListValues.length > 0) {
					for (PicklistEntry pickListEntry : pickListValues) {
						PicklistItem pickListItem = new ObjectFactory()
								.createPicklistItem();
						pickListItem.setDataSource(this.dataSource.getName());
						pickListItem.setActive(pickListEntry.isActive());
						pickListItem.setDefaultValue(pickListEntry
								.isDefaultValue());
						pickListItem.setLabel(pickListEntry.getLabel());
						pickListItem.setValue(pickListEntry.getValue());
						xmlField.getPicklistItems().add(pickListItem);
					}
				}
				if (referenceTo != null && referenceTo.length > 0) {
					xmlField.getReferenceTo()
							.addAll(Arrays.asList(referenceTo));
				}
				field.setIdLookup(idLookup);
				field.setDeprecatedAndHidden(deprecatedAndHidden);
				field.setGroupable(groupable);
				field.setInlineHelpText(inlineHelpText);
				field.setRelationshipOrder(relationshipOrder);
				field.setWriteRequiresMasterRead(writeRequiresMasterRead);
				this.entity.getField().add(xmlField);
			}
		}
	}
}
