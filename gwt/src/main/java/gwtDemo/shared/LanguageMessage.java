package gwtDemo.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LanguageMessage implements Serializable {
	private String message;
	private String language;

	public LanguageMessage() {
		// Make GWT happy
	}

	public LanguageMessage(String message, String language) {
		setMessage(message);
		setLanguage(language);
	}

	public String getMessage() {
		return message;
	}

	public String getLanguage() {
		return language;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
