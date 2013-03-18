package org.randompage.bookmarking.frontend.action;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.frontend.helper.ViewUtils;
import org.randompage.bookmarking.frontend.model.Identity;

/**
 * Test action responsible for handling logins
 *
 * @see org.randompage.bookmarking.frontend.action.LoginAction
 */
public class LoginActionTest {
    private static final String email = "john@doe.com";
    private static final String password = "secret";
    private UserManager userManager;
    private LoginAction loginAction;
    private Map<String, Object> sessionMap;
    private ViewUtils viewUtils;
    private User user;

    // Verify that login fails
    private void assertNoLogin() {
        assertThat(loginAction.login(), nullValue());
        // verification
        verify(userManager).getUser(email);
        verify(viewUtils).addGlobalMessage(anyString(),
                eq(FacesMessage.SEVERITY_FATAL));
        verify(user, never()).setLastLogin(isA(Date.class));
        verify(sessionMap, never()).put(eq("user"), anyObject());
    }

    @Before
    public void setUpBeforeEachTest() {
        userManager = mock(UserManager.class);
        sessionMap = mock(Map.class);
        viewUtils = mock(ViewUtils.class);
        user = mock(User.class);
        Identity identity = new Identity(email, password);
        // stubbing
        loginAction = new LoginAction();
        loginAction.setIdentity(identity);
        loginAction.setSessionMap(sessionMap);
        loginAction.setUserManager(userManager);
        loginAction.setViewUtils(viewUtils);
    }

    /**
     * Test login is successful when supplying correct credentials
     */
    @Test
    public void loginTest() {
        // mocking
        when(user.getPassword()).thenReturn(DigestUtils.md5Hex(password));
        when(userManager.getUser(email)).thenReturn(user);
        // test
        assertThat(loginAction.login(), equalTo("login"));
        // verify that login happened with no error and that user was put in session
        verify(userManager).getUser(email);
        verify(viewUtils, never()).addGlobalMessage(anyString(), isA(FacesMessage.Severity.class));
        verify(sessionMap).put("user", user);
    }

    /**
     * Test login fails when user supplies wrong password
     */
    @Test
    public void loginWrongPasswordTest() {
        // mocking
        when(user.getPassword()).thenReturn("illegal-password");
        when(userManager.getUser(email)).thenReturn(user);
        // test
        assertNoLogin();
        // verify that user password is tested
        verify(user).getPassword();
    }

    /**
     * Test login fails when username does not exists
     */
    @Test
    public void loginWrongUsernameTest() {
        // mocking
        when(userManager.getUser(email)).thenReturn(null);
        // test
        assertNoLogin();
        // verify that as user did not exists, his password should never be retrieved
        verify(user, never()).getPassword();
    }
}
