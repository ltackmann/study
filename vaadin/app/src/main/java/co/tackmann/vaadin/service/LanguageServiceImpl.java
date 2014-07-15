package co.tackmann.vaadin.service;

import java.util.HashSet;
import java.util.Set;

public class LanguageServiceImpl implements LanguageService {
	@Override
	@SuppressWarnings("serial")
	public Set<String> getLanguages() {
		return new HashSet<String>() {{
			this.add("Danish");
			this.add("English");
		}};
	}
}
