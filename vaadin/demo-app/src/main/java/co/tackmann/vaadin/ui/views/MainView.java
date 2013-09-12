package co.tackmann.vaadin.ui.views;

import co.tackmann.vaadin.ui.components.ContentComponent;
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
	private final MenuComponent menu;

	public MainView(Navigator navigator, UI ui) {
		this.menu = new MenuComponent(navigator);
		this.content = new ContentComponent();
		initLayout(ui);
	}

	private void initLayout(UI ui) {
		ui.getPage().setTitle("Vaadin Showcases");

		HorizontalLayout header = new HorizontalLayout();
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
			content.showTextInput();
			break;
		case "inputDemo":
			content.showTextInput();
			break;
		case "notificationDemo":
			content.showNotification();
			break;
		default:
			throw new IllegalArgumentException("unhandled view state ["
					+ viewName + "] with parameters [" + viewParameters + "]");
		}
	}
}
