package com.rise.common.model;

import java.util.Set;

public class EducationQualification extends BaseModel {

	private String name;
	private String university;
	private int year;
	private int month;
	private int percentage;
	private float gpa;

	private Set<Person> persons;

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

	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> argPersons) {
		this.persons = argPersons;
	}

}
