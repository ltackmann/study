package org.randompage.samples.spring.security.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.randompage.samples.spring.security.SecuredService;
import org.randompage.samples.spring.security.domain.Role;
import org.randompage.samples.spring.security.test.utils.AuthUtils;
import org.randompage.samples.spring.security.test.utils.SpringTester;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test behaviour of JSR250 annotations
 * 
 * @author Lars Tackmann
 * 
 */
public class AuditAnnotationsTest extends SpringTester {
	@Rule
    public ExpectedException thrown= ExpectedException.none();
	@Autowired
	private SecuredService secured;
	@Autowired
	private AuthUtils authUtils;

	// assert that roles from @DeclareRoles are the ones allowed in isUserInRole
	@Test
	public void declareRolesTest() {
		// login using role not listed in declare roles
		final String ROLE_NEW = "ROLE_NEW";
		assertThat(Arrays.asList(Role.allRoles).contains(ROLE_NEW), is(false));
		authUtils.loginAs(ROLE_NEW);
		// TODO ensure that userInRole only works with declared roles
		secured.permitAll();
		throw new AssertionError("TODO");
	}

	// assert that indirect calls to @DenyAll methods are allowed
	@Test
	public void denyAllIndirectTest() {
		assertThat(secured.denyAllIndirect(), notNullValue());
	}

	// assert that direct calls to @DenyAll methods are prohibited
	@Test
	public void denyAllTest() {
		thrown.expect(RuntimeException.class);
		
		authUtils.loginAs(Role.ROLE_ADMIN);
		secured.denyAll();
	}

	// assert that @PermitAll methods requires fails without a role
	@Test
	public void permitAllFailTest() {
		thrown.expect(RuntimeException.class);	
		
		authUtils.clearAuth();
		secured.permitAll();
	}

	// assert that @PermitAll methods executes under any role
	@Test
	public void permitAllTest() {
		for (String role : Role.allRoles) {
			authUtils.loginAs(role);
			assertThat(secured.permitAll(), notNullValue());
		}
	}

	// assert that @RolesAllowed methods fails with incorrect role
	@Test
	public void rolesAllowedFailTest() {
		thrown.expect(RuntimeException.class);
		
		authUtils.loginAs(Role.ROLE_USER);
		secured.rolesAllowed();
	}

	// assert that @RolesAllowed methods succeeds with correct role
	@Test
	public void rolesAllowedTest() {
		authUtils.loginAs(Role.ROLE_ADMIN);
		assertThat(secured.rolesAllowed(), notNullValue());
	}

	// assert that @RunAs propagates roles on nested calls
	@Test
	public void runAsTest() {
		authUtils.loginAs(Role.ROLE_USER);
		// @RunAs specifies role for subsequent invocations within a method
		// but not for the direct method call. This method requires role USER
		// but will successfully execute subsequent method requiring role ADMIN
		assertThat(secured.runAs(), notNullValue());
	}
}
