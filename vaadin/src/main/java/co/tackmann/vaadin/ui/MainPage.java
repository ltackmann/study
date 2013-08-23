package co.tackmann.vaadin.ui;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

// http://gwt.googleusercontent.com/samples/Showcase/Showcase.html#!CwCheckBox

public class MainPage {
	// TODO MVP style testing with mockito

	public void attachTo(UI ui) {
		initField();
		initLayout(ui);
	}
	
	private void initField() {
		/*
		nameField.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(nameField.getValue().isEmpty()) {
					actionComponent.setVisible(false);
				} else {
					actionComponent.setVisible(true);
					actionComponent.showCombo(nameField.getValue());
				}
			}
		});
		nameField.setImmediate(true);
		*/
	}

	private void initLayout(UI ui) {
		EventBus eventBus = new EventBus();
		
		VerticalLayout wrapper = new VerticalLayout();
		HorizontalLayout header = new HorizontalLayout();
		HorizontalLayout contentContainer = new HorizontalLayout();
		
		MenuComponent menu = new MenuComponent(eventBus);
		ContentComponent content = new ContentComponent(eventBus);
		
		wrapper.addComponent(header);
		wrapper.addComponent(contentContainer);
		contentContainer.addComponent(menu);
		contentContainer.addComponent(content);
		
		wrapper.setMargin(true);  
		wrapper.setSpacing(true); 
		ui.setContent(wrapper);

		// input
		//pageLayout.addComponent(nameField);
		//pageLayout.addComponent(actionComponent);
	}
}
