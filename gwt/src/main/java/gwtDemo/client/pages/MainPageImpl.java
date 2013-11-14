package gwtDemo.client.pages;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;

public class MainPageImpl extends Composite implements MainPage {
    //HeaderComponent headerComponent;
    
    // TODO use gwt query to access content-container and add components by hand
    
    public MainPageImpl() {
    	HTMLPanel htmlPanel = new HTMLPanel(null);
    	initWidget(uiBinder.createAndBindUi(this));
    }
    
    @Override
    public Widget asWidget() {
        return this;
    }
}







