package com.data.generator.ideas.repec.org;

import java.util.List;

public class Affiliation {

	private String affiliation;
	private String location;
	private String homePage;
	private String phone;
	private String fax;
	private String postal;

	public Affiliation() {
	}

	public String getAffiliation() {
		return this.affiliation;
	}

	public void setAffiliation(String argAffiliation) {
		this.affiliation = argAffiliation;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String argLocation) {
		this.location = argLocation;
	}

	public String getHomePage() {
		return this.homePage;
	}

	public void setHomePage(String argHomePage) {
		this.homePage = argHomePage;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String argPhone) {
		this.phone = argPhone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String argFax) {
		this.fax = argFax;
	}

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String argPostal) {
		this.postal = argPostal;
	}

	@Override
	public String toString() {
		return "Affiliation [affiliation=" + this.affiliation + ", location="
				+ this.location + ", homePage=" + this.homePage + ", phone="
				+ this.phone + ", fax=" + this.fax + ", postal=" + this.postal
				+ "]";
	}

}
