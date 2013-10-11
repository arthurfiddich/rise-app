package com.rise.common.model;

import java.util.Set;

import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "Program", mainTab = MainTab.FALSE)
public class Program extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String inchargeOfficer;
	private String name;

	private Set<TrainingProviderEmpanelment> trainingProviderEmpanelments;
	private Set<EmploymentAgencyEmpanelment> employmentAgencyEmpanelments;
	private ContactInformation contactInformation;
	private Set<TrainingBatch> trainingBatches;
	private Department department;

	public Program() {
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

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}

	public Set<TrainingBatch> getTrainingBatches() {
		return this.trainingBatches;
	}

	public void setTrainingBatches(Set<TrainingBatch> argTrainingBatches) {
		this.trainingBatches = argTrainingBatches;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department argDepartment) {
		this.department = argDepartment;
	}

}
