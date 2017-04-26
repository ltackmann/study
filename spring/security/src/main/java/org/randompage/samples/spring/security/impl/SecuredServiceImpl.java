package org.randompage.samples.spring.security.impl;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;

import org.randompage.samples.spring.security.SecuredService;
import org.randompage.samples.spring.security.domain.Role;
import org.springframework.stereotype.Component;

@Component
@RunAs(Role.ROLE_ADMIN)
@DeclareRoles( { Role.ROLE_USER, Role.ROLE_ADMIN })
public class SecuredServiceImpl implements SecuredService {
	// deny direct invocations
	@DenyAll
	@Override
	public String denyAll() {
		return "denyAll";
	}
	
	// indirect invocations to @DenyAll are allowed
	@Override
	public String denyAllIndirect() {
		return denyAll();
	}

	// for specified roles
	@Override
	@RolesAllowed( { Role.ROLE_ADMIN })
	public String rolesAllowed() {
		return "rolesAllowed";
	}

	// require one role, but invoke subsequent methods as the @RunAs role
	@Override
	@RolesAllowed( { Role.ROLE_USER })
	public String runAs() {
		return rolesAllowed();
	}

	// for any role (but not no role!)
	@Override
	@PermitAll
	public String permitAll() {
		return "permitAll";
	}
}