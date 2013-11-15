package gwtDemo.shared.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

@SuppressWarnings("serial")
public class LocalMessageGroup implements Serializable {
	private final Map<String, LocalMessage> messages = new HashMap<String, LocalMessage>();
	private String language;
	
	public LocalMessageGroup() {
		// make GWT happy
	}
	
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
	
	public static LocalMessageGroup fromJsonObject(JSONObject jsonObject) {
		List<LocalMessage> messages = new LinkedList<LocalMessage>();
		JSONArray jsonArray = jsonObject.get("messages").isArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonMessage = jsonArray.get(i).isObject();
			messages.add(LocalMessage.fromJsonObject(jsonMessage));
		}
		return new LocalMessageGroup(messages);
	}
}
