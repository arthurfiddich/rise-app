package com.data.generator.db.operations.impl;

import com.data.generator.constants.DataGeneratorConstants;
import com.data.generator.db.operations.Authentication;
import com.data.generator.salesforce.SfdcPartnerWebServiceImpl;
import com.data.generator.util.Precondition;
import com.sforce.soap.partner.PartnerConnection;

/**
 * 
 * @author Amar, This class will make a web service call to salesforce for
 *         authentication and it will give the PartnerConnection instance...
 * 
 */
public class SfdcAuthentication implements Authentication<PartnerConnection> {

	@Override
	public PartnerConnection authenticate(String argUserName,
			String argPassword, String argSecurityToken) {
		Precondition.ensureNotEmpty(argUserName,
				DataGeneratorConstants.USER_NAME);
		Precondition.ensureNotEmpty(argPassword,
				DataGeneratorConstants.PASSWORD);

		SfdcPartnerWebServiceImpl sfdcPartnerWebServiceImpl = new SfdcPartnerWebServiceImpl(
				argUserName, argPassword, argSecurityToken,
				DataGeneratorConstants.SFDC_AUTH_URL);
		sfdcPartnerWebServiceImpl.initialize();
		return sfdcPartnerWebServiceImpl.getPartnerConnection();
	}

}
