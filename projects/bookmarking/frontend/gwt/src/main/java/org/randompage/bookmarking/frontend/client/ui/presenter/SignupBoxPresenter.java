package org.randompage.bookmarking.frontend.client.ui.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.randompage.bookmarking.frontend.client.event.AccessGrantedEvent;
import org.randompage.bookmarking.frontend.client.resource.i18n.Messages;
import org.randompage.bookmarking.frontend.client.service.UserServiceAsync;
import org.randompage.bookmarking.frontend.client.ui.validator.EmailValidator;
import org.randompage.bookmarking.frontend.shared.UserDTO;

public class SignupBoxPresenter {
    private final UserServiceAsync userService;
    private final HandlerManager eventBus;
    private final Display display;
    private final Messages messages;

    public interface Display {
        void systemError();

        void nameError();

        void emailError(String msg);

        void passwordError();

        void confirmError();
    }

    public SignupBoxPresenter(UserServiceAsync userService, HandlerManager eventBus,
                              Display display, Messages messages) {
        this.userService = userService;
        this.eventBus = eventBus;
        this.display = display;
        this.messages = messages;
    }

    public void handleSignup(final UserDTO user, String password, String confirm) {
        EmailValidator emailValidator = new EmailValidator();

        if (user.getName() == null || user.getName().isEmpty()) {
            display.nameError();
            return;
        } else if (!emailValidator.validate(user.getEmail())) {
            display.emailError(messages.signupErrorInvalidEmail());
            return;
        } else if (password == null || !password.matches("^[a-zA-Z0-9_-]{6,16}")) {
            display.passwordError();
            return;
        } else if (!password.equals(confirm)) {
            display.confirmError();
            return;
        }

        userService.create(user, password, new AsyncCallback<Boolean>() {
            public void onFailure(Throwable caught) {
                display.systemError();
            }

            @Override
            public void onSuccess(Boolean created) {
                if (!created) {
                    String error = messages.signupErrorEmailTaken(user.getEmail());
                    display.emailError(error);
                } else {
                    eventBus.fireEvent(new AccessGrantedEvent(user));
                }
            }
        });
    }
}
