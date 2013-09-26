package gwtDemo.client.ui.presenter;

import gwtDemo.client.event.LanguageChangedEvent;
import gwtDemo.client.service.LanguageServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;

public class HeaderPresenter {
    private final LanguageServiceAsync languageService;
    private final EventBus eventBus;
    private final Display display;

    public HeaderPresenter(LanguageServiceAsync languageService, EventBus eventBus, Display display) {
        this.languageService = languageService;
        this.eventBus = eventBus;
        this.display = display;
    }
    
    public void changeLanguage(final String language) {
        languageService.getCurrentLanguage(new AsyncCallback<String>() {
            public void onFailure(Throwable caught) {
                display.systemError();
            }

            public void onSuccess(String currentLanguage) {
                if (!currentLanguage.equals(language)) {
                    eventBus.fireEvent(new LanguageChangedEvent(language));
                }
            }
        });
    }
    
    public interface Display {
        void systemError();
    }
}
