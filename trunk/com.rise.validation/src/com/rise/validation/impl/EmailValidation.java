package com.rise.validation.impl;

import org.apache.commons.validator.routines.EmailValidator;

import com.rise.common.util.checker.Precondition;
import com.rise.validation.Validation;

public class EmailValidation implements Validation<String, String> {

	@Override
	public boolean validate(String argToken) throws Exception {
		if (Precondition.checkNotEmpty(argToken)) {
			return EmailValidator.getInstance(true).isValid(argToken);
		}
		return false;
	}

	@Override
	public boolean validate(String argToken, String argRegion) throws Exception {
		return false;
	}

}
