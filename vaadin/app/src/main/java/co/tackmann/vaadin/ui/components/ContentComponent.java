package co.tackmann.vaadin.ui.components;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ContentComponent extends CustomComponent {
	private final VerticalLayout layout = new VerticalLayout();
	
	public ContentComponent() {
		setCompositionRoot(layout);
	}
	
	// TODO make into custom widgets for each use case
	public void showNotificationDemo() {
		layout.removeAllComponents();
		layout.addComponent(new Label("Notification"));
	}
	
	public void showInputDemo() {
		layout.removeAllComponents();
		
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
					
					layout.addComponent(clearButton);
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
		
		layout.addComponents(textField, outputLabel, clearButton);
	}
	
	public void showValidationDemo() {
		layout.removeAllComponents();
		layout.addComponent(new Label("Validation"));
	}
}
