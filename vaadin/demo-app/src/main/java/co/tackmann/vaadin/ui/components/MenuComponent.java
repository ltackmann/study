package co.tackmann.vaadin.ui.components;

import co.tackmann.vaadin.ui.ButtonNavigationListener;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MenuComponent extends CustomComponent {
	public MenuComponent(Navigator navigator) {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(new Button("Text input", new ButtonNavigationListener(navigator, "main", "inputDemo")));
		layout.addComponent(new Button("Notifications", new ButtonNavigationListener(navigator, "main", "notificationDemo")));
		
		setCompositionRoot(layout);
	}
	
	// TODO figure out how to show button as active based on URL
}
