package gwtDemo.client.pages;

import com.google.gwt.user.client.ui.Label;

import gwtDemo.client.components.HeaderComponent;
import gwtDemo.client.framework.Page;

public class MainPage extends Page {
    // TODO use gwt query to access content-container and add components by hand
    
    public MainPage() {
    	super("main");
    	initPage();
    }
    
	@Override
	public void initPage() {
		add(new Label("page"));
	}
}







