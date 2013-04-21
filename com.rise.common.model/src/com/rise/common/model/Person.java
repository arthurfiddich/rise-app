package com.rise.common.model;

import java.util.Date;

public class Person extends BaseModel {

	private PersonName personName;
	private Date dateOfBirth;
	// This should be computed at runtime.
	private int age;
	private String aadhaarNumber;
	private ContactInformation contactInformation;
	private Passport passport;
	
}
