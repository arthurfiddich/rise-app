package com.data.generator.ssrn.util;

import com.data.generator.util.Precondition;

public class SsrnUtl {

	/**
	 * This method will construct a URL of a particular page based on the page
	 * number that we passed as an argument.
	 * 
	 * @param argPageNumber
	 * @return
	 */
	public static String getPageUrl(int argPageNumber) {
		int pageNumber = Precondition.ensureNonNegative(argPageNumber,
				"Page Number");
		StringBuilder pageUrlBuilder = new StringBuilder();
		pageUrlBuilder.append(SsrnConstants.ECONOMICS_PAGE_URL_PART_1);
		pageUrlBuilder.append(pageNumber);
		pageUrlBuilder.append(SsrnConstants.ECONOMICS_PAGE_URL_PART_2);
		return pageUrlBuilder.toString();
	}
}
