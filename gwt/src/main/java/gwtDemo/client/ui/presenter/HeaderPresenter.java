package gwtDemo.client.ui.presenter;

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

    public HeaderPresenter(UserServiceAsync userService, EventBus eventBus, ClientMessages messages, Display display) {
        this.userService = userService;
        this.eventBus = eventBus;
        this.messages = messages;
        this.display = display;
    }
    
    public void changeLanguage(final String language) {
        languageService.getCurrentLanguage(new AsyncCallback<String>() {
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
    
    public interface Display {
        void showError(String message);
    }
}
