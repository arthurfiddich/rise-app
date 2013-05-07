package com.rise.common.model;

import java.util.Date;

public class Esi extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String esiNumber;
	private Date joinDate;

	private Candidate candidate;

	public Esi() {
		super();
	}

	public String getEsiNumber() {
		return this.esiNumber;
	}

	public void setEsiNumber(String argEsiNumber) {
		this.esiNumber = argEsiNumber;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date argJoinDate) {
		this.joinDate = argJoinDate;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate argCandidate) {
		this.candidate = argCandidate;
	}

}
