package co.tackmann;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
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
	private Button notificationButton = new Button("Notify Me");

	// private ComboBox combo = new ComboBox(" Test");
	// private CheckBox checkBox = new CheckBox(" Keep previous results");
	// private Button button = new Button(" Time it!");
	// private VerticalLayout resultsLayout = new VerticalLayout();

	// TODO can this be moved out ?
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "co.tackmann.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	void buildLayout() {
		layout.setMargin(true);
		setContent(layout);

		layout.addComponent(nameField);
		layout.addComponent(nameLabel);
		layout.addComponent(button);
		layout.addComponent(notificationButton);
	}

	void handleEvents() {
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				nameLabel.setCaption("Hello " + nameField.getValue());
			}
		});
		notificationButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				Notification.show("Hello " + nameField.getValue());
			}
		});
	}

	@Override
	protected void init(VaadinRequest request) {
		// TODO hide/show content
		buildLayout();
		handleEvents();
	}
}
