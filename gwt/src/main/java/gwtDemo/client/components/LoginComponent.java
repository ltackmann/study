package gwtDemo.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

import gwtDemo.client.elements.UiButton;
import gwtDemo.client.elements.UiInput;
import gwtDemo.client.elements.UiInput.InputType;
import gwtDemo.client.elements.UiLabel;
import gwtDemo.client.elements.UiPopupBox;
import gwtDemo.client.framework.Component;
import gwtDemo.client.resource.i18n.ClientMessages;

public class LoginComponent extends Component {
	private final LoginHandler loginHandler;
	
	public LoginComponent(LoginHandler loginHandler) {
		this.loginHandler = loginHandler;
		initComponent();
	}
	
	@Override
	public void initComponent() {
		setId("ui-login");
		ClientMessages messages = injector.getClientMessages();
		UiPopupBox container = new UiPopupBox();
		
		container.add(new UiLabel(messages.loginTitle()));
		
		container.add(new UiLabel(messages.loginEmail()));
		final UiInput emailBox = new UiInput(InputType.EMAIL);
		add(emailBox);
		
		add(new UiLabel(messages.loginPassword()));
		final UiInput passwordBox = new UiInput(InputType.PASSWORD);
		add(passwordBox);
		
		UiButton loginButton = new UiButton(messages.loginButton());
		loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				loginHandler.onLogin(emailBox.getText(), passwordBox.getText());
			}
		});
	}
	
	public static interface LoginHandler {
		void onLogin(String email, String password);
	}
}
