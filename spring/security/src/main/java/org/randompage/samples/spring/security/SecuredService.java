package org.randompage.samples.spring.security;

public interface SecuredService {
	String denyAll();
	
	String denyAllIndirect();

	String rolesAllowed();

	String runAs();

	String permitAll();
}
