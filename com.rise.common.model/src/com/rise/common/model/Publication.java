package com.rise.common.model;

import java.util.Date;

public class Publication extends BaseModel {

	private String publishedBy;
	private Date publishedDate;
	private String description;

	private Person person;

	public Publication() {
		super();
	}

	public String getPublishedBy() {
		return this.publishedBy;
	}

	public void setPublishedBy(String argPublishedBy) {
		this.publishedBy = argPublishedBy;
	}

	public Date getPublishedDate() {
		return this.publishedDate;
	}

	public void setPublishedDate(Date argPublishedDate) {
		this.publishedDate = argPublishedDate;
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
