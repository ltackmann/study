package gwtDemo.client.pages;

import gwtDemo.client.components.HeaderComponent;
import gwtDemo.client.framework.api.Page;

public class MainPage extends Page {
    // TODO use gwt query to access content-container and add components by hand
    
    public MainPage() {
    	super("main");
    	initPage();
    }
    
	@Override
	public void initPage() {
		HeaderComponent headerComponent = new HeaderComponent();
		add(headerComponent);
	}
}







