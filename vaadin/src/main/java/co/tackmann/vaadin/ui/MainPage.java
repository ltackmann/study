package co.tackmann.vaadin.ui;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MainPage {
	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout actionLayout = new HorizontalLayout();
	private VerticalLayout outputLayout = new VerticalLayout();

	private final TextField nameField = new TextField("Type your name");
	private final Label actionLabel = new Label();
	private Button actionButton = new Button("Click Me");
	private ComboBox combo = new ComboBox("Test");

	// private CheckBox checkBox = new CheckBox(" Keep previous results");

	// TODO MVP style testing with mockito

	public void attachTo(UI ui) {
		initCombo();
		initAction();
		initLayout(ui);
	}
	
	private void initCombo() {
		for (ActionType actionType : ActionType.values()) {
			combo.addItem(actionType);
			combo.setItemCaption(actionType, actionType.name);
		}

		combo.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				ActionType actionType = (ActionType) combo.getValue();
				if (actionType != null) {
					actionLabel.setCaption(actionType.name);
					actionButton.setDescription(actionType.name); // tool tip
					actionButton.setData(actionType);
					actionLayout.setVisible(true);
				} else {
					actionLayout.setVisible(false);
				}
			}
		});
		combo.setImmediate(true);
	}

	private void initAction() {
		actionButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				ActionType actionType = (ActionType) actionButton.getData();
				switch (actionType) {
				case NOTIFICATION:
					Notification.show("Hello " + nameField.getValue());
					break;
				case FIELD:
					actionLabel.setCaption("Hello " + nameField.getValue());
					break;
				default:
					Notification.show("please select",
							Notification.TYPE_ERROR_MESSAGE);
				}
			}
		});
		actionLayout.setVisible(false);
	}

	private void initLayout(UI ui) {
		layout.setMargin(true);
		ui.setContent(layout);

		// input
		layout.addComponent(nameField);
		layout.addComponent(combo);

		// action
		actionLayout.addComponent(actionLabel);
		actionLayout.addComponent(actionButton);
		layout.addComponent(actionLayout);
		
		// result
		layout.addComponent(outputLayout);
	}
}
