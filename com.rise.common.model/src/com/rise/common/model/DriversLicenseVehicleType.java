package com.rise.common.model;

import java.util.Set;

public class DriversLicenseVehicleType extends BaseModel {

	private String name;
	private String code;
	private String description;

	private Set<DriversLicense> driversLicenses;

	public DriversLicenseVehicleType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String argCode) {
		this.code = argCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String argDescription) {
		this.description = argDescription;
	}

	public Set<DriversLicense> getDriversLicenses() {
		return this.driversLicenses;
	}

	public void setDriversLicenses(Set<DriversLicense> argDriversLicenses) {
		this.driversLicenses = argDriversLicenses;
	}

}
