package com.rise.common.model;

import java.util.Set;

import com.rise.common.model.BaseModel;
import com.rise.common.model.ContactInformation;
import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "Department", mainTab = MainTab.TRUE)
public class Department extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String inchargeOfficer;
	private String name;

	private ContactInformation contactInformation;
	private Set<Program> programs;
	private Ministry ministry;

	public Department() {
		super();
	}

	public String getInchargeOfficer() {
		return this.inchargeOfficer;
	}

	public void setInchargeOfficer(String argInchargeOfficer) {
		this.inchargeOfficer = argInchargeOfficer;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
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

	public Ministry getMinistry() {
		return this.ministry;
	}

	public void setMinistry(Ministry argMinistry) {
		this.ministry = argMinistry;
	}

}
