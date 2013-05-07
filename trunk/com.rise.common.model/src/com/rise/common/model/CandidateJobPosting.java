package com.rise.common.model;

import java.util.Date;

public class CandidateJobPosting extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dateApplied;
	private boolean status;

	private Candidate candidate;
	private JobPosting jobPosting;

	public CandidateJobPosting() {
		super();
	}

	public Date getDateApplied() {
		return this.dateApplied;
	}

	public void setDateApplied(Date argDateApplied) {
		this.dateApplied = argDateApplied;
	}

	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean argStatus) {
		this.status = argStatus;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate argCandidate) {
		this.candidate = argCandidate;
	}

	public JobPosting getJobPosting() {
		return this.jobPosting;
	}

	public void setJobPosting(JobPosting argJobPosting) {
		this.jobPosting = argJobPosting;
	}

}
