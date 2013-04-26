package com.rise.model;

import java.util.Set;

import com.rise.common.model.BaseModel;
import com.rise.common.model.ContactInformation;

public class TrainingProvider extends BaseModel {

	private String name;
	private String contactPersonName;

	private ContactInformation contactInformation;
	private Set<TrainingProviderEmpanelment> trainingProviderEmpanelments;
	private Set<JobPosting> jobPostings;

	public TrainingProvider() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String argContactPersonName) {
		this.contactPersonName = argContactPersonName;
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

	public Set<JobPosting> getJobPostings() {
		return this.jobPostings;
	}

	public void setJobPostings(Set<JobPosting> argJobPostings) {
		this.jobPostings = argJobPostings;
	}

}
