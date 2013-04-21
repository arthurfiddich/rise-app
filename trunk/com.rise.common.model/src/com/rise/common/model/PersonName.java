package com.rise.common.model;

public class PersonName extends BaseModel {

	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;

	public PersonName() {
		super();
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String argTitle) {
		this.title = argTitle;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String argFirstName) {
		this.firstName = argFirstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String argMiddleName) {
		this.middleName = argMiddleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String argLastName) {
		this.lastName = argLastName;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String argSuffix) {
		this.suffix = argSuffix;
	}

}
