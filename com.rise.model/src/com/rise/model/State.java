package com.rise.model;

import java.util.Set;

import com.rise.common.model.BaseModel;
import com.rise.common.model.ContactInformation;

public class State extends BaseModel {

	private String name;
	private String code;
	private String capitalCity;

	private ContactInformation contactInformation;
	private Set<Ministry> ministries;

	public State() {
		super();
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

	public String getCapitalCity() {
		return this.capitalCity;
	}

	public void setCapitalCity(String argCapitalCity) {
		this.capitalCity = argCapitalCity;
	}

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}

	public Set<Ministry> getMinistries() {
		return this.ministries;
	}

	public void setMinistries(Set<Ministry> argMinistries) {
		this.ministries = argMinistries;
	}

}
