package com.rise.common.model;

import java.util.Date;

import com.rise.common.util.annotation.DesiredField;

public class Award extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DesiredField
	private String name;
	@DesiredField
	private String issuedBy;
	@DesiredField
	private Date dateIssued;
	@DesiredField
	private String description;

	private Person person;

	public Award() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getIssuedBy() {
		return this.issuedBy;
	}

	public void setIssuedBy(String argIssuedBy) {
		this.issuedBy = argIssuedBy;
	}

	public Date getDateIssued() {
		return this.dateIssued;
	}

	public void setDateIssued(Date argDateIssued) {
		this.dateIssued = argDateIssued;
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

}
