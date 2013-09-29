package jaxrsDemo.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.randompage.samples.jaxrs.spring.model.UserBean;
import org.randompage.samples.jaxrs.spring.model.UserRole;
import jaxrsDemo.test.utils.TestUtils;
import jaxrsDemo.test.utils.WebRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.glassfish.jersey.client.*;

public final class UserResourceTest extends WebRunner {
	private static final String username = "Aladdin";
	private static final String password = "open sesame";
	private UserBean user;
	@Autowired
	private TestUtils testUtils;

	// TODO perhaps create XML tests in JRUBY and JSON tests here
	
	@Test
	public void createUserTest() {
		UserBean newUser = new UserBean();
		newUser.setName("Lars Tackmann");
		newUser.setUsername(username);
		newUser.setPassword("secret");
		newUser.setRole(UserRole.USER);

		// post new user
		WebResource resource = getResource("users");
		Builder builder = resource.entity(newUser, MediaType.APPLICATION_XML);
		resource.accept(MediaType.APPLICATION_XML);
		ClientResponse response = builder.post(ClientResponse.class);
		// check response
		assertThat(Status.fromStatusCode(response.getStatus()),
				equalTo(Status.CREATED));
		// TODO check location header
	}

	@Test(dependsOnMethods = { "updateUserTest" })
	public void deleteUserTest() {
		// TODO assert that authorization is required
		WebResource resource = getResource("users/" + username);
		ClientResponse response = resource.delete(ClientResponse.class);
		assertThat(Status.fromStatusCode(response.getStatus()),
				equalTo(Status.OK));
	}

	@BeforeClass
	protected final void beforeClass() {
		testUtils.prepareUser(username, password);
	}

	@Test(description = "should assert proper response from retrieval of non existing user")
	public void nonExistingUserTest() {
		WebResource resource = getResource("users/" + "not-there");
		Builder builder = resource.accept(MediaType.APPLICATION_XML);
		ClientResponse response = builder.get(ClientResponse.class);
		assertThat(Status.fromStatusCode(response.getStatus()),
				equalTo(Status.NOT_FOUND));
	}

	@Test(dependsOnMethods = { "createUserTest" })
	public void retrieveUserTest() {
		WebResource resource = getResource("users/" + username);
		Builder builder = resource.accept(MediaType.APPLICATION_XML);
		user = builder.get(UserBean.class);

		assertThat(user, notNullValue());
		assertThat(user.getUsername(), equalTo(username));
		assertThat(user.getId(), greaterThan(0L));
	}

	@SuppressWarnings("unchecked")
	@Test(dependsOnMethods = { "retrieveUserTest" })
	public void updateUserTest() {
		final String oldName = user.getName();
		final String newName = "djinni";
		user.setName(newName);

		// TODO split into multiple tests
		// TODO see if we can get rid of global user and instead use a
		// dataprovider (test as data provider ?)

		// fail updating user without authorization
		WebResource resource = getResource("users/" + username);
		Builder builder = resource.entity(user, MediaType.APPLICATION_XML);
		ClientResponse response = builder.put(ClientResponse.class);
		assertThat(Status.fromStatusCode(response.getStatus()),
				equalTo(Status.UNAUTHORIZED));

		// succeed with proper headers
		testUtils.setAuth(builder, username, password);
		response = builder.put(ClientResponse.class);
		assertThat(Status.fromStatusCode(response.getStatus()),
				equalTo(Status.CREATED));

		// check updated user
		retrieveUserTest();
		assertThat(user.getName(), allOf(is(newName), not(equalTo(oldName))));
	}
}
