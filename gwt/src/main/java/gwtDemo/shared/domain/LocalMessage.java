package gwtDemo.shared.domain;

import com.google.gwt.json.client.JSONObject;

public class LocalMessage {
	private final String messageKey;
	private String text;
	private String language;
	
	public static LocalMessage fromJsonObject(JSONObject jsonObject) {
		final String messageKey = jsonObject.get("key").isString().stringValue();
		final String messageText = jsonObject.get("text").isString().stringValue();
		final String messageLanguage = jsonObject.get("language").isString().stringValue();

		LocalMessage localMessage = new LocalMessage(messageKey);
		localMessage.setLanguage(messageLanguage);
		localMessage.setText(messageText);
		return localMessage;
	}

	public LocalMessage(String messageKey) {
		this.messageKey = messageKey;
	}
	
	public String getMessageKey() {
		return messageKey;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
}
