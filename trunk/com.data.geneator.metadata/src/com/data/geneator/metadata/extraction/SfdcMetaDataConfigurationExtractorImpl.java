package com.data.geneator.metadata.extraction;

import java.util.Arrays;
import java.util.List;

import com.data.generator.salesforce.SfdcPartnerWebServiceImpl;
import com.data.generator.util.Precondition;
import com.sforce.soap.partner.DescribeGlobalResult;
import com.sforce.soap.partner.DescribeGlobalSObjectResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;

public class SfdcMetaDataConfigurationExtractorImpl extends
		SfdcPartnerWebServiceImpl implements SfdcMetaDataConfigurationExtractor {

	public SfdcMetaDataConfigurationExtractorImpl(String argUserName,
			String argPassword, String argSecurityToken) {
		super(argUserName, argPassword, argSecurityToken, null);
	}

	public SfdcMetaDataConfigurationExtractorImpl(String argSessionId,
			String argServerUrl) {
		super(argSessionId, argServerUrl);
	}

	public SfdcMetaDataConfigurationExtractorImpl(String argUserName,
			String argPassword, String argSecurityToken, String argAuthUrl) {
		super(argUserName, argPassword, argSecurityToken, argAuthUrl);
	}

	@Override
	public void initialize() {
		super.initialize();
	}

	@Override
	public DescribeGlobalResult getDescribeGlobalResult() {
		try {
			initialize();
			PartnerConnection partnerConnection = super.getPartnerConnection();
			if (Precondition.checkNotNull(partnerConnection)) {
				return partnerConnection.describeGlobal();
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(
					"error while retrieving DescribeGlobalResult", e);
		}
	}

	@Override
	public String[] getAllTypes() {
		DescribeGlobalResult describeGlobalResult = this
				.getDescribeGlobalResult();
		if (Precondition.checkNotNull(describeGlobalResult)) {
			DescribeGlobalSObjectResult[] describeGlobalSObjectResultArray = describeGlobalResult
					.getSobjects();
			if (Precondition.checkNotEmpty(describeGlobalSObjectResultArray)) {
				String[] types = new String[describeGlobalSObjectResultArray.length];
				for (int i = 0; i < describeGlobalSObjectResultArray.length; i++) {
					types[i] = describeGlobalSObjectResultArray[i].getName();
				}
				return types;
			}
		}
		return null;
	}

	@Override
	public DescribeSObjectResult getDescribeSObjectResult(String argObjectName) {
		if (Precondition.checkNotEmpty(argObjectName)) {
			PartnerConnection partnerConnection = super.getPartnerConnection();
			if (Precondition.checkNotNull(partnerConnection)) {
				try {
					return partnerConnection.describeSObject(argObjectName);
				} catch (ConnectionException e) {
					throw new RuntimeException(
							"error while retrieving DescribeSObjectResult for object name: "
									+ argObjectName, e);
				}
			}
		}
		return null;
	}

	@Override
	public List<DescribeSObjectResult> getDescribeSObjectResultArray(
			String[] argObjectNameArray) {
		if (Precondition.checkNotEmpty(argObjectNameArray)) {
			PartnerConnection partnerConnection = super.getPartnerConnection();
			if (Precondition.checkNotNull(partnerConnection)) {
				try {
					DescribeSObjectResult[] describeSObjectResultArray = partnerConnection
							.describeSObjects(argObjectNameArray);
					return Arrays.asList(describeSObjectResultArray);
				} catch (ConnectionException e) {
					throw new RuntimeException(
							"error while retrieving DescribeSObjectResult for an array object names: ",
							e);
				}
			}
		}
		return null;
	}

	@Override
	public Field[] getFields(String argObjectName) {
		if (Precondition.checkNotEmpty(argObjectName)) {
			DescribeSObjectResult describeGlobalSObjectResult = this
					.getDescribeSObjectResult(argObjectName);
			return this.getFields(describeGlobalSObjectResult);
		}
		return null;
	}

	@Override
	public Field[] getFields(DescribeSObjectResult argDescribeSObjectResult) {
		if (Precondition.checkNotNull(argDescribeSObjectResult)) {
			return argDescribeSObjectResult.getFields();
		}
		return null;
	}

}
