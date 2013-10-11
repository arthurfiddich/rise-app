package com.rise.common.model;

import java.util.Set;

import com.rise.common.util.annotation.DesiredField;
import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "Candidate", mainTab = MainTab.TRUE)
public class Candidate extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DesiredField
	private String contactPersonName;

	private Set<CandidateTrainingBatch> candidateTrainingBatches;
	private Set<CandidateJobPosting> candidateJobPostings;
	private ProvidentFund providentFund;
	private Address address;
	private Person person;
	private Esi esi;

	public Candidate() {
		super();
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String argContactPersonName) {
		this.contactPersonName = argContactPersonName;
	}

	public Set<CandidateTrainingBatch> getCandidateTrainingBatches() {
		return this.candidateTrainingBatches;
	}

	public void setCandidateTrainingBatches(
			Set<CandidateTrainingBatch> argCandidateTrainingBatches) {
		this.candidateTrainingBatches = argCandidateTrainingBatches;
	}

	public Set<CandidateJobPosting> getCandidateJobPostings() {
		return this.candidateJobPostings;
	}

	public void setCandidateJobPostings(
			Set<CandidateJobPosting> argCandidateJobPostings) {
		this.candidateJobPostings = argCandidateJobPostings;
	}

	public ProvidentFund getProvidentFund() {
		return this.providentFund;
	}

	public void setProvidentFund(ProvidentFund argProvidentFund) {
		this.providentFund = argProvidentFund;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address argAddress) {
		this.address = argAddress;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person argPerson) {
		this.person = argPerson;
	}

	public Esi getEsi() {
		return this.esi;
	}

	public void setEsi(Esi argEsi) {
		this.esi = argEsi;
	}

}
