package com.rise.model;

import java.util.Set;

import com.rise.common.model.BaseModel;
import com.rise.common.model.ContactInformation;
import com.rise.common.model.Person;

public class Candidate extends BaseModel {

	private String contactPersonName;
	private String pfNumber;
	private String esiNumber;

	private Person person;
	private Set<ContactInformation> contactInformations;
	private Set<JobPosting> jobPostings;

	public Candidate() {
		super();
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String argContactPersonName) {
		this.contactPersonName = argContactPersonName;
	}

	public String getPfNumber() {
		return this.pfNumber;
	}

	public void setPfNumber(String argPfNumber) {
		this.pfNumber = argPfNumber;
	}

	public String getEsiNumber() {
		return this.esiNumber;
	}

	public void setEsiNumber(String argEsiNumber) {
		this.esiNumber = argEsiNumber;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person argPerson) {
		this.person = argPerson;
	}

	public Set<ContactInformation> getContactInformations() {
		return this.contactInformations;
	}

	public void setContactInformations(
			Set<ContactInformation> argContactInformations) {
		this.contactInformations = argContactInformations;
	}

	public Set<JobPosting> getJobPostings() {
		return this.jobPostings;
	}

	public void setJobPostings(Set<JobPosting> argJobPostings) {
		this.jobPostings = argJobPostings;
	}

}
