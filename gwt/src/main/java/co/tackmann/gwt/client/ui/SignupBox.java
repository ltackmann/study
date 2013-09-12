package co.tackmann.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.*;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.*;
import org.randompage.bookmarking.api.domain.Role;
import org.randompage.bookmarking.frontend.client.EventBus;
import org.randompage.bookmarking.frontend.client.resource.Resources;
import org.randompage.bookmarking.frontend.client.resource.i18n.Messages;
import org.randompage.bookmarking.frontend.client.service.UserService;
import org.randompage.bookmarking.frontend.client.service.UserServiceAsync;
import org.randompage.bookmarking.frontend.client.ui.presenter.SignupBoxPresenter;
import org.randompage.bookmarking.frontend.shared.UserDTO;

public class SignupBox extends Composite implements SignupBoxPresenter.Display {
    private static SignupBoxUiBinder uiBinder = GWT.create(SignupBoxUiBinder.class);

    @UiTemplate("signupBox.ui.xml")
    interface SignupBoxUiBinder extends UiBinder<HTMLPanel, SignupBox> {
    }

    private final SignupBoxPresenter presenter;
    private final Messages messages;

    @UiField
    Label error;
    @UiField
    TextBox nameBox;
    @UiField
    TextBox emailBox;
    @UiField
    PasswordTextBox passwordBox;
    @UiField
    PasswordTextBox confirmBox;

    public SignupBox() {
        initWidget(uiBinder.createAndBindUi(this));

        messages = GWT.create(Messages.class);
        UserServiceAsync userService = GWT.create(UserService.class);
        presenter = new SignupBoxPresenter(userService, EventBus.INSTANCE, this, messages);
    }

    /**
     * Construct widget with a unique identifier
     *
     * @param identifier
     */
    // TODO check if we can put these in common IdComposite superclass
    @UiConstructor
    public SignupBox(String identifier) {
        this();
        getElement().setId(identifier);
    }

    @Override
    public void systemError() {
        error.setText(messages.systemError());
    }

    @Override
    public void nameError() {
        setAlert(messages.signupErrorNameMissing(), nameBox);
    }

    @Override
    public void emailError(String error) {
        setAlert(error, emailBox);
    }

    @Override
    public void passwordError() {
        setAlert(messages.signupErrorInvalidPassword(), passwordBox);
    }

    @Override
    public void confirmError() {
        setAlert(messages.signupErrorPasswordMismatch(), confirmBox);
    }

    @UiHandler("signup")
    void login(ClickEvent event) {
        removeAlerts();
        
        UserDTO user = new UserDTO(nameBox.getText(), Role.USER, emailBox.getText());
        final String password = passwordBox.getText();
        final String confirm = confirmBox.getText();
        presenter.handleSignup(user, password, confirm);
    }

    private void removeAlerts() {
        Widget[] widgets = { nameBox, emailBox, passwordBox, confirmBox};
        for(Widget widget : widgets) {
            widget.removeStyleName(Resources.INSTANCE.style().error());
        }
        error.setText("");
        error.setVisible(false);
    }

    private void setAlert(String message, final Widget target) {
        error.setText(message);
        error.setVisible(true);
        DeferredCommand.addCommand(new Command() {
            public void execute() {
                target.setStyleName(Resources.INSTANCE.style().error());
            }
        });
    }
}
