/**
 * SfdcPartnerWebServiceImpl
 *
 * Copyright (C) 2010 Data Generator, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Data Generator, Inc. ("Confidential Information"). 
 * 
 */
package com.data.generator.salesforce;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.GetUserInfoResult;
import com.sforce.soap.partner.LoginResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectorConfig;
import com.sforce.ws.bind.XmlObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class SfdcPartnerWebServiceImpl does the Actions related to Connecting to
 * the SFDC Server for Authenticating the User Credentials provided.
 */
public class SfdcPartnerWebServiceImpl implements SfdcPartnerWebService {

	private static Logger logger = LoggerFactory
			.getLogger(SfdcPartnerWebServiceImpl.class);

	/** The Constant APOSTROPHE. */
	private static final String APOSTROPHE = "'";

	/**
	 * The Constant SELECT_ID_NAME_FROM_PROFILE_WHERE_ID for selecting the
	 * ID,name from the Profile SObject in Salesforce based on the ID provided.
	 */
	private static final String SELECT_ID_NAME_FROM_PROFILE_WHERE_ID = "SELECT Id, Name from Profile where Id =";

	/** The Constant NAME. */
	private static final String NAME = "Name";

	/** The Constant DEFAULT_COMPRESS. */
	private static final boolean DEFAULT_COMPRESS = false;

	/** The Constant DEFAULT_BATCH_SIZE. */
	private static final int DEFAULT_BATCH_SIZE = 2000;

	/** The Constant COM_DATA_GENERATOR_HTTP_PROXY_AUTH_PASSWORD. */
	private static final String COM_DATA_GENERATOR_HTTP_PROXY_AUTH_PASSWORD = "com.data.generator.httpProxyAuthPassword";

	/** The Constant COM_DATA_GENERATOR_HTTP_PROXY_AUTH_USERNAME. */
	private static final String COM_DATA_GENERATOR_HTTP_PROXY_AUTH_USERNAME = "com.data.generator.httpProxyAuthUsername";

	/** The Constant COM_DATA_GENERATOR_HTTP_PROXY_PORT. */
	private static final String COM_DATA_GENERATOR_HTTP_PROXY_PORT = "com.data.generator.httpProxyPort";

	/** The Constant COM_DATA_GENERATOR_HTTP_PROXY_HOST_NAME. */
	private static final String COM_DATA_GENERATOR_HTTP_PROXY_HOST_NAME = "com.data.generator.httpProxyHostName";

	/** The partner connection. */
	private PartnerConnection partnerConnection;

	/** The user name. */
	private String userName;

	/** The password. */
	private String password;

	/** The security token. */
	private String securityToken;

	/** The lr. */
	private LoginResult lr;

	/** The session id. */
	private String sessionId;

	/** The server url. */
	private String serverUrl;

	/** The auth url. */
	private String authUrl;

	/** The manual login. */
	private boolean manualLogin;
	// FIXME added by raja reddy
	/** The metadata server url. */
	private String metadataServerUrl;

	/** The http proxy host name. */
	private String httpProxyHostName = System
			.getProperty(COM_DATA_GENERATOR_HTTP_PROXY_HOST_NAME);

	/** The http proxy port. */
	private String httpProxyPort = System
			.getProperty(COM_DATA_GENERATOR_HTTP_PROXY_PORT);

	/** The http proxy auth username. */
	private String httpProxyAuthUsername = System
			.getProperty(COM_DATA_GENERATOR_HTTP_PROXY_AUTH_USERNAME);

	/** The http proxy password. */
	private String httpProxyAuthPassword = System
			.getProperty(COM_DATA_GENERATOR_HTTP_PROXY_AUTH_PASSWORD);

	/**
	 * Gets the authentication url specified in the configuration file.
	 * 
	 * @return the auth url
	 */
	public String getAuthUrl() {
		return this.authUrl;
	}

	/**
	 * Sets the auth url.
	 * 
	 * @param argAuthUrl
	 *            the new auth url
	 */
	public void setAuthUrl(String argAuthUrl) {
		this.authUrl = argAuthUrl;
	}

	/**
	 * Instantiates a new sfdc partner web service impl.
	 * 
	 * @param argSessionId
	 *            the session id
	 * @param argServerUrl
	 *            the server url
	 */
	public SfdcPartnerWebServiceImpl(String argSessionId, String argServerUrl) {
		this.setSessionId(argSessionId);
		this.setServerUrl(argServerUrl);
		this.setManualLogin(true);
	}

