package com.rise.common.model;

public class PhoneNumberType extends BaseModel {

	private String name;
	private String description;

	public PhoneNumberType() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String argDescription) {
		this.description = argDescription;
	}

}
