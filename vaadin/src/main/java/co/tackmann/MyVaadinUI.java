package co.tackmann;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {
	// TODO can this be moved out ?
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "co.tackmann.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		
		final TextField nameField = new TextField("Name"); 
		layout.addComponent(nameField);
		
		final Label nameLabel = new Label();
		layout.addComponent(nameLabel);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				nameLabel.setCaption("Hello " + nameField.getValue());
			}
		});
		layout.addComponent(button);
	}

}
