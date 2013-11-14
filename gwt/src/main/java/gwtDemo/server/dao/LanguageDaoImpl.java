package gwtDemo.server.dao;

import gwtDemo.shared.domain.LocalMessage;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class LanguageDaoImpl implements LanguageDao {
	private static final Set<String> languages = new HashSet<String>() {{
		this.add("Danish");
		this.add("English");
	}};
	
	@Override
	public Set<String> getLanguages() {
		return languages;
	}

	@Override
	public LocalMessage getMessage(String locale, String messageId) {
		return null;
	}
}
