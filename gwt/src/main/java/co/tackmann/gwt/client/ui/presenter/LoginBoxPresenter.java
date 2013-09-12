package co.tackmann.gwt.client.ui.presenter;

import co.tackmann.gwt.client.event.AccessGrantedEvent;
import co.tackmann.gwt.client.service.UserServiceAsync;
import co.tackmann.gwt.client.ui.validator.EmailValidator;
import co.tackmann.gwt.shared.UserDTO;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class LoginBoxPresenter {
    private final UserServiceAsync userService;
    private final HandlerManager eventBus;
    private final Display display;

    public interface Display {
        void loginFailure();

        void systemError();
    }

    public LoginBoxPresenter(UserServiceAsync userService, HandlerManager eventBus, Display display) {
        this.userService = userService;
        this.eventBus = eventBus;
        this.display = display;
    }

    public void handleLogin(String email, String password) {
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
