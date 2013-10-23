package gwtDemo.client.ui.presenter;

import gwtDemo.client.AppInjector;
import gwtDemo.client.event.LanguageChangedEvent;
import gwtDemo.client.resource.i18n.ClientMessages;
import gwtDemo.client.service.UserServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;

public class HeaderPresenter {
    private final UserServiceAsync userService;
    private final EventBus eventBus;
    private final Display display;
    private final ClientMessages messages;

    public HeaderPresenter(AppInjector injector, Display display) {
        this.userService = injector.getUserService();
        this.eventBus = injector.getEventBus();
        this.messages = injector.getClientMessages();
        this.display = display;
    }
    
    public void changeLanguage(final String language) {
        languageServiceClient.getCurrentLanguage(new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                display.showError(messages.systemError());
            }

            public void onSuccess(String currentLanguage) {
                if (!currentLanguage.equals(language)) {
                    eventBus.fireEvent(new LanguageChangedEvent(language));
                }
            }
        });
    }
    
    public void handleLogin(String email, String password) {
    	// TODO validate email
    	// TODO share validator with backend
    }
    
    public interface Display {
        void showError(String message);
    }
}
