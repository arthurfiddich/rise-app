package com.rise.common.model;

public class Phone extends BaseModel {

	private String countryCode;
	private String areaCode;
	private String phoneNumber;
	private String extension;
	private Descriptor descriptor;
	private PhoneNumberType phoneNumberType;
	private ContactInformation contactInformation;

	public Phone() {
		super();
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String argCountryCode) {
		this.countryCode = argCountryCode;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String argAreaCode) {
		this.areaCode = argAreaCode;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String argPhoneNumber) {
		this.phoneNumber = argPhoneNumber;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String argExtension) {
		this.extension = argExtension;
	}

	public Descriptor getDescriptor() {
		return this.descriptor;
	}

	public void setDescriptor(Descriptor argDescriptor) {
		this.descriptor = argDescriptor;
	}

	public PhoneNumberType getPhoneNumberType() {
		return this.phoneNumberType;
	}

	public void setPhoneNumberType(PhoneNumberType argPhoneNumberType) {
		this.phoneNumberType = argPhoneNumberType;
	}

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}
}
