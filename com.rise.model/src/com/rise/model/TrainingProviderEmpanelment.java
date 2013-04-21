package com.rise.model;

import java.util.Date;
import java.util.Set;

import com.rise.common.model.BaseModel;

public class TrainingProviderEmpanelment extends BaseModel {

	private Date dateRequested;
	private Date dateApproved;
	private Date fromDate;
	private Date toDate;

//	private Set<Program> programs;
	private Set<TrainingProvider> trainingProviders;

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

//	public Set<Program> getPrograms() {
//		return this.programs;
//	}
//
//	public void setPrograms(Set<Program> argPrograms) {
//		this.programs = argPrograms;
//	}

	public Set<TrainingProvider> getTrainingProviders() {
		return this.trainingProviders;
	}

	public void setTrainingProviders(Set<TrainingProvider> argTrainingProviders) {
		this.trainingProviders = argTrainingProviders;
	}

}
