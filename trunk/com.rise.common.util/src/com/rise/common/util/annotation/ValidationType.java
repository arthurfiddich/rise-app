package com.rise.common.util.annotation;

import com.rise.common.util.checker.Precondition;

public enum ValidationType {
	PHONE_NUMBER("PhoneNumber"), EMAIL("Email"), DATE_OF_BIRTH("Dob"), AADHAAR_NUMBER(
			"AadhaarNumber"), WEBSITE("Website");

	public String validationType;

	private ValidationType(String argValidationType) {
		this.validationType = argValidationType;
	}

	public static String getType(String argType) {
		ValidationType validationType = ValidationType.valueOf(argType);
		if (Precondition.checkNotNull(validationType)) {
			return validationType.name();
		}
		return null;
	}
}
