package com.data.generator.ssrn;

import java.util.List;

public class User {

	private final String name;
	private final List<String> emailsList;

	private User(String argName, List<String> argEmailsList) {
		this.name = argName;
		this.emailsList = argEmailsList;
	}

	public String getName() {
		return this.name;
	}

	public List<String> getEmailsList() {
		return this.emailsList;
	}

}
