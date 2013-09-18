package co.tackmann.gwt.client.ui;

import co.tackmann.gwt.client.resource.i18n.Messages;
import co.tackmann.gwt.client.ui.presenter.HeaderPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class HeaderComponent extends Composite implements HeaderPresenter.Display {
    @UiTemplate("headerComponent.ui.xml")
    interface LoginBoxUiBinder extends UiBinder<HTMLPanel, HeaderComponent> {}
    private static LoginBoxUiBinder uiBinder = GWT.create(LoginBoxUiBinder.class);
    
    private final HeaderPresenter presenter;
    private final Messages messages;

    @UiField
    Label alert;
    @UiField
    TextBox emailBox;
    @UiField
    PasswordTextBox passwordBox;

    public HeaderComponent() {
        initWidget(uiBinder.createAndBindUi(this));
        
        messages = GWT.create(Messages.class);
        UserServiceAsync userService = GWT.create(UserService.class);
        presenter = new HeaderPresenter(userService,EventBus.INSTANCE, this);
    }

    /**
     * Construct widget with a unique identifier
     *
     * @param identifier
     */
    @UiConstructor
    public HeaderComponent(String identifier) {
        this();
        getElement().setId(identifier);
    }

    @UiHandler("login")
    void login(ClickEvent event) {
        //presenter.handleLogin(emailBox.getText(), passwordBox.getText());
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
