package com.rise.common.model;

import java.util.Date;

public class Passport extends BaseModel {

	private String passportNumber;
	private String issuedBy;
	private String issuedPlace;
	private Date issuedDate;
	private Date expiryDate;
	private boolean emigrationCheckRequired;

	public Passport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPassportNumber() {
		return this.passportNumber;
	}

	public void setPassportNumber(String argPassportNumber) {
		this.passportNumber = argPassportNumber;
	}

	public String getIssuedBy() {
		return this.issuedBy;
	}

	public void setIssuedBy(String argIssuedBy) {
		this.issuedBy = argIssuedBy;
	}

	public String getIssuedPlace() {
		return this.issuedPlace;
	}

	public void setIssuedPlace(String argIssuedPlace) {
		this.issuedPlace = argIssuedPlace;
	}

	public Date getIssuedDate() {
		return this.issuedDate;
	}

	public void setIssuedDate(Date argIssuedDate) {
		this.issuedDate = argIssuedDate;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date argExpiryDate) {
		this.expiryDate = argExpiryDate;
	}

	public boolean isEmigrationCheckRequired() {
		return this.emigrationCheckRequired;
	}

	public void setEmigrationCheckRequired(boolean argEmigrationCheckRequired) {
		this.emigrationCheckRequired = argEmigrationCheckRequired;
	}

}
