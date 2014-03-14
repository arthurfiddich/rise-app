package com.data.geneator.metadata.extraction;

import java.util.List;

import com.sforce.soap.partner.DescribeGlobalResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;

public interface SfdcMetaDataConfigurationExtractor {

	void initialize();

	DescribeGlobalResult getDescribeGlobalResult();

	String[] getAllTypes();

	DescribeSObjectResult getDescribeSObjectResult(String argObjectName);
	
	List<DescribeSObjectResult> getDescribeSObjectResultArray(String[] argObjectNameArray);

	Field[] getFields(String argObjectName);

	Field[] getFields(DescribeSObjectResult argDescribeSObjectResult);
}
