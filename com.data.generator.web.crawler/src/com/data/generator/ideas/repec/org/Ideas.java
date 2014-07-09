package com.data.generator.ideas.repec.org;

import java.util.List;

import com.data.generator.custom.annotation.Reference;
import com.data.generator.custom.annotation.Variable;

public class Ideas {

	@Variable
	private String firstName;
	@Variable
	private String middleName;
	@Variable
	private String lastName;
	@Variable
	private String suffix;
	@Reference
	private List<Affiliation> affiliationList;
	@Variable
	private List<String> emailList;

	private Ideas(IdeasBuilder argIdeasBuilder) {
		this.firstName = argIdeasBuilder.getFirstName();
		this.middleName = argIdeasBuilder.getMiddleName();
		this.lastName = argIdeasBuilder.getLastName();
		this.suffix = argIdeasBuilder.getSuffix();
		this.affiliationList = argIdeasBuilder.getAffiliationList();
		this.emailList = argIdeasBuilder.getEmailList();
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getSuffix() {
		return this.suffix;
	}

	public List<Affiliation> getAffiliationList() {
		return this.affiliationList;
	}

	public List<String> getEmailList() {
		return this.emailList;
	}

	@Override
	public String toString() {
		return "Ideas [firstName=" + this.firstName + ", middleName="
				+ this.middleName + ", lastName=" + this.lastName + ", suffix="
				+ this.suffix + ", affiliationList=" + this.affiliationList
				+ ", emailList=" + this.emailList + "]";
	}

	// Builder Class
	public static class IdeasBuilder {
		private String firstName;
		private String middleName;
		private String lastName;
		private String suffix;
		private List<Affiliation> affiliationList;
		private List<String> emailList;

		public IdeasBuilder() {
		}

		public IdeasBuilder(String argFirstName, String argMiddleName,
				String argLastName, String argSuffix) {
			super();
			this.firstName = argFirstName;
			this.middleName = argMiddleName;
			this.lastName = argLastName;
			this.suffix = argSuffix;
		}

		public List<Affiliation> getAffiliationList() {
			return this.affiliationList;
		}

		public IdeasBuilder setAffiliationList(List<Affiliation> argAffiliationList) {
			this.affiliationList = argAffiliationList;
			return this;
		}

		public String getFirstName() {
			return this.firstName;
		}

		public String getMiddleName() {
			return this.middleName;
		}

		public String getLastName() {
			return this.lastName;
		}

		public String getSuffix() {
			return this.suffix;
		}

		public Ideas build() {
			return new Ideas(this);
		}

		public void setFirstName(String argFirstName) {
			this.firstName = argFirstName;
		}

		public void setMiddleName(String argMiddleName) {
			this.middleName = argMiddleName;
		}

		public void setLastName(String argLastName) {
			this.lastName = argLastName;
		}

		public void setSuffix(String argSuffix) {
			this.suffix = argSuffix;
		}

		public List<String> getEmailList() {
			return this.emailList;
		}

		public IdeasBuilder setEmailList(List<String> argEmailList) {
			this.emailList = argEmailList;
			return this;
		}

	}
}
