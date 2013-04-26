package com.rise.common.model;

import java.util.Date;

public class DriversLicense extends BaseModel {

	private String driversLicenseNumber;
	private String issuedBy;
	private String issuedState;
	private String issuedPlace;
	private Date issuedDate;
	private Date expiryDate;

	private DriversLicenseVehicleType driversLicenseVehicleType;
	private Person person;

	public DriversLicense() {
		super();
	}

	public String getDriversLicenseNumber() {
		return this.driversLicenseNumber;
	}

	public void setDriversLicenseNumber(String argDriversLicenseNumber) {
		this.driversLicenseNumber = argDriversLicenseNumber;
	}

	public String getIssuedBy() {
		return this.issuedBy;
	}

	public void setIssuedBy(String argIssuedBy) {
		this.issuedBy = argIssuedBy;
	}

	public String getIssuedState() {
		return this.issuedState;
	}

	public void setIssuedState(String argIssuedState) {
		this.issuedState = argIssuedState;
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

	public DriversLicenseVehicleType getDriversLicenseVehicleType() {
		return this.driversLicenseVehicleType;
	}

	public void setDriversLicenseVehicleType(
			DriversLicenseVehicleType argDriversLicenseVehicleType) {
		this.driversLicenseVehicleType = argDriversLicenseVehicleType;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person argPerson) {
		this.person = argPerson;
	}

}
