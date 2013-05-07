package com.rise.common.model;

public class EducationQualification extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String university;
	private int year;
	private int month;
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

	public int getYear() {
		return this.year;
	}

	public void setYear(int argYear) {
		this.year = argYear;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int argMonth) {
		this.month = argMonth;
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
