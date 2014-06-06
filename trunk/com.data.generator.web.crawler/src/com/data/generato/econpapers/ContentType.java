package com.data.generato.econpapers;

public enum ContentType {

	EMAIL("Email"), NAME("Name");

	private final String contentType;

	private ContentType(String argContentType) {
		this.contentType = argContentType;
	}

	public String getContentType() {
		return this.contentType;
	}

}
