package gwtDemo.shared.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalMessageGroup {
	private final Map<String, LocalMessage> messages = new HashMap<String, LocalMessage>();
	private String language;
	
	public LocalMessageGroup(List<LocalMessage> localMessages) {
		for(LocalMessage message : localMessages) {
			messages.put(message.getMessageKey(), message);
			if(language == null) {
				language = message.getLanguage();
			} else {
				assert(language.equals(message.getLanguage()));
			}
		}
	}
	
	public LocalMessage getMessage(String key) {
		return messages.get(key);
	}
	
	public boolean hasMessage(String key) {
		return messages.containsKey(key);
	}
	
	public String getLanguage() {
		return language;
	}
}
