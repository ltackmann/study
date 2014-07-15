package co.tackmann.vaadin;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import co.tackmann.vaadin.config.SpringConfig;
import co.tackmann.vaadin.ui.views.MainView;
import co.tackmann.vaadin.ui.views.StartView;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("runo")
@SuppressWarnings("serial")
public class AppUI extends UI {
	private AnnotationConfigApplicationContext applicationContext;
	
	@Override
	protected void init(VaadinRequest request) {
		if(applicationContext == null) {
			// TODO check vaadin if this should this be synchronized ?
			bindDependencies();
		}
		
        // register views and start application
		Navigator navigator = applicationContext.getBean(Navigator.class);
		navigator.addView("main", applicationContext.getBean(MainView.class));
		navigator.addView("", applicationContext.getBean(StartView.class));
	}
	
	private void bindDependencies() {
		applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		// runtime spring bindings
		BeanDefinition uiBean  = new GenericBeanDefinition();
		uiBean.setBeanClassName(UI.class.getSimpleName());
		uiBean.setScope("singleton");
		applicationContext.registerBeanDefinition(UI.class.getSimpleName(), uiBean);
		
		BeanDefinition navigatorBean  = new GenericBeanDefinition();
		navigatorBean.setBeanClassName(Navigator.class.getSimpleName());
		navigatorBean.setScope("singleton");
		applicationContext.registerBeanDefinition(Navigator.class.getSimpleName(), navigatorBean);
	}
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = AppUI.class, widgetset = "com.vaadin.DefaultWidgetSet")
	public static class Servlet extends VaadinServlet {
	}
}


