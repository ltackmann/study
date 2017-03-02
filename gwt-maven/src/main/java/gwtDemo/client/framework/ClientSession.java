package gwtDemo.client.framework;

import gwtDemo.client.event.LanguageChanged;
import gwtDemo.shared.domain.User;

public class ClientSession extends AbstractGwtLogic {
	private String language;
	private User user;
	
	public ClientSession(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
		getEventBus().fireEvent(new LanguageChanged(language));
	}

	public void setUser(User user) {
		this.user = user;
	}
}
