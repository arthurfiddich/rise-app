package com.rise.common.model;

import java.util.Date;
import java.util.Set;

import com.rise.common.util.annotation.Component;
import com.rise.common.util.annotation.DesiredField;
import com.rise.common.util.annotation.FieldType.Type;
import com.rise.common.util.annotation.Reference;
import com.rise.common.util.checker.HibernateClassNameConstants;

public class Person extends BaseModel {
	/**
	 * Here I am using Component based mapping for PersonName in Person
	 * Object...
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private String title;
	// private String firstName;
	// private String middleName;
	// private String lastName;
	// private String suffix;
	@DesiredField
	private Date dateOfBirth;

	@DesiredField
	private String aadhaarNumber;

	@Reference(className = "com.rise.common.model.EducationQualification", type = Type.SET, prefix = "EDU", name = HibernateClassNameConstants.EDUCATION_QUALIFICATION, variableName = "educationQualifications")
	private Set<EducationQualification> educationQualifications;

	@Reference(className = "com.rise.common.model.EmploymentExperience", type = Type.SET, prefix = "EMP-EXP", name = HibernateClassNameConstants.EMPLOYMENT_EXPERIENCE, variableName = "employmentExperiences")
	private Set<EmploymentExperience> employmentExperiences;

	@Reference(className = "com.rise.common.model.ContactInformation", type = Type.DEFAULT, prefix = "CI", name = HibernateClassNameConstants.CONTACT_INFORMATION, variableName = "contactInformation")
	private ContactInformation contactInformation;

	@Reference(className = "com.rise.common.model.DriversLicense", type = Type.DEFAULT, prefix = "DL", name = HibernateClassNameConstants.DRIVERS_LICENSE, variableName = "driversLicense")
	private DriversLicense driversLicense;

	@Component
	// @Reference(className="com.rise.common.model.PersonName", type =
	// Type.DEFAULT)
	private PersonName personName;

	@Reference(className = "com.rise.common.model.Candidate", type = Type.DEFAULT, prefix = "CANDIDATE", name = HibernateClassNameConstants.CANDIDATE, variableName = "candidate")
	private Candidate candidate;

	@Reference(className = "com.rise.common.model.Passport", type = Type.DEFAULT, prefix = "PASSPORT", name = HibernateClassNameConstants.PASSPORT, variableName = "passport")
	private Passport passport;

	@Reference(className = "com.rise.common.model.Award", type = Type.SET, prefix = "AWARD", name = HibernateClassNameConstants.AWARD, variableName = "awards")
	private Set<Award> awards;

	public Person() {
		super();
	}

	// public String getTitle() {
	// return this.title;
	// }
	//
	// public void setTitle(String argTitle) {
	// this.title = argTitle;
	// }
	//
	// public String getFirstName() {
	// return this.firstName;
	// }
	//
	// public void setFirstName(String argFirstName) {
	// this.firstName = argFirstName;
	// }
	//
	// public String getMiddleName() {
	// return this.middleName;
	// }
	//
	// public void setMiddleName(String argMiddleName) {
	// this.middleName = argMiddleName;
	// }
	//
	// public String getLastName() {
	// return this.lastName;
	// }
	//
	// public void setLastName(String argLastName) {
	// this.lastName = argLastName;
	// }
	//
	// public String getSuffix() {
	// return this.suffix;
	// }
	//
	// public void setSuffix(String argSuffix) {
	// this.suffix = argSuffix;
	// }

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

	public Candidate getCandidate() {
		return this.candidate;
	}

	public void setCandidate(Candidate argCandidate) {
		this.candidate = argCandidate;
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

	public PersonName getPersonName() {
		return this.personName;
	}

	public void setPersonName(PersonName argPersonName) {
		this.personName = argPersonName;
	}

}
