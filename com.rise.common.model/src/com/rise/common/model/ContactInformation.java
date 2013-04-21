package com.rise.common.model;

import java.util.Set;

public class ContactInformation extends BaseModel {

	private Set<Phone> phoneNumbers;
	private Set<EmailAddress> emailAddresses;
	private Set<Website> webSites;
	private Set<InstantMessenger> instantMessengers;
	private Set<Address> addresses;

	public ContactInformation() {
		super();
	}

	public Set<Phone> getPhoneNumbers() {
		return this.phoneNumbers;
	}

	public void setPhoneNumbers(Set<Phone> argPhoneNumbers) {
		this.phoneNumbers = argPhoneNumbers;
	}

	public Set<EmailAddress> getEmailAddresses() {
		return this.emailAddresses;
	}

	public void setEmailAddresses(Set<EmailAddress> argEmailAddresses) {
		this.emailAddresses = argEmailAddresses;
	}

	public Set<Website> getWebSites() {
		return this.webSites;
	}

	public void setWebSites(Set<Website> argWebSites) {
		this.webSites = argWebSites;
	}

	public Set<InstantMessenger> getInstantMessengers() {
		return this.instantMessengers;
	}

	public void setInstantMessengers(Set<InstantMessenger> argInstantMessengers) {
		this.instantMessengers = argInstantMessengers;
	}

	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> argAddresses) {
		this.addresses = argAddresses;
	}

}
