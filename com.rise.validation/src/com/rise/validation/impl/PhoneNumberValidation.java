package com.rise.validation.impl;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.rise.common.util.checker.Precondition;
import com.rise.validation.Validation;

public class PhoneNumberValidation implements Validation<String, String> {

	@Override
	public boolean validate(String argToken) throws Exception {
		return validate(argToken, "IN");
	}

	@Override
	public boolean validate(String argToken, String argRegion) throws Exception {
		if (Precondition.checkNotEmpty(argToken)
				&& Precondition.checkNotEmpty(argRegion)) {
			String inputValue = Precondition.ensureNotEmpty(argToken,
					"Input Token");
			String region = Precondition.ensureNotEmpty(argRegion, "Region");
			PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
			PhoneNumber phoneNumber = phoneNumberUtil.parseAndKeepRawInput(inputValue, region);
			return phoneNumberUtil.isValidNumber(phoneNumber);
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		// PhoneNumberValidation phoneNumberValidation = new
		// PhoneNumberValidation();
		// boolean correct = phoneNumberValidation.validate("714-756-2233");
		// System.out.println(correct);

		PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
		PhoneNumber phoneNumber = phoneNumberUtil.parseAndKeepRawInput(
				"714-756-2233", "US");
		System.out.println("Country Code: " + phoneNumber.getCountryCode());
		System.out.println("Extension: " + phoneNumber.getExtension());
		System.out.println("National Number: "
				+ phoneNumber.getNationalNumber());
		System.out.println("Domestic Carrier Code: "
				+ phoneNumber.getPreferredDomesticCarrierCode());
		System.out.println("Raw Input: " + phoneNumber.getRawInput());
		System.out.println("Country Code Source: "
				+ phoneNumber.getCountryCodeSource().toString());
	}
}
