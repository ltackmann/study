package co.tackmann.gwt.client.ui.presenter;

import co.tackmann.gwt.client.event.AccessGrantedEvent;
import co.tackmann.gwt.client.service.UserServiceAsync;
import co.tackmann.gwt.client.ui.validator.EmailValidator;
import co.tackmann.gwt.shared.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;

public class HeaderPresenter {
    private final LanguageServiceAsync languageService;
    private final EventBus eventBus;
    private final Display display;

    public interface Display {
        void loginFailure();

        void systemError();
    }

    public HeaderPresenter(UserServiceAsync userService, EventBus eventBus, Display display) {
        this.userService = userService;
        this.eventBus = eventBus;
        this.display = display;
    }

    public void changeLanguage(String language) {
        EmailValidator emailValidator = new EmailValidator();
        if (password == null || !emailValidator.validate(email)) {
            display.loginFailure();
            return;
        }
        
        userService.authenticate(email, password, new AsyncCallback<UserDTO>() {
            public void onFailure(Throwable caught) {
                display.systemError();
            }

            public void onSuccess(UserDTO user) {
                if (user == null) {
                    display.loginFailure();
                } else {
                    eventBus.fireEvent(new AccessGrantedEvent(user));
                }
            }
        });
    }
}
