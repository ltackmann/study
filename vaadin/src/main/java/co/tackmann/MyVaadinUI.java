package co.tackmann;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
	private VerticalLayout layout = new VerticalLayout();

	private final TextField nameField = new TextField("Name");
	private final Label nameLabel = new Label();
	private Button button = new Button("Click Me");
	private ComboBox combo = new ComboBox("Test");

	// private CheckBox checkBox = new CheckBox(" Keep previous results");
	// private Button button = new Button(" Time it!");
	// private VerticalLayout resultsLayout = new VerticalLayout();

	// TODO MVP style testing with mockito

	@Override
	protected void init(VaadinRequest request) {
		// TODO hide/show content
		initButtons();
		initCombo();
		initLayout();
	}

	void initLayout() {
		layout.setMargin(true);
		setContent(layout);

		layout.addComponent(nameField);
		layout.addComponent(nameLabel);
		layout.addComponent(combo);
		layout.addComponent(button);
	}

	void initCombo() {
		for (ActionType actionType : ActionType.values()) {
			combo.addItem(actionType);
			combo.setItemCaption(actionType, actionType.name);
		}

		combo.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				ActionType actionType = (ActionType) combo.getValue();
				if(actionType != null) {
					button.setDescription(actionType.name);
					button.setData(actionType);
					button.setVisible(true);
				} else {
					button.setVisible(false);
				}
			}
		});
		combo.setImmediate(true);
	}

	void initButtons() {
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				ActionType actionType = (ActionType) button.getData();
				switch (actionType) {
				case NOTIFICATION:
					Notification.show("Hello " + nameField.getValue());
					break;
				case FIELD:
					nameLabel.setCaption("Hello " + nameField.getValue());
					break;
				default:
					Notification.show("please select", Notification.TYPE_ERROR_MESSAGE);
				}
			}
		});
		button.setVisible(false);
	}

	// TODO can this be moved out ?
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "co.tackmann.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}
}
