package com.rise.validation.impl;

import com.rise.validation.Validation;

/*
 * Reference Links: http://developer.uidai.gov.in/site/book/export/html/18
 * http://tahaz.files.wordpress.com/2010/07/aadhaar_authentication_api_1-0.pdf
 * http://developer.uidai.gov.in/site/downloads
 */

public class AadhaarNumberValidation implements Validation<String, String>{

	@Override
	public boolean validate(String argToken) throws Exception {
		return true;
	}

	@Override
	public boolean validate(String argToken, String argRegion) throws Exception {
		return false;
	}

}
