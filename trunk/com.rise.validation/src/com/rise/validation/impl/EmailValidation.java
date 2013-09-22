package com.rise.validation.impl;

import com.rise.validation.Validation;

public class EmailValidation implements Validation<String, String>{

	@Override
	public boolean validate(String argToken) throws Exception {
		return true;
	}

	@Override
	public boolean validate(String argToken, String argRegion) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
