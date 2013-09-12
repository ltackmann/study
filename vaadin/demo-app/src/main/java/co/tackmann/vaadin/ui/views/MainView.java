package co.tackmann.vaadin.ui.views;

import co.tackmann.vaadin.ui.components.ContentComponent;
import co.tackmann.vaadin.ui.components.HeaderComponent;
import co.tackmann.vaadin.ui.components.MenuComponent;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

// http://gwt.googleusercontent.com/samples/Showcase/Showcase.html#!CwCheckBox
@SuppressWarnings("serial")
public class MainView extends VerticalLayout implements View {
	private final ContentComponent content;
	private final HeaderComponent header;
	private final MenuComponent menu;

	public MainView(Navigator navigator, UI ui) {
		this.content = new ContentComponent();
		this.header = new HeaderComponent(ui);
		this.menu = new MenuComponent(navigator);
		initLayout();
	}

	private void initLayout() {
		HorizontalLayout contentContainer = new HorizontalLayout();
		addComponent(header);
		addComponent(contentContainer);
		contentContainer.addComponent(menu);
		contentContainer.addComponent(content);
		setMargin(true);
		setSpacing(true);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		final String viewName = event.getViewName().trim();
		final String viewParameters = event.getParameters().trim();

		switch (viewParameters) {
		case "":
			content.showInputDemo();
			header.setTitle("Vaadin Demo");
			break;
		case "inputDemo":
			content.showInputDemo();
			header.setTitle("Vaadin Input Demo");
			break;
		case "notificationDemo":
			content.showNotificationDemo();
			header.setTitle("Vaadin Notification Demo");
			break;
		case "validationDemo":
			content.showValidationDemo();
			header.setTitle("Vaadin Validation Demo");
			break;
		default:
			throw new IllegalArgumentException("unhandled view state ["
					+ viewName + "] with parameters [" + viewParameters + "]");
		}
	}
}
