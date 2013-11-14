package gwtDemo.client.components;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import gwtDemo.client.framework.api.Component;

public class LoginComponent extends Composite implements Component {
	public LoginComponent() {
		VerticalPanel panel = new VerticalPanel();
		initWidget(panel);
		initComponent();
	}
	
	@Override
	public void initComponent() {
		// TODO Auto-generated method stub
		/** gwt:VerticalPanel>
		<gwt:Label text="{msg.loginTitle}" styleName="{style.caption}"/>
		<gwt:Label text="{msg.loginEmail}"/>
		<gwt:TextBox ui:field="emailBox"/>
		<gwt:Label text="{msg.loginPassword}"/>
		<gwt:PasswordTextBox ui:field="passwordBox"/>
		<gwt:Button text="{msg.loginButton}" styleName="button" ui:field="login"/>
		</gwt:VerticalPanel> */
	}
}
