package com.rise.common.model;

import java.util.Set;

import com.rise.common.model.BaseModel;
import com.rise.common.model.ContactInformation;

public class Ministry extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ministerName;
	private String name;

	private ContactInformation contactInformation;
	private Set<Department> departments;
	private State state;

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

	public State getState() {
		return this.state;
	}

	public void setState(State argState) {
		this.state = argState;
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
