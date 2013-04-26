package com.rise.model;

import java.util.Set;

import com.rise.common.model.BaseModel;
import com.rise.common.model.ContactInformation;

public class Department extends BaseModel {

	private String name;
	private String inchargeOfficer;

	private ContactInformation contactInformation;
	private Set<Program> programs;
	private Set<Ministry> ministries;

	public Department() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getInchargeOfficer() {
		return this.inchargeOfficer;
	}

	public void setInchargeOfficer(String argInchargeOfficer) {
		this.inchargeOfficer = argInchargeOfficer;
	}

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}

	public Set<Program> getPrograms() {
		return this.programs;
	}

	public void setPrograms(Set<Program> argPrograms) {
		this.programs = argPrograms;
	}

	public Set<Ministry> getMinistries() {
		return this.ministries;
	}

	public void setMinistries(Set<Ministry> argMinistries) {
		this.ministries = argMinistries;
	}

}
