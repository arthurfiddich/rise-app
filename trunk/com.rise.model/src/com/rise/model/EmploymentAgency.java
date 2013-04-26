package com.rise.model;

import java.util.Set;

import com.rise.common.model.BaseModel;
import com.rise.common.model.ContactInformation;

public class EmploymentAgency extends BaseModel {

	private String name;
	private String contactPersonName;

	private ContactInformation contactInformation;
	private Set<JobPosting> jobPostings;
	private Set<EmploymentAgencyEmpanelment> employmentAgencyEmpanelments;

	public EmploymentAgency() {
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

	public Set<JobPosting> getJobPostings() {
		return this.jobPostings;
	}

	public void setJobPostings(Set<JobPosting> argJobPostings) {
		this.jobPostings = argJobPostings;
	}

	public Set<EmploymentAgencyEmpanelment> getEmploymentAgencyEmpanelments() {
		return this.employmentAgencyEmpanelments;
	}

	public void setEmploymentAgencyEmpanelments(
			Set<EmploymentAgencyEmpanelment> argEmploymentAgencyEmpanelments) {
		this.employmentAgencyEmpanelments = argEmploymentAgencyEmpanelments;
	}

}
