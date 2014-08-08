package com.data.generator.ump.crawler;

public class User {

	private String name;
	private String email;

	public User(String argName, String argEmail) {
		super();
		this.name = argName;
		this.email = argEmail;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String argEmail) {
		this.email = argEmail;
	}

}
