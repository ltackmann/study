package gwtDemo.server.dao;

import java.util.Set;

import gwtDemo.shared.domain.LocalMessage;

public interface Dao {
	LocalMessage getMessage(String locale, String messageId);

	Set<String> getLanguages();
}
