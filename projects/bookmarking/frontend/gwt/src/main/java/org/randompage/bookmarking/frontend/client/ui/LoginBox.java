package org.randompage.bookmarking.frontend.client.ui;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.ui.*;
import org.randompage.bookmarking.frontend.client.EventBus;
import org.randompage.bookmarking.frontend.client.ui.presenter.LoginBoxPresenter;
import org.randompage.bookmarking.frontend.client.resource.i18n.Messages;
import org.randompage.bookmarking.frontend.client.service.UserService;
import org.randompage.bookmarking.frontend.client.service.UserServiceAsync;

public class LoginBox extends Composite implements LoginBoxPresenter.Display {
    private static LoginBoxUiBinder uiBinder = GWT.create(LoginBoxUiBinder.class);

    @UiTemplate("loginBox.ui.xml")
    interface LoginBoxUiBinder extends UiBinder<HTMLPanel, LoginBox> {
    }
    private final LoginBoxPresenter presenter;
    private final Messages messages;

    @UiField
    Label alert;
    @UiField
    TextBox emailBox;
    @UiField
    PasswordTextBox passwordBox;

    public LoginBox() {
        initWidget(uiBinder.createAndBindUi(this));
        
        messages = GWT.create(Messages.class);
        UserServiceAsync userService = GWT.create(UserService.class);
        presenter = new LoginBoxPresenter(userService,EventBus.INSTANCE, this);
    }

    /**
     * Construct widget with a unique identifier
     *
     * @param identifier
     */
    @UiConstructor
    public LoginBox(String identifier) {
        this();
        getElement().setId(identifier);
    }

    @UiHandler("login")
    void login(ClickEvent event) {
        presenter.handleLogin(emailBox.getText(), passwordBox.getText());
    }

    @Override
    public void loginFailure() {
        clearBoxes();
        alert.setText(messages.loginError());
    }

    @Override
    public void systemError() {
        clearBoxes();
        alert.setText(messages.systemError());
    }

    private void clearBoxes() {
        emailBox.setText("");
        passwordBox.setText("");
    }
}
