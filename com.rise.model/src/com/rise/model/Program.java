package com.rise.model;

import java.util.Set;

import com.rise.common.model.BaseModel;
import com.rise.common.model.ContactInformation;

public class Program extends BaseModel {

	private String name;
	private String inchargeOfficer;

	private ContactInformation contactInformation;
	private Set<TrainingProviderEmpanelment> trainingProviderEmpanelments;
	private Set<EmploymentAgencyEmpanelment> employmentAgencyEmpanelments;
	private Set<Department> departments;

	public Program() {
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

	public Set<TrainingProviderEmpanelment> getTrainingProviderEmpanelments() {
		return this.trainingProviderEmpanelments;
	}

	public void setTrainingProviderEmpanelments(
			Set<TrainingProviderEmpanelment> argTrainingProviderEmpanelments) {
		this.trainingProviderEmpanelments = argTrainingProviderEmpanelments;
	}

	public Set<EmploymentAgencyEmpanelment> getEmploymentAgencyEmpanelments() {
		return this.employmentAgencyEmpanelments;
	}

	public void setEmploymentAgencyEmpanelments(
			Set<EmploymentAgencyEmpanelment> argEmploymentAgencyEmpanelments) {
		this.employmentAgencyEmpanelments = argEmploymentAgencyEmpanelments;
	}

	public Set<Department> getDepartments() {
		return this.departments;
	}

	public void setDepartments(Set<Department> argDepartments) {
		this.departments = argDepartments;
	}

}
