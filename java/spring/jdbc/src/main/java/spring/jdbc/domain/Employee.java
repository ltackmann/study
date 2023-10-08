package spring.jdbc.domain;

/**
 * @author Lars Tackmann
 */
public class Employee {
	private String username;

	public Employee() {
	}

	public Employee(String username) {
		this();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
