package com.rise.common.model;

public class CandidateTrainingBatch extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TrainingBatch trainingBatch;
	private Candidate candidate;

	public CandidateTrainingBatch() {
		super();
	}

	public TrainingBatch getTrainingBatch() {
		return this.trainingBatch;
	}

	public void setTrainingBatch(TrainingBatch argTrainingBatch) {
		this.trainingBatch = argTrainingBatch;
	}

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate argCandidate) {
		this.candidate = argCandidate;
	}

}
