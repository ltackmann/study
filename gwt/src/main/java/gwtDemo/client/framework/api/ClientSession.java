package gwtDemo.client.framework.api;

import com.google.web.bindery.event.shared.EventBus;

import gwtDemo.client.event.LanguageChanged;
import gwtDemo.shared.domain.User;

public class ClientSession {
	private EventBus eventBus;
	private User user;
	private String language;
	
	public ClientSession(User user, EventBus eventBus) {
		this.user = user;
		this.eventBus = eventBus;
	}

	public User getUser() {
		return user;
	}

	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
		 eventBus.fireEvent(new LanguageChanged(language));
	}

	public void setUser(User user) {
		this.user = user;
	}
}
