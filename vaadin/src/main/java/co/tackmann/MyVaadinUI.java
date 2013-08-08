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
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
	private VerticalLayout layout = new VerticalLayout();
	private HorizontalLayout actionLayout = new HorizontalLayout();
	private VerticalLayout outputLayout = new VerticalLayout();

	private final TextField nameField = new TextField("Type your name");
	private final Label actionLabel = new Label();
	private Button actionButton = new Button("Click Me");
	private ComboBox combo = new ComboBox("Test");

	// private CheckBox checkBox = new CheckBox(" Keep previous results");
	// private Button button = new Button(" Time it!");

	// TODO MVP style testing with mockito

	@Override
	protected void init(VaadinRequest request) {
		// TODO hide/show content
		initCombo();
		initAction();
		initLayout();
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

	void initAction() {
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

	void initLayout() {
		layout.setMargin(true);
		setContent(layout);

		layout.addComponent(nameField);
		// first show combo when name is in place
		layout.addComponent(combo);
		layout.addComponent(actionLayout);

		actionLayout.addComponent(actionLabel);
		actionLayout.addComponent(actionButton);
	}

	void initFields() {

	}

	// TODO can this be moved out ?
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "co.tackmann.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}
}
