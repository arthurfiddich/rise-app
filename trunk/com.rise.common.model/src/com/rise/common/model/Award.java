package com.rise.common.model;

import java.util.Date;
import java.util.Set;

public class Award extends BaseModel {

	private String name;
	private String issuedBy;
	private Date dateIssued;

	private Set<Person> persons;

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

	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> argPersons) {
		this.persons = argPersons;
	}

}
