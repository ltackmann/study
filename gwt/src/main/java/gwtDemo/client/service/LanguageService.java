package gwtDemo.client.service;

import java.util.Set;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("users.rpc")
public interface LanguageService extends RemoteService {
	Set<String> getLanguages();

	String getCurrentLanguage();
}
