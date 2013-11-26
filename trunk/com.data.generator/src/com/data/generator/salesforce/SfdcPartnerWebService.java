/**
 * SfdcPartnerWebService
 *
 * Copyright (C) 2010 CipherCloud, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * CipherCloud, Inc. ("Confidential Information"). 
 * 
 * For more information, visit http://www.ciphercloud.com
 */
package com.data.generator.salesforce;

import com.sforce.soap.partner.GetUserInfoResult;
import com.sforce.soap.partner.PartnerConnection;

/**
 * The Interface SfdcPartnerWebService is used to connect the SFDC server for
 * extracting the metadata from the ORG credentials Provided.
 */
public interface SfdcPartnerWebService {

	/**
	 * Gets the partner connection.
	 * 
	 * @return the partner connection
	 */
	public abstract PartnerConnection getPartnerConnection();

	/**
	 * Sets the partner connection.
	 * 
	 * @param argBinding
	 *            the new partner connection
	 */
	public abstract void setPartnerConnection(PartnerConnection argBinding);

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public abstract String getUserName();

	/**
	 * Sets the user name.
	 * 
	 * @param argUserName
	 *            the new user name
	 */
	public abstract void setUserName(String argUserName);

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public abstract String getPassword();

	/**
	 * Sets the password.
	 * 
	 * @param argPassword
	 *            the new password
	 */
	public abstract void setPassword(String argPassword);

	/**
	 * Gets the user info.
	 * 
	 * @return the user info
	 */
	public GetUserInfoResult getUserInfo();
}