	/**
	 * Instantiates a new sfdc partner web service impl.
	 * 
	 * @param argUserName
	 *            the user name
	 * @param argPassword
	 *            the password
	 * @param argSecurityToken
	 *            the security token
	 * @param argAuthUrl
	 *            the auth url
	 */
	public SfdcPartnerWebServiceImpl(String argUserName, String argPassword,
			String argSecurityToken, String argAuthUrl) {
		super();
		this.userName = argUserName;
		this.password = argPassword;
		this.securityToken = argSecurityToken;
		this.authUrl = argAuthUrl;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.data.generator.salesforce.SfdcPartnerWebService#getPartnerConnection
	 * ()
	 */
	@Override
	public PartnerConnection getPartnerConnection() {
		return this.partnerConnection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.data.generator.salesforce.SfdcPartnerWebService#setPartnerConnection
	 * (com.sforce.soap.partner.PartnerConnection)
	 */
	@Override
	public void setPartnerConnection(PartnerConnection argBinding) {
		this.partnerConnection = argBinding;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.data.generator.salesforce.SfdcPartnerWebService#getUserName()
	 */
	@Override
	public String getUserName() {
		return this.userName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.data.generator.salesforce.SfdcPartnerWebService#setUserName(java
	 * .lang.String)
	 */
	@Override
	public void setUserName(String argUserName) {
		this.userName = argUserName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.data.generator.salesforce.SfdcPartnerWebService#getPassword()
	 */
	@Override
	public String getPassword() {
		return this.password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.data.generator.salesforce.SfdcPartnerWebService#setPassword(java
	 * .lang.String)
	 */
	@Override
	public void setPassword(String argPassword) {
		this.password = argPassword;
	}

	/**
	 * Gets the security token.
	 * 
	 * @return the security token
	 */
	public String getSecurityToken() {
		return this.securityToken;
	}

	/**
	 * Sets the security token.
	 * 
	 * @param argSecurityToken
	 *            the new security token
	 */
	public void setSecurityToken(String argSecurityToken) {
		this.securityToken = argSecurityToken;
	}

	/**
	 * Gets the password with security token appended.
	 * 
	 * @return the password with security token
	 */
	public String getPasswordWithSecurityToken() {
		StringBuffer result = new StringBuffer();
		if (this.getPassword() != null) {
			result.append(this.getPassword());
		}
		if (this.getSecurityToken() != null) {
			result.append(this.getSecurityToken());
		}
		return result.toString();
	}

	/**
	 * Initializes the login procedure.
	 */
	public void initialize() {
		this.initializeBinding();
		if (this.getSessionId() == null) {
			this.login();
		}
	}

	/**
	 * Authenticates with the Salesforce server with the credentials provided by
	 * the User.
	 */
	protected void initializeBinding() {
		try {
			ConnectorConfig connectorConfig = new ConnectorConfig();
			connectorConfig.setCompression(DEFAULT_COMPRESS);
			if (this.getUserName() != null) {
				connectorConfig.setUsername(this.getUserName());
			}
			if (this.getPasswordWithSecurityToken() != null) {
				connectorConfig
						.setPassword(this.getPasswordWithSecurityToken());
			}
			if (this.getAuthUrl() != null) {
				connectorConfig.setAuthEndpoint(this.getAuthUrl());
			}
			if (this.isManualLogin()) {
				connectorConfig.setManualLogin(true);
			}
			if (this.getServerUrl() != null) {
				connectorConfig.setServiceEndpoint(this.getServerUrl());
			}
			if (getSessionId() != null) {
				connectorConfig.setSessionId(this.getSessionId());
			}
			if (getHttpProxyHostName() != null
					&& !getHttpProxyHostName().isEmpty()
					&& getHttpProxyPort() != null
					&& !getHttpProxyPort().isEmpty()) {
				int port = Integer.parseInt(this.getHttpProxyPort());
				connectorConfig.setProxy(this.getHttpProxyHostName(), port);
				if (getHttpProxyAuthUsername() != null
						&& !getHttpProxyAuthUsername().isEmpty()
						&& getHttpProxyAuthPassword() != null
						&& !getHttpProxyAuthPassword().isEmpty()) {
					connectorConfig
							.setProxyUsername(getHttpProxyAuthUsername());
					connectorConfig
							.setProxyPassword(getHttpProxyAuthPassword());
				}
			}
			this.partnerConnection = Connector.newConnection(connectorConfig);
			this.partnerConnection.setQueryOptions(DEFAULT_BATCH_SIZE);
		}
		// catch(com.sforce.ws.ConnectionException ce){
		// if(ce.toString().contains("INVALID_LOGIN")){
		// throw new RuntimeException("INVALID_LOGIN", ce);
		// }else{
		// throw new RuntimeException("CONNECTION_FAILED", ce);
		// }
		// }
		catch (Exception e) {
			logger.error("Error while making web service call for login : " + e);
			throw new RuntimeException("error while obtaining the binding"
					+ e.getMessage(), e);
		}
	}

	/**
	 * Performs Login Procedure.
	 */
	protected void login() {
		try {
			this.lr = this.partnerConnection.login(this.getUserName(),
					this.getPasswordWithSecurityToken());
			this.setSessionId(this.lr.getSessionId());
			this.setServerUrl(this.lr.getServerUrl());
			this.setMetadataServerUrl(this.lr.getMetadataServerUrl());

		} catch (Exception e) {
			throw new RuntimeException(
					"Error while loggin on to Salesforce. Please check your Login Credentials. "
							+ e.getMessage() != null ? e.getMessage() : "", e);
		}
	}

	/*
	 * Gets the UserInfo SObject by which we can get the Information about that
	 * user (non-Javadoc)
	 * 
	 * @see
	 * com.data.generator.salesforce.SfdcPartnerWebService#getUserInfo()
	 */
	@Override
	public GetUserInfoResult getUserInfo() {
		try {
			return this.partnerConnection.getUserInfo();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Gets the session id.
	 * 
	 * @return the session id
	 */
	public String getSessionId() {
		return this.sessionId;
	}

	/**
	 * Sets the session id.
	 * 
	 * @param argSessionId
	 *            the new session id
	 */
	public void setSessionId(String argSessionId) {
		this.sessionId = argSessionId;
	}

	/**
	 * Gets the server url.
	 * 
	 * @return the server url
	 */
	public String getServerUrl() {
		return this.serverUrl;
	}

	/**
	 * Sets the server url.
	 * 
	 * @param argServerUrl
	 *            the new server url
	 */
	public void setServerUrl(String argServerUrl) {
		this.serverUrl = argServerUrl;
	}

	/**
	 * Sets the manual login.
	 * 
	 * @param argManualLogin
	 *            the new manual login
	 */
	public void setManualLogin(boolean argManualLogin) {
		this.manualLogin = argManualLogin;
	}

	/**
	 * Checks if is manual login.
	 * 
	 * @return true, if is manual login
	 */
	public boolean isManualLogin() {
		return this.manualLogin;
	}

	/**
	 * Gets the metadata server url.
	 * 
	 * @return the metadata server url
	 */
	public String getMetadataServerUrl() {
		return this.metadataServerUrl;
	}

	/**
	 * Sets the metadata server url.
	 * 
	 * @param argMetadataServerUrl
	 *            the new metadata server url
	 */
	public void setMetadataServerUrl(String argMetadataServerUrl) {
		this.metadataServerUrl = argMetadataServerUrl;
	}

	/**
	 * Gets the profile name of an User for allocating the privileges allocated
	 * to that Profile.
	 * 
	 * @param argProfileId
	 *            the profile id
	 * @return the profile name
	 */
	public String getProfileName(String argProfileId) {
		String query = SELECT_ID_NAME_FROM_PROFILE_WHERE_ID + APOSTROPHE
				+ argProfileId + APOSTROPHE;
		try {
			String profileName = null;
			QueryResult queryResult = this.getPartnerConnection().query(query);
			if (queryResult != null && queryResult.getSize() > 0) {
				SObject[] queryResultRecords = queryResult.getRecords();
				if (queryResultRecords != null && queryResultRecords.length > 0) {
					SObject profile = queryResultRecords[0];
					if (profile.hasChildren()) {
						Iterator<XmlObject> profilexmlObject = profile
								.getChildren();
						Map<String, String> map = new HashMap<String, String>();
						if (profilexmlObject != null) {
							while (profilexmlObject.hasNext()) {
								XmlObject profileXmlObj = profilexmlObject
										.next();
								map.put(profileXmlObj.getName().getLocalPart(),
										profileXmlObj.getValue().toString());
							}
							profileName = map.get(NAME);
						}
					}
					return profileName;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"Exception while getting profile name for profile id:"
							+ argProfileId, e);
		}
		return null;
	}

	public String getHttpProxyHostName() {
		return this.httpProxyHostName;
	}

	public void setHttpProxyHostName(String argHttpProxyHostName) {
		this.httpProxyHostName = argHttpProxyHostName;
	}

	public String getHttpProxyPort() {
		return this.httpProxyPort;
	}

	public void setHttpProxyPort(String argHttpProxyPort) {
		this.httpProxyPort = argHttpProxyPort;
	}

	public String getHttpProxyAuthUsername() {
		return this.httpProxyAuthUsername;
	}

	public void setHttpProxyAuthUsername(String argHttpProxyAuthUsername) {
		this.httpProxyAuthUsername = argHttpProxyAuthUsername;
	}

	public String getHttpProxyAuthPassword() {
		return this.httpProxyAuthPassword;
	}

	public void setHttpProxyAuthPassword(String argHttpProxyAuthPassword) {
		this.httpProxyAuthPassword = argHttpProxyAuthPassword;
	}
}
