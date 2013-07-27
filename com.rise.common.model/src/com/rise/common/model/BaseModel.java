package com.rise.common.model;

import java.util.Date;

import com.rise.common.util.annotation.DesiredField;

public class BaseModel implements Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@DesiredField
	private Integer id;
	private Integer createdBy;
	private Integer modifiedBy;
	private Date dateCreated;
	private Date dateModified;
//	private String recordStatus;

	public BaseModel() {
		super();
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer argId) {
		this.id = argId;
	}

	@Override
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	@Override
	public void setCreatedBy(Integer argCreatedBy) {
		this.createdBy = argCreatedBy;
	}

	@Override
	public Integer getModifiedBy() {
		return this.modifiedBy;
	}

	@Override
	public void setModifiedBy(Integer argModifiedBy) {
		this.modifiedBy = argModifiedBy;
	}

	@Override
	public Date getDateCreated() {
		return this.dateCreated;
	}

	@Override
	public void setDateCreated(Date argDateCreated) {
		this.dateCreated = argDateCreated;
	}

	@Override
	public Date getDateModified() {
		return this.dateModified;
	}

	@Override
	public void setDateModified(Date argDateModified) {
		this.dateModified = argDateModified;
	}

//	public String getRecordStatus() {
//		return this.recordStatus;
//	}
//
//	public void setRecordStatus(String argRecordStatus) {
//		this.recordStatus = argRecordStatus;
//	}

}
