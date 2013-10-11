package com.rise.common.model;

import java.util.Date;

import com.rise.common.util.annotation.DesiredField;
import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "EmploymentExperience", mainTab = MainTab.FALSE)
public class EmploymentExperience extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DesiredField
	private String jobTitle;
	@DesiredField
	private Date fromDate;
	@DesiredField
	private Date toDate;
	@DesiredField
	private String description;
	@DesiredField
	private String reasonForLeaving;

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

	public String getReasonForLeaving() {
		return this.reasonForLeaving;
	}

	public void setReasonForLeaving(String argReasonForLeaving) {
		this.reasonForLeaving = argReasonForLeaving;
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
