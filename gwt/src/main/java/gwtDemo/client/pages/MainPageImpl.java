package gwtDemo.client.pages;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;

public class MainPageImpl extends Composite implements MainPage {
    @UiTemplate("mainView.ui.xml")
    interface MainViewUiBinder extends UiBinder<HTMLPanel, MainPageImpl> { }
    private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
    
    //@Inject
    //@UiField(provided = true) 
    //HeaderComponent headerComponent;
    
    // TODO use gwt query to access content-container and add components by hand
    
    public MainPageImpl() {
    	initWidget(uiBinder.createAndBindUi(this));
    }
    
    @Override
    public Widget asWidget() {
        return this;
    }
}







