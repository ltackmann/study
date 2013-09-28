package gwtDemo.client.view;

import com.google.inject.Inject;

import gwtDemo.client.view.presenter.MainViewPresenter;
import gwtDemo.client.ui.HeaderComponent;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;

public class MainView extends Composite implements MainViewPresenter.Display {
    @UiTemplate("mainView.ui.xml")
    interface MainViewUiBinder extends UiBinder<HTMLPanel, MainView> { }
    private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
    
    //@Inject
    //@UiField(provided = true) 
    //HeaderComponent headerComponent;
    
    public MainView() {
    	initWidget(uiBinder.createAndBindUi(this));
    }
    
    @Override
    public Widget asWidget() {
        return this;
    }
}







