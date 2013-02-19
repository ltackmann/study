package org.randompage.security;

public interface ArgumentAuditor extends Auditor {
	void checkAuguments(Object... args) throws SecurityException;
}
