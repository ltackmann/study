package gwtDemo.client.ui.presenter;

import gwtDemo.client.event.LanguageChanged;
import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.api.ClientSession;
import gwtDemo.client.framework.api.ComponentPresenter;
import gwtDemo.client.ui.component.HeaderComponent;

import com.google.web.bindery.event.shared.EventBus;

public class HeaderPresenter extends ComponentPresenter<HeaderComponent> {
    public HeaderPresenter(HeaderComponent component, AppInjector injector) {
    	super(component, injector);
    }
    
    public void changeLanguage(final String language) {
    	ClientSession session = injector.getClientSession();
    	if (!session.getCurrentLanguage().equals(language)) {
    		EventBus eventBus = injector.getEventBus();
    		session.setCurrentLanguage(language);
            eventBus.fireEvent(new LanguageChanged(language));
        }
    }
    
    public void handleLogin(String email, String password) {
    	// TODO share validator with backend
    	if(isNotNullOrEmpty(email) && isNotNullOrEmpty(password)) {
    		
    	}
    }
    
    private boolean isNotNullOrEmpty(String str) {
    	return str != null && !str.isEmpty();
    }
}
