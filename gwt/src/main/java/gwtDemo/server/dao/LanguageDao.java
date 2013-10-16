package gwtDemo.server.dao;

import java.util.Set;

import gwtDemo.shared.LocalMessage;

public interface LanguageDao {
	LocalMessage getMessage(String locale, String messageId);

	Set<String> getLanguages();
}
