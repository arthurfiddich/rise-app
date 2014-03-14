package com.data.geneator.metadata.extraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.generator.salesforce.SfdcPartnerWebServiceImpl;

public class DataSourceConfigurationManager {

	private static Logger logger = LoggerFactory
			.getLogger(DataSourceConfigurationManager.class);

	private final String sfdcUserName;
	private final String sfdcPassword;
	private final String SfdcSecurityToken;
	private final String sfdcAuthenticationUrl;
	private String outputXmlFilePath;
	private String dataSourceName;

	private final SfdcPartnerWebServiceImpl sfdcPartnerWebServiceImpl;

	public DataSourceConfigurationManager(String argUserName,
			String argPassword, String argSecurityToken,
			String argOutputXmlFilePath, String argDataSourceName) {
		this(argUserName, argPassword, argSecurityToken, argOutputXmlFilePath,
				argDataSourceName, null);
	}

	public DataSourceConfigurationManager(String argUserName,
			String argPassword, String argSecurityToken,
			String argOutputXmlFilePath, String argDataSourceName,
			String argAuthUrl) {
		super();
		this.sfdcUserName = argUserName;
		this.sfdcPassword = argPassword;
		this.SfdcSecurityToken = argSecurityToken;
		this.sfdcAuthenticationUrl = argAuthUrl;
		this.outputXmlFilePath = argOutputXmlFilePath;
		this.dataSourceName = argDataSourceName;
		this.sfdcPartnerWebServiceImpl = new SfdcPartnerWebServiceImpl(
				this.getSfdcUserName(), this.getSfdcPassword(),
				this.getSfdcSecurityToken(), this.getSfdcAuthenticationUrl());
	}

	public void login() {
		this.getSfdcPartnerWebServiceImpl().initialize();
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

	public String getSfdcAuthenticationUrl() {
		return this.sfdcAuthenticationUrl;
	}

	public String getOutputXmlFilePath() {
		return this.outputXmlFilePath;
	}

	public void setOutputXmlFilePath(String argOutputXmlFilePath) {
		this.outputXmlFilePath = argOutputXmlFilePath;
	}

	public String getDataSourceName() {
		return this.dataSourceName;
	}

	public void setDataSourceName(String argDataSourceName) {
		this.dataSourceName = argDataSourceName;
	}

	public SfdcPartnerWebServiceImpl getSfdcPartnerWebServiceImpl() {
		return this.sfdcPartnerWebServiceImpl;
	}

}
