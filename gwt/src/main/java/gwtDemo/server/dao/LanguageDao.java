package gwtDemo.server.dao;

import java.util.Set;

import gwtDemo.shared.LanguageMessage;

public interface LanguageDao {
	LanguageMessage getMessage(String locale, String messageId);

	Set<String> getLanguages();
}
