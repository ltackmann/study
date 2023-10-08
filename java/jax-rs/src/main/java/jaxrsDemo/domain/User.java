package jaxrsDemo.domain;

import org.apache.commons.codec.digest.DigestUtils;

public class User {
	private long id;

	private String username;

	private String password;

	private String name;

	private boolean enabled;

	private Role role;

	public User() {
		setRole(Role.USER);
		setEnabled(true);
	}

	public User(String name, String username, String password) {
		this();
		setName(name);
		setUsername(username);
		setPassword(password);
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = DigestUtils.md5(password).toString();
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
