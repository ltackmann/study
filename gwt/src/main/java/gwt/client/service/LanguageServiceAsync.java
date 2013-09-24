package gwt.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LanguageServiceAsync {
	void getLanguages(AsyncCallback<Set<String>> callback);

	void getDefaultLanguage(AsyncCallback<String> callback);
}
