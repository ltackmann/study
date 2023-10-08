package org.randompage.bookmarking.frontend.action;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import javax.faces.application.FacesMessage.Severity;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.frontend.helper.ViewUtils;

/**
 * Test action responsible for handling user sign up
 *
 * @see org.randompage.bookmarking.frontend.action.SignupAction
 */
public class SignupActionTest {
    private SignupAction signupAction;
    private UserManager userManager;
    private User user;
    private ViewUtils viewUtils;

    @Before
    public void setUpBeforeEachTest() {
        // mocking
        userManager = mock(UserManager.class);
        user = mock(User.class);
        viewUtils = mock(ViewUtils.class);
        // stubbing
        signupAction = new SignupAction();
        signupAction.setUserManager(userManager);
        signupAction.setUser(user);
        signupAction.setViewUtils(viewUtils);
    }

    /**
     * Successfully sign a new user up
     */
    @Test
    public void signupTest() {
        // mocking
        when(userManager.getUser(anyString())).thenReturn(null);
        // test
        assertThat(signupAction.signup(), equalTo("created"));
        // verification
        verify(userManager).getUser(anyString());
        verify(userManager).createUser(user);
        verify(user).getEmail();
        verify(viewUtils, never()).addMessage(anyString(), isA(Severity.class), anyString());
    }

    /**
     * Fail to sign up a new user, when the users email is already in use
     */
    @Test
    public void signupFailExistingUserTest() {
        final String email = "existing-user";
        // mocking
        when(user.getEmail()).thenReturn(email);
        when(userManager.getUser(email)).thenReturn(user);
        // test
        assertThat(signupAction.signup(), equalTo("user-exists"));
        // verification
        verify(userManager).getUser(email);
        verify(userManager, never()).createUser(user);
        verify(user).getEmail();
        verify(viewUtils).addMessage(anyString(), isA(Severity.class), anyString());
    }
}
