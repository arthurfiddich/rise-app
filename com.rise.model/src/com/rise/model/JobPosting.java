package com.rise.model;

import java.util.Date;
import java.util.Set;

import com.rise.common.model.BaseModel;

public class JobPosting extends BaseModel {

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

	private EmploymentAgency employmentAgency;
	private Set<Candidate> candidates;
	private Set<TrainingProvider> trainingProviders;

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

	public EmploymentAgency getEmploymentAgency() {
		return this.employmentAgency;
	}

	public void setEmploymentAgency(EmploymentAgency argEmploymentAgency) {
		this.employmentAgency = argEmploymentAgency;
	}

	public Set<Candidate> getCandidates() {
		return this.candidates;
	}

	public void setCandidates(Set<Candidate> argCandidates) {
		this.candidates = argCandidates;
	}

	public Set<TrainingProvider> getTrainingProviders() {
		return this.trainingProviders;
	}

	public void setTrainingProviders(Set<TrainingProvider> argTrainingProviders) {
		this.trainingProviders = argTrainingProviders;
	}

}
