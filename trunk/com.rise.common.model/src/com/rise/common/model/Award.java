package com.rise.common.model;

import java.util.Date;

public class Award extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String issuedBy;
	private Date dateIssued;
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
