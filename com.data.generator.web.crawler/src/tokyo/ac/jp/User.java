package tokyo.ac.jp;

import java.util.List;

public class User {

	public final String name;
	public final List<String> emailsList;

	public User(String argName, List<String> argEmailsList) {
		super();
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
