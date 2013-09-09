package co.tackmann.vaadin.ui;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class ActionComponent extends CustomComponent {
	private VerticalLayout actionLayout = new VerticalLayout();
	private VerticalLayout actionResultLayout = new VerticalLayout();
	
	public ActionComponent() {
		VerticalLayout wrapper = new VerticalLayout();
		wrapper.addComponent(actionLayout);
		setCompositionRoot(wrapper);
	}
	
	public void showCombo(final String message) {
		final ComboBox actionSelector = new ComboBox("Select action");
		
		for (ActionType actionType : ActionType.values()) {
			actionSelector.addItem(actionType);
			actionSelector.setItemCaption(actionType, actionType.name);
		}

		actionSelector.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				ActionType actionType = (ActionType) actionSelector.getValue();
				if (actionType == null) {
					actionLayout.setVisible(false);
					actionSelector.setComponentError(new UserError("Please select an action"));
				} else {
					actionSelector.setComponentError(null);
					showAction(actionType, message);
				}
			}
		});
		actionSelector.setImmediate(true);
		actionSelector.setVisible(false);
		actionLayout.addComponent(actionSelector);
	}

	private void showAction(final ActionType actionType, final String message) {
		HorizontalLayout wrapper = new HorizontalLayout();
		final Label label = new Label();
		label.setCaption(actionType.name);
		final Button button = new Button("Do action");
		button.setDescription(actionType.name); // tool tip
		button.setData(actionType);
		wrapper.addComponent(label);
		wrapper.addComponent(button);
		
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				switch (actionType) {
				case SHOUT:
					showResult("Shouting " + message);
					break;
				case WISPER:
					showResult("Wispering " + message);
					break;
				default:
					Notification.show("please select", Notification.TYPE_ERROR_MESSAGE);
				}
			}
		});
		actionLayout.addComponent(wrapper);
	}
	
	private void showResult(String message) {
		if(actionResultLayout.iterator().hasNext()) {
			// clear old content
			actionResultLayout.removeAllComponents();
		}
		actionResultLayout.addComponent(new Label(message));
	}
}
