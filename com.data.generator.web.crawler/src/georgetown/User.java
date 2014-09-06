package georgetown;

import java.util.List;

public class User {

	private final String phoneNumber;
	private final List<String> emailsList;
	private String name;

	public User(String argPhoneNumber, List<String> argEmailsList) {
		super();
		this.phoneNumber = argPhoneNumber;
		this.emailsList = argEmailsList;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public List<String> getEmailsList() {
		return this.emailsList;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String argName) {
		this.name = argName;
	}

}
