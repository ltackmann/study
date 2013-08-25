package co.tackmann.vaadin.ui;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

// http://gwt.googleusercontent.com/samples/Showcase/Showcase.html#!CwCheckBox
public class MainPage {
	final EventBus eventBus;
	
	public MainPage(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	public void attachTo(UI ui) {
		initLayout(ui);
	}

	private void initLayout(UI ui) {
		VerticalLayout wrapper = new VerticalLayout();
		HorizontalLayout header = new HorizontalLayout();
		MenuComponent menu = new MenuComponent(eventBus);
		HorizontalLayout contentContainer = new HorizontalLayout();
		ContentComponent content = new ContentComponent(eventBus);
		
		wrapper.addComponent(header);
		wrapper.addComponent(contentContainer);
		contentContainer.addComponent(menu);
		contentContainer.addComponent(content);
		wrapper.setMargin(true);  
		wrapper.setSpacing(true); 
		ui.setContent(wrapper);
	}
}
