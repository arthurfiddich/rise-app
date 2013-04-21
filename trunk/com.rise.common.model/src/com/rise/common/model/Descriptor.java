package com.rise.common.model;

public class Descriptor extends BaseModel {

	private String category;
	private String name;
	private String description;

	public Descriptor() {
		super();
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String argCategory) {
		this.category = argCategory;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String argDescription) {
		this.description = argDescription;
	}
}
