package com.rise.common.model;

import java.util.Date;

public class ProvidentFund extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pfNumber;
	private Date fromDate;

	private Candidate candidate;

	public ProvidentFund() {
		super();
	}

	public String getPfNumber() {
		return this.pfNumber;
	}

	public void setPfNumber(String argPfNumber) {
		this.pfNumber = argPfNumber;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date argFromDate) {
		this.fromDate = argFromDate;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate argCandidate) {
		this.candidate = argCandidate;
	}

}
