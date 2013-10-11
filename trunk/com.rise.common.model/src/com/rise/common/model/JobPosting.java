package com.rise.common.model;

import java.util.Date;
import java.util.Set;

import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "JobPosting", mainTab = MainTab.TRUE)
public class JobPosting extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jobHeading;
	private String jobTitle;
	private String jobCity;
	private String jobState;
	private Date datePosted;
	private Date dateLastUpdated;
	private String jobDescription;
	private String employmentAgencyName;
	private String jobPosterName;
	private String jobPosterEmailAddress;
	private String jobPosterMobileNumber;
	private boolean verified;

	private Set<CandidateJobPosting> candidateJobPostings;
	private EmploymentAgency employmentAgency;

	public JobPosting() {
		super();
	}

	public String getJobHeading() {
		return this.jobHeading;
	}

	public void setJobHeading(String argJobHeading) {
		this.jobHeading = argJobHeading;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String argJobTitle) {
		this.jobTitle = argJobTitle;
	}

	public String getJobCity() {
		return this.jobCity;
	}

	public void setJobCity(String argJobCity) {
		this.jobCity = argJobCity;
	}

	public String getJobState() {
		return this.jobState;
	}

	public void setJobState(String argJobState) {
		this.jobState = argJobState;
	}

	public Date getDatePosted() {
		return this.datePosted;
	}

	public void setDatePosted(Date argDatePosted) {
		this.datePosted = argDatePosted;
	}

	public Date getDateLastUpdated() {
		return this.dateLastUpdated;
	}

	public void setDateLastUpdated(Date argDateLastUpdated) {
		this.dateLastUpdated = argDateLastUpdated;
	}

	public String getJobDescription() {
		return this.jobDescription;
	}

	public void setJobDescription(String argJobDescription) {
		this.jobDescription = argJobDescription;
	}

	public String getEmploymentAgencyName() {
		return this.employmentAgencyName;
	}

	public void setEmploymentAgencyName(String argEmploymentAgencyName) {
		this.employmentAgencyName = argEmploymentAgencyName;
	}

	public String getJobPosterName() {
		return this.jobPosterName;
	}

	public void setJobPosterName(String argJobPosterName) {
		this.jobPosterName = argJobPosterName;
	}

	public String getJobPosterEmailAddress() {
		return this.jobPosterEmailAddress;
	}

	public void setJobPosterEmailAddress(String argJobPosterEmailAddress) {
		this.jobPosterEmailAddress = argJobPosterEmailAddress;
	}

	public String getJobPosterMobileNumber() {
		return this.jobPosterMobileNumber;
	}

	public void setJobPosterMobileNumber(String argJobPosterMobileNumber) {
		this.jobPosterMobileNumber = argJobPosterMobileNumber;
	}

	public boolean isVerified() {
		return this.verified;
	}

	public void setVerified(boolean argVerified) {
		this.verified = argVerified;
	}

	public Set<CandidateJobPosting> getCandidateJobPostings() {
		return this.candidateJobPostings;
	}

	public void setCandidateJobPostings(
			Set<CandidateJobPosting> argCandidateJobPostings) {
		this.candidateJobPostings = argCandidateJobPostings;
	}

	public EmploymentAgency getEmploymentAgency() {
		return this.employmentAgency;
	}

	public void setEmploymentAgency(EmploymentAgency argEmploymentAgency) {
		this.employmentAgency = argEmploymentAgency;
	}

}
