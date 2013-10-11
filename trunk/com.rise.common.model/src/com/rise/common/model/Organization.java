package com.rise.common.model;

import java.util.Set;

import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "Organization", mainTab = MainTab.FALSE)
public class Organization extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String dateIncorporated;
	private String type;

	private ContactInformation contactInformation;
	private Set<EmploymentExperience> employmentExperiences;

	public Organization() {
		super();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getDateIncorporated() {
		return this.dateIncorporated;
	}

	public void setDateIncorporated(String argDateIncorporated) {
		this.dateIncorporated = argDateIncorporated;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String argType) {
		this.type = argType;
	}

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}

	public Set<EmploymentExperience> getEmploymentExperiences() {
		return this.employmentExperiences;
	}

	public void setEmploymentExperiences(
			Set<EmploymentExperience> argEmploymentExperiences) {
		this.employmentExperiences = argEmploymentExperiences;
	}

}
