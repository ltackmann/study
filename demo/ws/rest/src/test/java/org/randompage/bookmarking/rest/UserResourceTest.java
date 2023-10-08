package org.randompage.bookmarking.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.randompage.bookmarking.rest.testUtils.Matchers.hasHTTPStatus;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.rest.testUtils.WebTester;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;

/**
 * Integration test targeting user handling in the UserResource
 *
 * @see UserResource
 *
 * @author Lars Tackmann
 */
public class UserResourceTest extends WebTester {
    @Inject
    private UserManager userManager;

    /**
     * Successful retrieve a existing user via the /users/{email} uri
     */
	@Test
	public void userRetrievalTest() {
        User user = mock(User.class);
        final String email = "user@bookmarking.com";
        when(userManager.getUser(email)).thenReturn(user);

		Builder builder = url("/users/" + email).accept(MediaType.APPLICATION_XML);

		// fail retrieval when missing authentication header
		ClientResponse response = builder.get(ClientResponse.class);
		assertThat(response.getStatus(), hasHTTPStatus(Status.UNAUTHORIZED));

		// fail retrieval with invalid credentials
		//setAuth(builder, username, "wrong-password");
		response = builder.get(ClientResponse.class);
		assertThat(response.getStatus(), hasHTTPStatus(Status.UNAUTHORIZED));

		// succeed with correct credentials
		//setAuth(builder, username, password);
		response = builder.get(ClientResponse.class);
		assertThat(response.getStatus(), hasHTTPStatus(Status.OK));
		String entityBody = response.getEntity(String.class);
		System.out.printf("got entity: %s\n", entityBody);
		// TODO use XPATH from test XML
	}
}
