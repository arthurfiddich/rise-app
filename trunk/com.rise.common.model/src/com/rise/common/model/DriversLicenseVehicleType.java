package com.rise.common.model;

public class DriversLicenseVehicleType extends BaseModel {

	private String name;
	private String code;
	private String description;

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

}
