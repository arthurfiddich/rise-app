package com.rise.common.model;

import java.util.Date;

public class EmploymentAgencyEmpanelment extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dateRequested;
	private Date dateApproved;
	private Date fromDate;
	private Date toDate;

	private EmploymentAgency employmentAgency;
	private Program program;

	public EmploymentAgencyEmpanelment() {
		super();
	}

	public Date getDateRequested() {
		return this.dateRequested;
	}

	public void setDateRequested(Date argDateRequested) {
		this.dateRequested = argDateRequested;
	}

	public Date getDateApproved() {
		return this.dateApproved;
	}

	public void setDateApproved(Date argDateApproved) {
		this.dateApproved = argDateApproved;
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

	public EmploymentAgency getEmploymentAgency() {
		return this.employmentAgency;
	}

	public void setEmploymentAgency(EmploymentAgency argEmploymentAgency) {
		this.employmentAgency = argEmploymentAgency;
	}

	public Program getProgram() {
		return this.program;
	}

	public void setProgram(Program argProgram) {
		this.program = argProgram;
	}

}
