package org.randompage.samples.spring.security.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import javax.annotation.security.PermitAll;

import org.randompage.samples.spring.security.test.utils.AuthUtils;
import org.randompage.samples.spring.security.test.utils.SpringTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
	
	@BeforeMethod
	public final void beforeMethod() {
		authUtils.clearAuth();
	}
	
	@Test(description = "should assert that access is granted and principal is avalible")
	public void allowedToAccessTest() {
		final String username = "john@doe.com";
		authUtils.login(username, "secret");
		assertThat(audit.guarded(), notNullValue());
		// check that interceptor was actually invoked
		//FIXME assertThat(AuditInterceptor.methodName, is("guarded"));
		//FIXME assertThat(AuditInterceptor.principal, is(username));
	}
	
	@Test(description = "should assert that access is denied if no login is performed")
	public void noLoginTest() {
		
	}
	
	@Test(description = "should assert that access is denied if not granted the correct rights")
	public void grantsTest() {
		
	}
	
	@Test(description = "should assert that all methods are checked on types annotated for auditing")
	public void typesTest() {
		
	}
}
