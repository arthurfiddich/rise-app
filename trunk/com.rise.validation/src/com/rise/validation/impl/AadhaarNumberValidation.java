package com.rise.validation.impl;

import com.rise.validation.Validation;

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
