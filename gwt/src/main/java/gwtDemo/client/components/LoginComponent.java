package gwtDemo.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

import gwtDemo.client.framework.Component;
import gwtDemo.client.resource.i18n.ClientMessages;

public class LoginComponent extends Component {
	private final LoginHandler loginHandler;
	
	public LoginComponent(LoginHandler loginHandler) {
		this.loginHandler = loginHandler;
	}
	
	@Override
	public void initComponent() {
		ClientMessages messages = injector.getClientMessages();
		
		add(new Label(messages.loginTitle()));
		
		add(new Label(messages.loginEmail()));
		final TextBox emailBox = new TextBox();
		add(emailBox);
		
		add(new Label(messages.loginPassword()));
		final PasswordTextBox passwordBox = new PasswordTextBox();
		add(passwordBox);
		
		Button loginButton = new Button(messages.loginButton());
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
