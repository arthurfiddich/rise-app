package com.rise.common.model;

import java.util.Set;

import com.rise.common.util.annotation.MainTab;
import com.rise.common.util.annotation.Tab;

@Tab(tabName = "TrainingProvider", mainTab = MainTab.FALSE)
public class TrainingProvider extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String contactPersonName;
	private String name;

	private Set<TrainingProviderEmpanelment> trainingProviderEmpanelments;
	private ContactInformation contactInformation;

	public TrainingProvider() {
		super();
	}

	public String getContactPersonName() {
		return this.contactPersonName;
	}

	public void setContactPersonName(String argContactPersonName) {
		this.contactPersonName = argContactPersonName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public Set<TrainingProviderEmpanelment> getTrainingProviderEmpanelments() {
		return this.trainingProviderEmpanelments;
	}

	public void setTrainingProviderEmpanelments(
			Set<TrainingProviderEmpanelment> argTrainingProviderEmpanelments) {
		this.trainingProviderEmpanelments = argTrainingProviderEmpanelments;
	}

	public ContactInformation getContactInformation() {
		return this.contactInformation;
	}

	public void setContactInformation(ContactInformation argContactInformation) {
		this.contactInformation = argContactInformation;
	}

}
