package com.rise.common.model;

public class Address extends BaseModel {

	private String streetAddress;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private Descriptor descriptor;
	private ContactInformation contactInformation;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String argStreetAddress) {
		this.streetAddress = argStreetAddress;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String argCity) {
		this.city = argCity;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String argState) {
		this.state = argState;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String argPostalCode) {
		this.postalCode = argPostalCode;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String argCountry) {
		this.country = argCountry;
	}

	public Descriptor getDescriptor() {
		return this.descriptor;
	}

	public void setDescriptor(Descriptor argDescriptor) {
		this.descriptor = argDescriptor;
	}

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}
}