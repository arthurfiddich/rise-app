package com.rise.common.model;

import java.util.Date;

public class TrainingProviderEmpanelment extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date dateRequested;
	private Date dateApproved;
	private Date fromDate;
	private Date toDate;

	private TrainingProvider trainingProvider;
	private Program program;

	public TrainingProviderEmpanelment() {
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

	public TrainingProvider getTrainingProvider() {
		return this.trainingProvider;
	}

	public void setTrainingProvider(TrainingProvider argTrainingProvider) {
		this.trainingProvider = argTrainingProvider;
	}

	public Program getProgram() {
		return this.program;
	}

	public void setProgram(Program argProgram) {
		this.program = argProgram;
	}

}
