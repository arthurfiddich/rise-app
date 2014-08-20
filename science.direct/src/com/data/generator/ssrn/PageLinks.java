package com.data.generator.ssrn;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.data.generator.file.impl.PropertyFileReader;
import com.data.generator.ssrn.util.SsrnProperties;
import com.data.generator.ssrn.util.SsrnUtl;
import com.data.generator.util.Precondition;

public class PageLinks {

	private final Properties properties;
	private PropertyFileReader propertyFileReader = new PropertyFileReader();

	public PageLinks(String argPropertiesFile) {
		this.properties = this.propertyFileReader.read(argPropertiesFile);
	}

	public List<String> getPageUrls() {
		String numberOfRecords = this.getProperties().getProperty(
				SsrnProperties.TOTAL_NUMBER_OF_RECORDS.getProperty());
		String recordsPerPage = this.getProperties().getProperty(
				SsrnProperties.RECORDS_PER_PAGE.getProperty());
		Precondition.ensureNotEmpty(numberOfRecords,
				"TotalNumberOfRecords Value");
		Precondition.ensureNotEmpty(recordsPerPage, "RecordsPerPage Value");
		int totalNumberOfRecords = Integer.parseInt(numberOfRecords);
		double totalRecordsPerPage = Float.parseFloat(recordsPerPage);
		int numberOfPages = (int) Math.ceil(totalNumberOfRecords
				/ totalRecordsPerPage);
		List<String> pageUrlsList = new ArrayList<String>();
		for (int i = 0; i < numberOfPages; i++) {
			String pageUrl = SsrnUtl.getPageUrl(i + 1);
			if (Precondition.checkNotEmpty(pageUrl)) {
				pageUrlsList.add(pageUrl);
			}
		}
		return pageUrlsList;
	}

	public Properties getProperties() {
		return this.properties;
	}

}
