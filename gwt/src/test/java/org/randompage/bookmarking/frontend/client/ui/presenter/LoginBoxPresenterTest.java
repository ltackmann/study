package org.randompage.bookmarking.frontend.client.ui.presenter;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.randompage.bookmarking.frontend.testUtils.AsyncCallbackTester.returnValue;
import static org.randompage.bookmarking.frontend.testUtils.AsyncCallbackTester.throwError;

import org.junit.Before;
import org.junit.Test;

import co.tackmann.gwt.client.event.AccessGrantedEvent;
import co.tackmann.gwt.client.service.UserServiceAsync;
import co.tackmann.gwt.client.ui.presenter.LoginBoxPresenter;
import co.tackmann.gwt.shared.UserDTO;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Test presenter used by log on box
 *
 * @see org.randompage.bookmarking.frontend.client.ui.presenter.LoginBoxPresenter
 * @see org.randompage.bookmarking.frontend.client.ui.LoginBox
 *
 * @author Lars Tackmann
 */
public class LoginBoxPresenterTest {
    private UserServiceAsync userService;
    private HandlerManager eventBus;
    private LoginBoxPresenter.Display loginBox;
    private LoginBoxPresenter presenter;

    @Before
    public void prepareMocks() {
        userService = mock(UserServiceAsync.class);
        eventBus = mock(HandlerManager.class);
        loginBox = mock(LoginBoxPresenter.Display.class);
        presenter = new LoginBoxPresenter(userService, eventBus, loginBox);
    }

    @Test
    public void testLoginFailsWhenEmailIsNull() {
        final String email = null;
        final String password = "secret";

        presenter.handleLogin(email, password);
        verify(loginBox).loginFailure();
        verify(userService, never()).authenticate(eq(email), eq(password), isA(AsyncCallback.class));
    }

    @Test
    public void testLoginFailsWhenPasswordIsNull() {
        final String email = "john@doe.com";
        final String password = null;

        presenter.handleLogin(email, password);
        verify(loginBox).loginFailure();
        verify(userService, never()).authenticate(eq(email), eq(password), isA(AsyncCallback.class));
    }

    @Test
    public void testLoginFailsWhenEmailIsIllegal() {
        final String email = "not-a-valid-email";
        final String password = "secret";

        presenter.handleLogin(email, password);
        verify(loginBox).loginFailure();
        verify(userService, never()).authenticate(eq(email), eq(password),isA(AsyncCallback.class));
    }

    @Test
    public void testLoginFailsWhenBadCredentials() {
        final String email = "non-existing-user@company.com";
        final String password = "or-bad-password";
        doAnswer(returnValue(null)).
                when(userService).authenticate(eq(email), eq(password), isA(AsyncCallback.class));

        // verify that error is shown and success event is never fired
        presenter.handleLogin(email, password);
        verify(loginBox).loginFailure();
        verify(eventBus, never()).fireEvent(isA(AccessGrantedEvent.class));
    }

    @Test
    public void testLoginFailsWhenBackendFails() {
        final String email = "john@doe.com";
        final String password = "secret";
        Throwable error = new RuntimeException("Backend failure");
        doAnswer(throwError(error)).
                when(userService).authenticate(eq(email), eq(password), isA(AsyncCallback.class));

        // verify that error is shown and success event is never fired
        presenter.handleLogin(email, password);
        verify(loginBox).systemError();
        verify(eventBus, never()).fireEvent(isA(AccessGrantedEvent.class));
    }

    /**
     * Verify log on succeeds when legal credentials are presented
     */
    @Test
    public void testLoginSuccess() {
        final String email = "john@doe.com";
        final String password = "secret";
        UserDTO user = mock(UserDTO.class);
        // stub service to return user 
        doAnswer(returnValue(user)).
                when(userService).authenticate(eq(email), eq(password), isA(AsyncCallback.class));

        // verify log on success by checking that access granted event is fired
        presenter.handleLogin(email, password);
        verify(eventBus).fireEvent(isA(AccessGrantedEvent.class));
    }
}
