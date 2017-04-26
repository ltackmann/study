package org.randompage.samples.spring.security.domain;

interface Roles {
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	public static String[] allRoles = { ROLE_ADMIN, ROLE_USER };
}
