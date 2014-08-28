package keio.university;

import java.util.List;

public class User {

	private String name;
	private List<String> emailsList;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

	public List<String> getEmailsList() {
		return this.emailsList;
	}

	public void setEmailsList(List<String> argEmailsList) {
		this.emailsList = argEmailsList;
	}

}
