package njubs.nju.edu.cn;

import java.util.List;

public class User {

	private final String name;
	private final List<String> emailsList;
	private final List<String> phonesList;

	public User(String argName, List<String> argEmailsList,
			List<String> argPhonesList) {
		super();
		this.name = argName;
		this.emailsList = argEmailsList;
		this.phonesList = argPhonesList;
	}

	public String getName() {
		return this.name;
	}

	public List<String> getEmailsList() {
		return this.emailsList;
	}

	public List<String> getPhonesList() {
		return this.phonesList;
	}

}
