package com.data.geneator.metadata.extraction;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.util.GenericJaxbHelper;
import com.data.generator.util.Precondition;
import com.generator.data.xmlns.configuration.v1.DataSourceConfiguration;
import com.sforce.soap.partner.DescribeSObjectResult;

public class SfdcMetaDataConfigurationManager {

	private static Logger logger = LoggerFactory
			.getLogger(SfdcMetaDataConfigurationManager.class);
	private static final String PACKAGE_NAME = com.generator.data.xmlns.configuration.v1.ObjectFactory.class
			.getPackage().getName();
	private SfdcMetaDataConfigurationExtractor sfdcMetaDataConfigurationExtractor;
	private SfdcMetaDataConfigurationBuilder sfdcMetaDataConfigurationBuilder;
	private String dataSourceConfigurationXmlFilePath;
	private String dataSourceName;
	private final String sfdcUserName;
	private final String sfdcPassword;
	private final String SfdcSecurityToken;

	public SfdcMetaDataConfigurationManager(
			String argDataSourceConfigurationXmlFilePath,
			String argDataSourceName, String argSfdcUserName,
			String argSfdcPassword, String argSfdcSecurityToken) {
		this(argDataSourceConfigurationXmlFilePath, argDataSourceName,
				argSfdcUserName, argSfdcPassword, argSfdcSecurityToken, null);
	}

	public SfdcMetaDataConfigurationManager(
			String argDataSourceConfigurationXmlFilePath,
			String argDataSourceName, String argSfdcUserName,
			String argSfdcPassword, String argSfdcSecurityToken,
			String argServerUrl) {
		super();
		this.dataSourceConfigurationXmlFilePath = argDataSourceConfigurationXmlFilePath;
		this.dataSourceName = argDataSourceName;
		this.sfdcUserName = argSfdcUserName;
		this.sfdcPassword = argSfdcPassword;
		this.SfdcSecurityToken = argSfdcSecurityToken;
		this.sfdcMetaDataConfigurationExtractor = new SfdcMetaDataConfigurationExtractorImpl(
				argSfdcUserName, argSfdcPassword, argSfdcSecurityToken,
				argServerUrl);
		this.sfdcMetaDataConfigurationBuilder = new SfdcMetaDataConfigurationBuilder();
	}

	public void login() {
		if (logger.isInfoEnabled()) {
			logger.info("Initializing a partner connection to salesforce: ");
		}
		this.sfdcMetaDataConfigurationExtractor.initialize();
	}

	public void build() {
		if (logger.isInfoEnabled()) {
			logger.info("Building Salesforce Application Schema...: ");
		}
		this.sfdcMetaDataConfigurationBuilder.buildDataSourceConfiguration();
		this.sfdcMetaDataConfigurationBuilder
				.buildDataSource(this.dataSourceName);
		String[] types = this.sfdcMetaDataConfigurationExtractor.getAllTypes();

		try {
			if (Precondition.checkNotEmpty(types)) {
				for (int i = 0; i < types.length; i++) {
					String entityName = types[i];
//					if (entityName.equals("Account")
//							|| entityName.equals("Contact")
//							|| entityName.equals("Lead")
//							|| entityName.equals("Opportunity")) {
						DescribeSObjectResult describeSObjectResult = this.sfdcMetaDataConfigurationExtractor
								.getDescribeSObjectResult(entityName);
						logger.info("Building xml schema for entity #" + i
								+ " entity name: " + entityName);
						this.sfdcMetaDataConfigurationBuilder
								.buildEntity(describeSObjectResult);
						this.sfdcMetaDataConfigurationBuilder
								.buildFields(describeSObjectResult);
					}
//				}
				this.writeConfigurationXmlFile(this.dataSourceConfigurationXmlFilePath);
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"Exception while building data source configuration xml file: ",
					e);
		}
	}

	private void writeConfigurationXmlFile(
			String argDataSourceConfigurationXmlFilePath)
			throws FileNotFoundException, JAXBException {
		Precondition.checkNotEmpty(argDataSourceConfigurationXmlFilePath);
		JAXBElement<DataSourceConfiguration> dataSourceConfigurationElement = this.sfdcMetaDataConfigurationBuilder
				.getDataSourceConfigurationElement();
		GenericJaxbHelper<DataSourceConfiguration> genericJaxbHelper = new GenericJaxbHelper<DataSourceConfiguration>(
				PACKAGE_NAME);
		genericJaxbHelper.marshal(argDataSourceConfigurationXmlFilePath,
				dataSourceConfigurationElement);
	}

	public String getSfdcUserName() {
		return this.sfdcUserName;
	}

	public String getSfdcPassword() {
		return this.sfdcPassword;
	}

	public String getSfdcSecurityToken() {
		return this.SfdcSecurityToken;
	}

}
