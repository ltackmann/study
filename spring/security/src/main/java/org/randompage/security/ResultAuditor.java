package org.randompage.security;

public interface ResultAuditor extends Auditor {
	void checkResult(Object object) throws SecurityException;
}
