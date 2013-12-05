package com.data.generator.executor;

import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectorConfig;

public class SfdcPartnerWebServiceImpl {
	public static void main(String[] args) {
		com.data.generator.salesforce.SfdcPartnerWebServiceImpl sfdcPartnerWebServiceImpl = new com.data.generator.salesforce.SfdcPartnerWebServiceImpl(
				"crypto@ciphercloud.com", "CipherCloud@321", "", null);
		sfdcPartnerWebServiceImpl.initialize();
		PartnerConnection partnerConnection = sfdcPartnerWebServiceImpl
				.getPartnerConnection();
		ConnectorConfig connectorConfig = partnerConnection.getConfig();
		System.out.println(connectorConfig.getAuthEndpoint());
	}
}
