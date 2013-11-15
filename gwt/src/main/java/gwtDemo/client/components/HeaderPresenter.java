package gwtDemo.client.components;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.ClientSession;
import gwtDemo.client.framework.ComponentPresenter;
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
    	boolean errors = false;
    	if(!emailValidator.validate(email)) {
    		component.showError("invalid email");
    		errors = true;
    	}
    	if(!passwordValidator.validate(password)) {
    		component.showError("invalid password");
    		errors = true;
    	}
    	if(!errors) { 
	    	injector.getUserService().authenticate(email, password, new AsyncCallback<User>() {
				@Override
				public void onFailure(Throwable caught) {
					component.showError("backend failed");
				}
	
				@Override
				public void onSuccess(User user) {
					if(user != null) {
						ClientSession session = injector.getClientSession();
						session.setUser(user);
					} else {
						component.showError("invalid credentials");
					}
				}
	    	});
    	}
    }
}
