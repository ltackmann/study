package co.tackmann.vaadin.ui;

import co.tackmann.vaadin.events.MenuEvent;

import com.google.gwt.thirdparty.guava.common.eventbus.EventBus;
import com.google.gwt.thirdparty.guava.common.eventbus.Subscribe;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class ContentComponent extends CustomComponent {
	private final VerticalLayout layout = new VerticalLayout();
	
	public ContentComponent(EventBus eventBus) {
		setCompositionRoot(layout);
		eventBus.register(this);
	}
	
	@Subscribe
	public void changeShowcase(MenuEvent event) {
		// TODO remove somewhere else (MVP style) and test with mockito
		// TODO add navigation handling
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
		final Label outputLabel = new Label();
		final TextField textField = new TextField();
		final Button clearButton = new Button("Clear");
		
		textField.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(textField.getValue().isEmpty()) {
					clearButton.setVisible(false);
				} else {
					outputLabel.setCaption("you typed: " + textField.getValue());
					clearButton.setVisible(true);
					
					container.addComponent(clearButton);
				}
			}
		});
		textField.setImmediate(true);
		
		clearButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				outputLabel.setCaption("");
				textField.setValue("");
			}
		});
		clearButton.setVisible(false);
		
		container.addComponents(textField, outputLabel, clearButton);
	}
}
