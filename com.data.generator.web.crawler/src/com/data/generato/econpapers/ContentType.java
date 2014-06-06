package com.data.generato.econpapers;

public enum ContentType {

	NAME("Name"), EMAIL("Email");

	private final String contentType;

	private ContentType(String argContentType) {
		this.contentType = argContentType;
	}

	public String getContentType() {
		return this.contentType;
	}

}
