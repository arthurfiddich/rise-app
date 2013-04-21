package com.rise.common.model;

public class ContactInformation extends BaseModel {

	private String streetAddress;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private String email;
	private String alternativeEmail;
	private String phone;
	private String alternativePhone;
	private String website;

	public ContactInformation() {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String argEmail) {
		this.email = argEmail;
	}

	public String getAlternativeEmail() {
		return this.alternativeEmail;
	}

	public void setAlternativeEmail(String argAlternativeEmail) {
		this.alternativeEmail = argAlternativeEmail;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String argPhone) {
		this.phone = argPhone;
	}

	public String getAlternativePhone() {
		return this.alternativePhone;
	}

	public void setAlternativePhone(String argAlternativePhone) {
		this.alternativePhone = argAlternativePhone;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String argWebsite) {
		this.website = argWebsite;
	}

}
