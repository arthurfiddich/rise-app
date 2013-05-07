package com.rise.common.model;

import java.util.Date;
import java.util.Set;

public class Person extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	private Date dateOfBirth;
	private String aadhaarNumber;

	private Set<EducationQualification> educationQualifications;
	private Set<EmploymentExperience> employmentExperiences;
	private ContactInformation contactInformation;
	private DriversLicense driversLicense;
	private Passport passport;
	private Set<Award> awards;

	public Person() {
		super();
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String argTitle) {
		this.title = argTitle;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String argFirstName) {
		this.firstName = argFirstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String argMiddleName) {
		this.middleName = argMiddleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String argLastName) {
		this.lastName = argLastName;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public void setSuffix(String argSuffix) {
		this.suffix = argSuffix;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date argDateOfBirth) {
		this.dateOfBirth = argDateOfBirth;
	}

	public String getAadhaarNumber() {
		return this.aadhaarNumber;
	}

	public void setAadhaarNumber(String argAadhaarNumber) {
		this.aadhaarNumber = argAadhaarNumber;
	}

	public Set<EducationQualification> getEducationQualifications() {
		return this.educationQualifications;
	}

	public void setEducationQualifications(
			Set<EducationQualification> argEducationQualifications) {
		this.educationQualifications = argEducationQualifications;
	}

	public Set<EmploymentExperience> getEmploymentExperiences() {
		return this.employmentExperiences;
	}

	public void setEmploymentExperiences(
			Set<EmploymentExperience> argEmploymentExperiences) {
		this.employmentExperiences = argEmploymentExperiences;
	}

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}

	public DriversLicense getDriversLicense() {
		return this.driversLicense;
	}

	public void setDriversLicense(DriversLicense argDriversLicense) {
		this.driversLicense = argDriversLicense;
	}

	public Passport getPassport() {
		return this.passport;
	}

	public void setPassport(Passport argPassport) {
		this.passport = argPassport;
	}

	public Set<Award> getAwards() {
		return this.awards;
	}

	public void setAwards(Set<Award> argAwards) {
		this.awards = argAwards;
	}

}
