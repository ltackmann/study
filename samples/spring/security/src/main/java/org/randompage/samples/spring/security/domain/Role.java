package org.randompage.samples.spring.security.domain;

public enum Role implements Roles {
	ADMIN(ROLE_ADMIN), USER(ROLE_USER);
	private final String roleName;

	private Role(String roleName) {
		this.roleName = roleName;
	}

	public String roleName() {
		return roleName;
	}
}
