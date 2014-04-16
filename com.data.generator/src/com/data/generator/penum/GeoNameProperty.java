package com.data.generator.penum;

public enum GeoNameProperty {

	GEO_NAME_CHILDREN_URL("GeoNameChildrenUrl");

	private String propertyName;

	private GeoNameProperty(String argPropertyName) {
		this.propertyName = argPropertyName;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String argPropertyName) {
		this.propertyName = argPropertyName;
	}

}
