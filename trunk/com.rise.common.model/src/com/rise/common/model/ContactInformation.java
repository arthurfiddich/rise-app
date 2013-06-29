package com.rise.common.model;

public class ContactInformation extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email1;
	private String email2;
	private String email3;
	private String phone1;
	private String phone2;
	private String phone3;
	private String mobile1;
	private String mobile2;
	private String mobile3;
	private String website;

	private TrainingProvider trainingProvider;
	private EmploymentAgency employmentAgency;
	private Organization organization;
	private Address primaryAddress;
	private Department department;
	private Ministry ministry;
	private Program program;
	private Person person;
	private State state;

	public ContactInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail1() {
		return this.email1;
	}

	public void setEmail1(String argEmail1) {
		this.email1 = argEmail1;
	}

	public String getEmail2() {
		return this.email2;
	}

	public void setEmail2(String argEmail2) {
		this.email2 = argEmail2;
	}

	public String getEmail3() {
		return this.email3;
	}

	public void setEmail3(String argEmail3) {
		this.email3 = argEmail3;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String argPhone1) {
		this.phone1 = argPhone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String argPhone2) {
		this.phone2 = argPhone2;
	}

	public String getPhone3() {
		return this.phone3;
	}

	public void setPhone3(String argPhone3) {
		this.phone3 = argPhone3;
	}

	public String getMobile1() {
		return this.mobile1;
	}

	public void setMobile1(String argMobile1) {
		this.mobile1 = argMobile1;
	}

	public String getMobile2() {
		return this.mobile2;
	}

	public void setMobile2(String argMobile2) {
		this.mobile2 = argMobile2;
	}

	public String getMobile3() {
		return this.mobile3;
	}

	public void setMobile3(String argMobile3) {
		this.mobile3 = argMobile3;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String argWebsite) {
		this.website = argWebsite;
	}

	public TrainingProvider getTrainingProvider() {
		return this.trainingProvider;
	}

	public void setTrainingProvider(TrainingProvider argTrainingProvider) {
		this.trainingProvider = argTrainingProvider;
	}

	public EmploymentAgency getEmploymentAgency() {
		return this.employmentAgency;
	}

	public void setEmploymentAgency(EmploymentAgency argEmploymentAgency) {
		this.employmentAgency = argEmploymentAgency;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization argOrganization) {
		this.organization = argOrganization;
	}

	public Address getPrimaryAddress() {
		return this.primaryAddress;
	}

	public void setPrimaryAddress(Address argPrimaryAddress) {
		this.primaryAddress = argPrimaryAddress;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department argDepartment) {
		this.department = argDepartment;
	}

	public Ministry getMinistry() {
		return this.ministry;
	}

	public void setMinistry(Ministry argMinistry) {
		this.ministry = argMinistry;
	}

	public Program getProgram() {
		return this.program;
	}

	public void setProgram(Program argProgram) {
		this.program = argProgram;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person argPerson) {
		this.person = argPerson;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State argState) {
		this.state = argState;
	}

	@Override
	public String toString() {
		return "ContactInformation [email1=" + this.email1 + ", email2="
				+ this.email2 + ", email3=" + this.email3 + ", phone1="
				+ this.phone1 + ", phone2=" + this.phone2 + ", phone3="
				+ this.phone3 + ", mobile1=" + this.mobile1 + ", mobile2="
				+ this.mobile2 + ", mobile3=" + this.mobile3 + ", website="
				+ this.website + ", primaryAddress=" + this.primaryAddress
				+ "]";
	}

}
