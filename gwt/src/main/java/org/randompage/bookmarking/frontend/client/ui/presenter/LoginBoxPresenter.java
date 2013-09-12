package org.randompage.bookmarking.frontend.client.ui.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.randompage.bookmarking.frontend.client.event.AccessGrantedEvent;
import org.randompage.bookmarking.frontend.client.service.UserServiceAsync;
import org.randompage.bookmarking.frontend.client.ui.validator.EmailValidator;
import org.randompage.bookmarking.frontend.shared.UserDTO;

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
