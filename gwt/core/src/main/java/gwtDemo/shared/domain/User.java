package gwtDemo.shared.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	private String username;
	private String password;
	private Role role;
	
	public User() {
		// make GWT happy
	}
	
	public User(Role role) {
		setRole(role);
	}
	
	public User(String username, String password, Role role) {
		setUsername(username);
		setPassword(password);
		setRole(role);
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
