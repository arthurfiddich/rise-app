package com.rise.common.model;

import com.rise.common.util.annotation.DesiredField;
import com.rise.common.util.annotation.Validation;
import com.rise.common.util.annotation.ValidationType;

@Validation(validationType = ValidationType.ADDRESS)
public class Address extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DesiredField
	private String streetAddress;
	@DesiredField
	private String city;
	@DesiredField
	private String state;
	@DesiredField
	private String postalCode;
	@DesiredField
	private String country;

	private Candidate candidate;
	private TrainingBatch trainingBatch;
	private ContactInformation contactInformation;

	public Address() {
		super();
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

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate argCandidate) {
		this.candidate = argCandidate;
	}

	public TrainingBatch getTrainingBatch() {
		return this.trainingBatch;
	}

	public void setTrainingBatch(TrainingBatch argTrainingBatch) {
		this.trainingBatch = argTrainingBatch;
	}

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}

	@Override
	public String toString() {
		return "Address [streetAddress=" + this.streetAddress + ", city="
				+ this.city + ", state=" + this.state + ", postalCode="
				+ this.postalCode + ", country=" + this.country + "]";
	}

}
