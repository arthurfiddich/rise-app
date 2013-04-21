package com.rise.common.model;

public class EmailAddress extends BaseModel {

	private String domain;
	private String userName;
	private Descriptor descriptor;
	private ContactInformation contactInformation;

	public EmailAddress() {
		super();
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String argDomain) {
		this.domain = argDomain;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String argUserName) {
		this.userName = argUserName;
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
