package com.rise.common.model;

public class InstantMessenger extends BaseModel {

	private String userName;
	private Descriptor descriptor;
	private InstantMessengerProvider instantMessengerProvider;
	private ContactInformation contactInformation;

	public InstantMessenger() {
		super();
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

	public InstantMessengerProvider getInstantMessengerProvider() {
		return this.instantMessengerProvider;
	}

	public void setInstantMessengerProvider(
			InstantMessengerProvider argInstantMessengerProvider) {
		this.instantMessengerProvider = argInstantMessengerProvider;
	}

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}

}
