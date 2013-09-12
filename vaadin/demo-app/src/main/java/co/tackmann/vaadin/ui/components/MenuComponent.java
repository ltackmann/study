package co.tackmann.vaadin.ui.components;

import java.util.LinkedList;
import java.util.List;

import co.tackmann.vaadin.ui.ButtonNavigationListener;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MenuComponent extends CustomComponent {
	private final List<Button> buttons = new LinkedList<Button>();
	private final Button inputDemoButton;
	private final Button notificationDemoButton;
	private final Button validationDemoButton;
	
	public MenuComponent(Navigator navigator) {
		inputDemoButton = createButton("Text input", navigator, "main", "inputDemo");
		notificationDemoButton = createButton("Notifications", navigator, "main", "notificationDemo");
		validationDemoButton = createButton("Validations", navigator, "main", "validationDemo");
		
		VerticalLayout layout = new VerticalLayout();
		initLayout(layout);
		setCompositionRoot(layout);
	}
	
	private void initLayout(VerticalLayout layout) {
		layout.addComponent(inputDemoButton);
		layout.addComponent(notificationDemoButton);
		layout.addComponent(validationDemoButton);
	}
	
	private Button createButton(String title, Navigator navigator, String view, String subView) {
		final ButtonNavigationListener listener = new ButtonNavigationListener(navigator, view, subView);
		final Button button = new Button(title, listener);
		listener.deferredListener = new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO ensure menu is also made active on navigation changes
				makeButtonActive(button);
			}
		};
		buttons.add(button);
		return button;
	}
	
	private void makeButtonActive(Button button) {
		for(Button aButton : buttons) {
			if(aButton == button) {
				aButton.addStyleName("active");
			} else {
				aButton.removeStyleName("active");
			}
		}
	}
}
