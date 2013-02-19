package org.randompage.bookmarking.rest;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.rest.testUtils.WebTester;
import org.randompage.bookmarking.ws.api.UserType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.randompage.bookmarking.rest.testUtils.Matchers.hasHTTPStatus;

/**
 * Test REST endpoint responsible for handling the /users URL
 *
 * @author Lars Tackmann
 * @see UsersResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public class UsersResourceTest extends WebTester {
    static class UserBuilder {
        UserType buildUser(String email) {
            UserType user = new UserType();
            user.setName("New User");
            user.setPassword("secret");
            user.setEmail(email);
            user.setTimestamp(new Date());
            return user;
        }
    }

    final UserBuilder userBuilder = new UserBuilder();

    @Inject
    private UserManager userManager;

    @Before
    public void prepareMocks() {
        reset(userManager);
    }

    /**
     * Create new user by posting it from the /users URI
     */
    @Test
    public void userCreationTest() {
        final String email = "newuser@bookmarking.com";
        UserType userType = userBuilder.buildUser(email);
        when(userManager.getUser(email)).thenReturn(null);

        // post new user
        WebResource.Builder builder = url("/users").entity(userType, MediaType.APPLICATION_XML);
        ClientResponse response = builder.post(ClientResponse.class);

        // check response location header and status code
        assertThat(response.getStatus(), hasHTTPStatus(Response.Status.CREATED));
        assertThat(response.getLocation().toString(), containsString(email));

        // check response entity
        UserType createdUser = response.getEntity(UserType.class);
        assertThat(createdUser, notNullValue());
    }

    /**
     * Fail when trying from create a user that already exists
     */
    @Test
    public void userExistsTest() {
        final String email = "existing@bookmarking.com";
        UserType userType = userBuilder.buildUser(email);
        User user = mock(User.class);
        when(userManager.getUser(email)).thenReturn(user);

        // POST user known from exists
        WebResource.Builder builder = url("/users").entity(userType, MediaType.APPLICATION_XML);
        ClientResponse response = builder.post(ClientResponse.class);

        // check response location header and status code
        assertThat(response.getStatus(), hasHTTPStatus(Response.Status.CONFLICT));
        assertThat(response.getLocation().toString(), containsString(email));
    }
}
