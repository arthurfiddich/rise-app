package com.rise.common.model;

import java.util.Set;

import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "EmploymentAgency", mainTab = MainTab.FALSE)
public class EmploymentAgency extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String contactPersonName;
	private String name;

	private Set<EmploymentAgencyEmpanelment> employmentAgencyEmpanelments;
	private ContactInformation contactInformation;
	private Set<JobPosting> jobPostings;

	public EmploymentAgency() {
		super();
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String argContactPersonName) {
		this.contactPersonName = argContactPersonName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
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

	public Set<JobPosting> getJobPostings() {
		return this.jobPostings;
	}

	public void setJobPostings(Set<JobPosting> argJobPostings) {
		this.jobPostings = argJobPostings;
	}

}
