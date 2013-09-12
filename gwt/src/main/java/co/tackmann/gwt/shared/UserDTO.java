package co.tackmann.gwt.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserDTO implements Serializable {
	private String email;
	private String name;

	public UserDTO() {
		// Make GWT happy
	}

	public UserDTO(String name, String email) {
		this();
		setName(name);
		setEmail(email);
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}
}
