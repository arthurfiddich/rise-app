package com.rise.common.model;

public class Organization extends BaseModel {

	private String name;
	private String dateIncorporated;
	private String type;

	private ContactInformation contactInformation;

	public Organization() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getDateIncorporated() {
		return this.dateIncorporated;
	}

	public void setDateIncorporated(String argDateIncorporated) {
		this.dateIncorporated = argDateIncorporated;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String argType) {
		this.type = argType;
	}

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}

}
