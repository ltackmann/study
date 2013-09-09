package co.tackmann.vaadin;

import javax.servlet.annotation.WebServlet;

import co.tackmann.vaadin.ui.views.MainView;
import co.tackmann.vaadin.ui.views.StartView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
@SuppressWarnings("serial")
public class AppUI extends UI {
	@Override
	protected void init(VaadinRequest request) {
		Navigator  navigator = new Navigator(this, this);
        
        // register the views
		navigator.addView("", new StartView(navigator));
		navigator.addView("main", new MainView(navigator, this));
	}
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = AppUI.class, widgetset = "co.tackmann.vaadin.AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}
}


