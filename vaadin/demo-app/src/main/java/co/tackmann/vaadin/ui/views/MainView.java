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
public class MainView extends VerticalLayout implements View {
	private static final long serialVersionUID = 7049561594037817863L;
	private final Navigator navigator;
	private ContentComponent content;
	
	public MainView(Navigator navigator, UI ui) {
		this.navigator = navigator;
		initLayout(ui);
	}
	
	private void initLayout(UI ui) {
		ui.getPage().setTitle("Vaadin Showcases");
		
		HorizontalLayout header = new HorizontalLayout();
		MenuComponent menu = new MenuComponent(navigator);
		HorizontalLayout contentContainer = new HorizontalLayout();
		content = new ContentComponent();
		
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
		// TODO Use Java 7 and do switch on strings
		if(viewParameters.isEmpty() || viewParameters.equals("inputDemo")) {
			content.showTextInput();
		} else if(viewParameters.equals("notificationDemo")) {
			content.showNotification();
		} else {
			throw new IllegalArgumentException("unhandled view state [" + viewName + "] with parameters [" + viewParameters + "]");
		}
	}
}
