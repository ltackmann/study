package org.randompage.samples.spring.security.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.annotation.security.PermitAll;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.randompage.samples.spring.security.test.utils.AuthUtils;
import org.randompage.samples.spring.security.test.utils.SpringTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class AuditInterceptorTest extends SpringTester {
	@Component
	static class Audit {
		@PermitAll
		public String guarded() {
			return "access granted";
		}
	}
	@Autowired
	private AuthUtils authUtils;

	@Autowired
	private Audit audit;
	
	@Before
	public final void beforeMethod() {
		authUtils.clearAuth();
	}
	
	// assert that access is granted and principal is available
	@Test
	public void allowedToAccessTest() {
		final String username = "john@doe.com";
		authUtils.login(username, "secret");
		assertThat(audit.guarded(), notNullValue());
		// check that interceptor was actually invoked
		//FIXME assertThat(AuditInterceptor.methodName, is("guarded"));
		//FIXME assertThat(AuditInterceptor.principal, is(username));
	}
	
	// assert that access is denied if no login is performed
	@Test
	public void noLoginTest() {
		Assert.fail("TODO implement");
		assertThat(audit.guarded(), nullValue());
	}
	
	// assert that access is denied if not granted the correct rights
	@Test
	public void grantsTest() {
		Assert.fail("TODO implement");
	}
	
	// assert that all methods are checked on types annotated for auditing
	@Test
	public void typesTest() {
		Assert.fail("TODO implement");
	}
}
