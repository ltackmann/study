package co.tackmann.vaadin.ui;

import co.tackmann.vaadin.events.MenuEvent;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class ContentComponent extends CustomComponent {
	private final VerticalLayout layout = new VerticalLayout();
	
	public ContentComponent(EventBus eventBus) {
		setCompositionRoot(layout);
		eventBus.register(this);
		initLayout(layout, eventBus);
	}

	private void initLayout(final VerticalLayout layout, final EventBus eventBus) {
		
	}
	
	@Subscribe
	public void changeShowcase(MenuEvent event) {
		// TODO remove somewhere else (MVP style) and test with mockito
		layout.removeAllComponents();
		
		switch(event) {
		case NOTIFICATION:
			showNotification(layout);
			break;
		case TEXT_INPUT:
			showTextInput(layout);
			break;
		default:
			Notification.show("unknown menu entry " + event.toString(), Notification.TYPE_ERROR_MESSAGE); 
		}
	}
	
	private void showNotification(final Layout container) {
		container.addComponent(new Label("Notification"));
	}
	
	private void showTextInput(final Layout container) {
		container.addComponent(new Label("Text input"));
	}
}
