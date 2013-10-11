package com.rise.common.model;

import java.util.Date;
import java.util.Set;

import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "TrainingBatch", mainTab = MainTab.FALSE)
public class TrainingBatch extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int maxCapacity;
	private Date startDate;
	private Date endDate;

	private Set<CandidateTrainingBatch> candidateTrainingBatches;
	private Address address;
	private Program program;

	public TrainingBatch() {
		super();
	}

	public int getMaxCapacity() {
		return this.maxCapacity;
	}

	public void setMaxCapacity(int argMaxCapacity) {
		this.maxCapacity = argMaxCapacity;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date argStartDate) {
		this.startDate = argStartDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date argEndDate) {
		this.endDate = argEndDate;
	}

	public Set<CandidateTrainingBatch> getCandidateTrainingBatches() {
		return this.candidateTrainingBatches;
	}

	public void setCandidateTrainingBatches(
			Set<CandidateTrainingBatch> argCandidateTrainingBatches) {
		this.candidateTrainingBatches = argCandidateTrainingBatches;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address argAddress) {
		this.address = argAddress;
	}

	public Program getProgram() {
		return this.program;
	}

	public void setProgram(Program argProgram) {
		this.program = argProgram;
	}

}
