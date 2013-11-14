package gwtDemo.client.framework.api;

import gwtDemo.shared.domain.User;

public class ClientSession {
	private final User currentUser;
	private String currentLanguage;
	
	public ClientSession(User sessionUser) {
		this.currentUser = sessionUser;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public String getCurrentLanguage() {
		return currentLanguage;
	}
	
	public void setCurrentLanguage(String language) {
		this.currentLanguage = language;
	}
}
