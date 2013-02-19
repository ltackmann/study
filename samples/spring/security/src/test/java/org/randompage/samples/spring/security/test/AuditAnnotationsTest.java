package org.randompage.samples.spring.security.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Arrays;

import org.randompage.samples.spring.security.SecuredService;
import org.randompage.samples.spring.security.domain.Role;
import org.randompage.samples.spring.security.test.utils.AuthUtils;
import org.randompage.samples.spring.security.test.utils.SpringTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * Test behaviour of JSR250 annotations
 * 
 * @author Lars Tackmann
 * 
 */
public class AuditAnnotationsTest extends SpringTester {
	@Autowired
	private SecuredService secured;
	@Autowired
	private AuthUtils authUtils;

	@Test(description = "should assert that roles from @DeclareRoles are the ones allowed in isUserInRole")
	public void declareRolesTest() {
		// login using role not listed in declare roles
		final String ROLE_NEW = "ROLE_NEW";
		assertThat(Arrays.asList(Role.allRoles).contains(ROLE_NEW), is(false));
		authUtils.loginAs(ROLE_NEW);
		// TODO ensure that userInRole only works with declared roles
		secured.permitAll();
		throw new AssertionError("TODO");
	}

	@Test(description = "Should assert that indirect calls to @DenyAll methods are allowed")
	public void denyAllIndirectTest() {
		assertThat(secured.denyAllIndirect(), notNullValue());
	}

	@Test(description = "Should assert that direct calls to @DenyAll methods are prohibited", expectedExceptions = { RuntimeException.class })
	public void denyAllTest() {
		authUtils.loginAs(Role.ROLE_ADMIN);
		secured.denyAll();
	}

	@Test(description = "Should assert that @PermitAll methods requires fails without a role", expectedExceptions = { RuntimeException.class })
	public void permitAllFailTest() {
		authUtils.clearAuth();
		secured.permitAll();
	}

	@Test(description = "Should assert that @PermitAll methods executes under any role")
	public void permitAllTest() {
		for (String role : Role.allRoles) {
			authUtils.loginAs(role);
			assertThat(secured.permitAll(), notNullValue());
		}
	}

	@Test(description = "Should assert that @RolesAllowed methods fails with incorrect role", expectedExceptions = { RuntimeException.class })
	public void rolesAllowedFailTest() {
		authUtils.loginAs(Role.ROLE_USER);
		secured.rolesAllowed();
	}

	@Test(description = "Should assert that @RolesAllowed methods succeeds with correct role")
	public void rolesAllowedTest() {
		authUtils.loginAs(Role.ROLE_ADMIN);
		assertThat(secured.rolesAllowed(), notNullValue());
	}

	@Test(description = "Should assert that @RunAs propagates roles on nested calls")
	public void runAsTest() {
		authUtils.loginAs(Role.ROLE_USER);
		// @RunAs specifies role for subsequent invocations within a method
		// but not for the direct method call. This method requires role USER
		// but will successfully execute subsequent method requiring role ADMIN
		assertThat(secured.runAs(), notNullValue());
	}
}
