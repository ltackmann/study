package gwtDemo.test.components;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;

import java.util.LinkedList;
import java.util.List;

import gwtDemo.client.components.HeaderComponent;
import gwtDemo.client.components.HeaderPresenter;
import gwtDemo.client.framework.ClientSession;
import gwtDemo.client.service.UserServiceAsync;
import gwtDemo.shared.domain.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwtmockito.GwtMockitoTestRunner;

import static gwtDemo.test.utils.AsyncCallbackTester.*;

@SuppressWarnings("unchecked")
@RunWith(GwtMockitoTestRunner.class)
public class HeaderComponentTest {
	private AppInjector injector; 
	private ClientSession clientSession;
	private HeaderComponent component;
	private HeaderPresenter presenter;
	private UserServiceAsync userService;
	
    @Before
    public void prepareMocks() {
    	// mock
    	clientSession = mock(ClientSession.class);
    	component = GWT.create(HeaderComponent.class);
    	injector = mock(AppInjector.class);
    	userService = mock(UserServiceAsync.class);
    	
    	// stub
    	when(injector.getUserService()).thenReturn(userService);
    	when(injector.getClientSession()).thenReturn(clientSession);
   
    	presenter = new HeaderPresenter(component, injector);
    }

    @Test
    @SuppressWarnings("serial")
    public void testLoginCredentialsValidation() {
		List<String[]> testData = new LinkedList<String[]>() {{ 
    		this.add(new String[] { "my@email.com", null, "invalid password" });
    		this.add(new String[] { "my@email.com", "", "invalid password" });
    		this.add(new String[] { null, "password", "invalid email" });
    		this.add(new String[] { "email", "password", "invalid email" });
    	}};
    	int passedTests = 0;
    	
    	for(String[] data : testData) {
    		prepareMocks();
    		final String email = data[0];
            final String password = data[1];
            final String error = data[2];

            presenter.handleLogin(email, password);
            
            verify(component).showError(eq(error));
            verify(userService, never()).authenticate(eq(email), eq(password), isA(AsyncCallback.class));
            passedTests++;
    	}
    	
    	assertThat(passedTests, equalTo(testData.size()));
    }

    
    @Test
    public void testLoginFailsWhenBadCredentials() {
        final String email = "non-existing-user@company.com";
        final String password = "or-bad-password";

        doAnswer(returnValue(null)).
                when(userService).authenticate(eq(email), eq(password), isA(AsyncCallback.class));

        presenter.handleLogin(email, password);
        verify(component).showError(eq("invalid credentials"));
        verify(injector, never()).getClientSession();
    }
    
    
    @Test
    public void testLoginFailsWhenBackendFails() {
        final String email = "john@doe.com";
        final String password = "secret";
        
        Throwable error = new RuntimeException("Backend failure");
        doAnswer(throwError(error)).
                when(userService).authenticate(eq(email), eq(password), isA(AsyncCallback.class));

        presenter.handleLogin(email, password);
        verify(component).showError(eq("backend failed"));
        verify(injector, never()).getClientSession();
    }

    @Test
    public void testLoginSuccess() {
        final String email = "john@doe.com";
        final String password = "secret";

        User user = mock(User.class);
        doAnswer(returnValue(user)).
                when(userService).authenticate(eq(email), eq(password), isA(AsyncCallback.class));

        presenter.handleLogin(email, password);
        verify(component, never()).showError(anyString());
    }
}
