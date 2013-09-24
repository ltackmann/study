package gwt.server.dao;

import java.util.Set;

import gwt.shared.LanguageMessage;

public interface LanguageDao {
	LanguageMessage getMessage(String locale, String messageId);

	Set<String> getLanguages();
}
