package com.rise.model;

import java.util.Set;

import com.rise.common.model.BaseModel;
import com.rise.common.model.ContactInformation;

public class Ministry extends BaseModel {

	private String name;
	private String ministerName;

	private ContactInformation contactInformation;
	private Set<State> states;
	private Set<Department> departments;

	public Ministry() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getMinisterName() {
		return this.ministerName;
	}

	public void setMinisterName(String argMinisterName) {
		this.ministerName = argMinisterName;
	}

	public Set<State> getStates() {
		return this.states;
	}

	public void setStates(Set<State> argStates) {
		this.states = argStates;
	}

	public Set<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(Set<Department> argDepartments) {
		this.departments = argDepartments;
	}

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}

}
