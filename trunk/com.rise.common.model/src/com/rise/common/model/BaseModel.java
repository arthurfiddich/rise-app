package com.rise.common.model;

import java.util.Date;

public class BaseModel {

	private Integer id;
	private Integer createdBy;
	private Integer modifiedBy;
	private Date dateCreated;
	private Date dateModified;
	private String recordStatus;

	public BaseModel() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer argId) {
		this.id = argId;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer argCreatedBy) {
		this.createdBy = argCreatedBy;
	}

	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Integer argModifiedBy) {
		this.modifiedBy = argModifiedBy;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date argDateCreated) {
		this.dateCreated = argDateCreated;
	}

	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Date argDateModified) {
		this.dateModified = argDateModified;
	}

	public String getRecordStatus() {
		return this.recordStatus;
	}

	public void setRecordStatus(String argRecordStatus) {
		this.recordStatus = argRecordStatus;
	}

}
