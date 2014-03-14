package com.data.geneator.metadata.extraction;

public class SfdcMetaDataExtractorTest {
	public static void main(String[] args) {
		SfdcMetaDataConfigurationManager sfdcMetaDataConfigurationManager = new SfdcMetaDataConfigurationManager(
				"./input/dg.xml", "DataGeneraton", "akram@ciphercloud.com",
				"info*123", "NDyogDWFbbdyDXJus68gPQGB");
		sfdcMetaDataConfigurationManager.build();
	}
}
