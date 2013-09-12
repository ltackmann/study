package org.randompage.bookmarking.frontend.client.ui.presenter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.randompage.bookmarking.frontend.testUtils.AsyncCallbackTester.returnValue;
import static org.randompage.bookmarking.frontend.testUtils.AsyncCallbackTester.throwError;

import org.junit.Before;
import org.junit.Test;

import co.tackmann.gwt.client.event.AccessGrantedEvent;
import co.tackmann.gwt.client.resource.i18n.Messages;
import co.tackmann.gwt.client.service.UserServiceAsync;
import co.tackmann.gwt.client.ui.presenter.SignupBoxPresenter;
import co.tackmann.gwt.shared.UserDTO;

import com.google.gwt.aria.client.Role;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Test presenter used by sign up box
 *
 * @author Lars Tackmann
 * @see org.randompage.bookmarking.frontend.client.ui.presenter.SignupBoxPresenter
 * @see org.randompage.bookmarking.frontend.client.ui.SignupBox
 */
public class SignupBoxPresenterTest {
    private UserServiceAsync userService;
    private HandlerManager eventBus;
    private SignupBoxPresenter.Display signupBox;
    private SignupBoxPresenter presenter;
    private Messages messages;

    @Before
    public void prepareMocks() {
        userService = mock(UserServiceAsync.class);
        eventBus = mock(HandlerManager.class);
        signupBox = mock(SignupBoxPresenter.Display.class);
        messages = mock(Messages.class);
        presenter = new SignupBoxPresenter(userService, eventBus, signupBox, messages);
    }

    @Test
    public void testSignupFailsWhenNameIsInvalid() {
        // known illegal values
        String[] names = {null, ""};
        for (String name : names) {
            UserDTO user = mock(UserDTO.class);
            when(user.getName()).thenReturn(name);

            presenter.handleSignup(user, "password", "password");
            verify(signupBox).nameError();
            verify(userService, never()).create(eq(user), anyString(), isA(AsyncCallback.class));

            prepareMocks();
        }
    }

    @Test
    public void testSignupFailsWhenEmailIsInvalid() {
        // known illegal values
        String[] emails = {null, "not-a-valid-email"};
        for (String email : emails) {
            UserDTO user = new UserDTO("John Doe", Role.USER, email);
            final String error = "illegal email";
            when(messages.signupErrorInvalidEmail()).thenReturn(error);

            presenter.handleSignup(user, "password", "password");
            verify(signupBox).emailError(eq(error));
            verify(userService, never()).create(eq(user), anyString(), isA(AsyncCallback.class));

            prepareMocks();
        }
    }

    @Test
    public void testSignupFailsWhenPasswordIsInvalid() {
        // known illegal values
        String[] passwords = {null, "short"};
        for (String password : passwords) {
            UserDTO user = new UserDTO("John Doe", Role.USER, "john@doe.com");

            presenter.handleSignup(user, password, "password");
            verify(signupBox).passwordError();
            verify(userService, never()).create(eq(user), anyString(), isA(AsyncCallback.class));

            prepareMocks();
        }
    }

    @Test
    public void testSignupFailsWhenPasswordConfirmDoesNotMatch() {
        UserDTO user = new UserDTO("John Doe", Role.USER, "john@doe.com");

        presenter.handleSignup(user, "password1", "password2");
        verify(signupBox).confirmError();
        verify(userService, never()).create(eq(user), anyString(), isA(AsyncCallback.class));
    }

    @Test
    public void testSignupFailsWhenBackendFails() {
        UserDTO user = new UserDTO("John Doe", Role.USER, "john@doe.com");
        final String password = "password";
        final String confirmPassword = "password";
        Throwable error = new RuntimeException("Backend failure");
        doAnswer(throwError(error)).
                when(userService).create(eq(user), eq(password), isA(AsyncCallback.class));

        // verify that error is shown and success event is never fired
        presenter.handleSignup(user, password, confirmPassword);
        verify(signupBox).systemError();
        verify(eventBus, never()).fireEvent(isA(GwtEvent.class));
    }

    /**
     * Ensure correct failure condition when email is already is registered for another account
     */
    @Test
    public void testSignupFailsWhenEmailIsTaken() {
        final String email = "john@doe.com";
        final String error = "email is taken";
        when(messages.signupErrorEmailTaken(email)).thenReturn(error);

        // mock service to indicate than email is already registered
        UserDTO user = new UserDTO("John Doe", Role.USER, email);
        final String password = "password";
        final String confirmPassword = "password";
        doAnswer(returnValue(Boolean.FALSE)).
                when(userService).create(eq(user), eq(password), isA(AsyncCallback.class));

        // verify that error is shown and success event is never fired
        presenter.handleSignup(user, password, confirmPassword);
        verify(signupBox).emailError(eq(error));
        verify(eventBus, never()).fireEvent(isA(GwtEvent.class));
    }

    @Test
    public void testSignupSuccess() {
          final String email = "john@doe.com";
        final String error = "email is taken";
        when(messages.signupErrorEmailTaken(email)).thenReturn(error);

        // mock service to indicate than email is already registered
        UserDTO user = new UserDTO("John Doe", Role.USER, email);
        final String password = "password";
        final String confirmPassword = "password";
        doAnswer(returnValue(Boolean.TRUE)).
                when(userService).create(eq(user), eq(password), isA(AsyncCallback.class));

        // verify that error is shown and success event is never fired
        presenter.handleSignup(user, password, confirmPassword);
        verify(eventBus).fireEvent(isA(AccessGrantedEvent.class));
    }
}
