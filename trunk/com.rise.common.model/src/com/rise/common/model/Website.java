package com.rise.common.model;

public class Website extends BaseModel {

	private String url;
	private Descriptor descriptor;
	private ContactInformation contactInformation;

	public Website() {
		super();
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String argUrl) {
		this.url = argUrl;
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
