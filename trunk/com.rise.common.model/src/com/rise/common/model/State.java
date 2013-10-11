package com.rise.common.model;

import java.util.Set;

import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "State", mainTab = MainTab.FALSE)
public class State extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
