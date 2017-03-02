package gwtDemo.client.app;

import gwtDemo.client.framework.AppController;
import gwtDemo.client.framework.AppEntryPoint;
import gwtDemo.client.framework.AppFrame;
import gwtDemo.client.framework.ClientSession;
import gwtDemo.client.framework.ClientSessionCreator;
import gwtDemo.shared.domain.Role;
import gwtDemo.shared.domain.User;

import com.google.gwt.user.client.ui.RootPanel;

public class DemoAppEntryPoint extends AppEntryPoint {
	@Override
	protected void startApplication(AppFrame frame, AppController controller) {
		frame.attachFrameTo(RootPanel.get());
		
		// initialize singleton providers
		ClientSessionCreator.INSTANCE = new ClientSession(new User(Role.GUEST));

		// start application
		controller.start();
	}
}
