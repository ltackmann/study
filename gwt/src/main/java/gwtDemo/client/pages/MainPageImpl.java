package gwtDemo.client.pages;

import com.google.gwt.user.client.ui.*;

public class MainPageImpl extends Composite implements MainPage {
    //HeaderComponent headerComponent;
    
    // TODO use gwt query to access content-container and add components by hand
    
    public MainPageImpl() {
    	HTMLPanel htmlPanel = new HTMLPanel("div");
    	initWidget(htmlPanel);
    }
    
    private init() {
    	
    }
    
    @Override
    public Widget asWidget() {
        return this;
    }
}







