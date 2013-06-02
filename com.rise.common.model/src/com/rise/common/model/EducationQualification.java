package com.rise.common.model;

public class EducationQualification extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String university;
	private int yearCompleted;
	private int monthCompleted;
	private int percentage;
	private float gpa;

	private Person person;

	public EducationQualification() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getUniversity() {
		return this.university;
	}

	public void setUniversity(String argUniversity) {
		this.university = argUniversity;
	}

	public int getYearCompleted() {
		return this.yearCompleted;
	}

	public void setYearCompleted(int argYearCompleted) {
		this.yearCompleted = argYearCompleted;
	}

	public int getMonthCompleted() {
		return this.monthCompleted;
	}

	public void setMonthCompleted(int argMonthCompleted) {
		this.monthCompleted = argMonthCompleted;
	}

	public int getPercentage() {
		return this.percentage;
	}

	public void setPercentage(int argPercentage) {
		this.percentage = argPercentage;
	}

	public float getGpa() {
		return this.gpa;
	}

	public void setGpa(float argGpa) {
		this.gpa = argGpa;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person argPerson) {
		this.person = argPerson;
	}

}
