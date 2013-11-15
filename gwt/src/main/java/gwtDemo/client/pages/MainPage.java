package gwtDemo.client.pages;

import gwtDemo.client.components.HeaderComponent;
import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.Page;

public class MainPage extends Page {
    // TODO use gwt query to access content-container and add components by hand
    
    public MainPage(AppInjector injector) {
    	super("main", injector);
    	initPage();
    }
    
	@Override
	public void initPage() {
		HeaderComponent headerComponent = new HeaderComponent(injector);
		add(headerComponent);
	}
}







