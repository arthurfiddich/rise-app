package com.rise.common.model;

import java.util.Date;

import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "Esi", mainTab = MainTab.FALSE)
public class Esi extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String esiNumber;
	private Date joinDate;
	private Date expiryDate;

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

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date argExpiryDate) {
		this.expiryDate = argExpiryDate;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate argCandidate) {
		this.candidate = argCandidate;
	}

}
