package gwtDemo.client.components;

import gwtDemo.client.event.LanguageChanged;
import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.api.ClientSession;
import gwtDemo.client.framework.api.ComponentPresenter;
import gwtDemo.shared.domain.User;
import gwtDemo.shared.validators.EmailValidator;
import gwtDemo.shared.validators.PasswordValidator;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class HeaderPresenter extends ComponentPresenter<HeaderComponent> {
	private final EmailValidator emailValidator = new EmailValidator();
	private final PasswordValidator passwordValidator = new PasswordValidator();
	
    public HeaderPresenter(HeaderComponent component, AppInjector injector) {
    	super(component, injector);
    }
    
    public void changeLanguage(final String language) {
    	ClientSession session = injector.getClientSession();
    	if (!session.getLanguage().equals(language)) {
    		session.setLanguage(language);
        }
    }
    
    public void handleLogin(String email, String password) {
    	if(!emailValidator.validate(email)) {
    		component.showError("email is not valid");
    	}
    	if(!passwordValidator.validate(password)) {
    		component.showError("password is not valid");
    	}
    	injector.getUserService().login(email, password, new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				component.showError("login failed");
			}

			@Override
			public void onSuccess(User user) {
				ClientSession session = injector.getClientSession();
				session.setUser(user);
			}
    	});
    }
}
