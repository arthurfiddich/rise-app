package com.data.generator.ssrn.util;

public enum SsrnProperties {

	TOTAL_NUMBER_OF_RECORDS("totalNumberOfRecords"), RECORDS_PER_PAGE(
			"recordsPerPage"), HOME_PAGE_URL("homePageUrl");

	private final String property;

	private SsrnProperties(String argProperty) {
		this.property = argProperty;
	}

	public String getProperty() {
		return this.property;
	}

}
