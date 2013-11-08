package gwtDemo.client.framework.api;

import gwtDemo.shared.User;

public class ClientSession {
	private final User currentUser;
	
	public ClientSession(User sessionUser) {
		this.currentUser = sessionUser;
	}

	public User getCurrentUser() {
		return currentUser;
	}
}
