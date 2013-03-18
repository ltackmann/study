package org.randompage.samples.jaxrs.spring.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.randompage.samples.jaxrs.spring.client.UserManager;
import org.randompage.samples.jaxrs.spring.domain.User;
import org.randompage.samples.jaxrs.spring.test.utils.SpringTester;
import org.randompage.samples.jaxrs.spring.test.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserManagerTest extends SpringTester {
	private static final String username = "Aladdin";
	private static final String password = "open sesame";
	@Autowired
	private UserManager userManager;
	@Autowired
	private TestUtils testUtils;

	@BeforeClass
	protected final void beforeClass() {
		testUtils.prepareUser(username, password);
	}

	@Test
	public void createAndRetrieveUser() {
		// create user
		User user = new User("John Doe", username, password);
		userManager.createUser(user);
		assertThat(user.getId(), greaterThan(0L));
		// retrieve user
		user = userManager.getUser(username);
		assertThat(user, notNullValue());
		assertThat(user.getUsername(), is(username));
	}
}
