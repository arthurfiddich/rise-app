package www.econ.kobe.u.ac.jp;

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

	public String getEmail() {
		return this.email;
	}

}
