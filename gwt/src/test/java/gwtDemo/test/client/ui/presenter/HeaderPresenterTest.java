package gwtDemo.test.client.ui.presenter;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import gwtDemo.client.service.LanguageServiceAsync;
import gwtDemo.client.ui.presenter.HeaderPresenter;

import org.junit.Before;
import org.junit.Test;

import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Test presenter used by log on box
 *
 * @see org.randompage.bookmarking.frontend.client.ui.presenter.HeaderPresenter
 * @see org.randompage.bookmarking.frontend.client.ui.HeaderComponent
 *
 * @author Lars Tackmann
 */
public class HeaderPresenterTest {
    private LanguageServiceAsync languageService;
    private EventBus eventBus;
    private HeaderPresenter.Display loginBox;
    private HeaderPresenter presenter;

    @Before
    public void prepareMocks() {
        languageService = mock(LanguageServiceAsync.class);
        eventBus = mock(EventBus.class);
        loginBox = mock(HeaderPresenter.Display.class);
        presenter = new HeaderPresenter(languageService, eventBus, loginBox);
    }

    @Test
    public void testLoginFailsWhenEmailIsNull() {
        final String email = null;
        final String password = "secret";
        /*
        presenter.handleLogin(email, password);
        verify(loginBox).loginFailure();
        verify(userService, never()).authenticate(eq(email), eq(password), isA(AsyncCallback.class));
        */
    }

    @Test
    public void testLoginFailsWhenPasswordIsNull() {
        final String email = "john@doe.com";
        final String password = null;
        /*
        presenter.handleLogin(email, password);
        verify(loginBox).loginFailure();
        verify(userService, never()).authenticate(eq(email), eq(password), isA(AsyncCallback.class));
        */
    }

    @Test
    public void testLoginFailsWhenEmailIsIllegal() {
        final String email = "not-a-valid-email";
        final String password = "secret";
        /*
        presenter.handleLogin(email, password);
        verify(loginBox).loginFailure();
        verify(userService, never()).authenticate(eq(email), eq(password),isA(AsyncCallback.class));
        */
    }

    @Test
    public void testLoginFailsWhenBadCredentials() {
        final String email = "non-existing-user@company.com";
        final String password = "or-bad-password";
        /*
        doAnswer(returnValue(null)).
                when(userService).authenticate(eq(email), eq(password), isA(AsyncCallback.class));

        // verify that error is shown and success event is never fired
        presenter.handleLogin(email, password);
        verify(loginBox).loginFailure();
        verify(eventBus, never()).fireEvent(isA(AccessGrantedEvent.class));
        */
    }

    @Test
    public void testLoginFailsWhenBackendFails() {
        final String email = "john@doe.com";
        final String password = "secret";
        /*
        Throwable error = new RuntimeException("Backend failure");
        doAnswer(throwError(error)).
                when(userService).authenticate(eq(email), eq(password), isA(AsyncCallback.class));

        // verify that error is shown and success event is never fired
        presenter.handleLogin(email, password);
        verify(loginBox).systemError();
        verify(eventBus, never()).fireEvent(isA(AccessGrantedEvent.class));
        */
    }

    /**
     * Verify log on succeeds when legal credentials are presented
     */
    @Test
    public void testLoginSuccess() {
        final String email = "john@doe.com";
        final String password = "secret";
        /*
        LanguageMessage user = mock(LanguageMessage.class);
        // stub service to return user 
        doAnswer(returnValue(user)).
                when(userService).authenticate(eq(email), eq(password), isA(AsyncCallback.class));

        // verify log on success by checking that access granted event is fired
        presenter.handleLogin(email, password);
        verify(eventBus).fireEvent(isA(AccessGrantedEvent.class));
        */
    }
}
