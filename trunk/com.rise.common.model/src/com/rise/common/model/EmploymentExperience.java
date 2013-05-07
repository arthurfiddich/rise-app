package com.rise.common.model;

import java.util.Date;

public class EmploymentExperience extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jobTitle;
	private Date fromDate;
	private Date toDate;
	private String description;

	private Person person;
	private Organization organization;

	public EmploymentExperience() {
		super();
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String argJobTitle) {
		this.jobTitle = argJobTitle;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date argFromDate) {
		this.fromDate = argFromDate;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date argToDate) {
		this.toDate = argToDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String argDescription) {
		this.description = argDescription;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person argPerson) {
		this.person = argPerson;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization argOrganization) {
		this.organization = argOrganization;
	}

}
