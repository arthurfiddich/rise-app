package com.rise.common.model;

import com.rise.common.util.annotation.DesiredField;
import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "EducationQualification", mainTab = MainTab.FALSE)
public class EducationQualification extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DesiredField
	private String name;
	@DesiredField
	private String university;
	@DesiredField
	private int yearCompleted;
	@DesiredField
	private int monthCompleted;
	@DesiredField
	private int percentage;
	@DesiredField
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
