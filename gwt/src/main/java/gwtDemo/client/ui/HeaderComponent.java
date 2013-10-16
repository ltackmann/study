package gwtDemo.client.ui;

import gwtDemo.client.resource.i18n.ClientMessages;
import gwtDemo.client.ui.presenter.HeaderPresenter;
import gwtDemo.client.view.MainView;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;

public class HeaderComponent extends Composite implements HeaderPresenter.Display {
	@UiTemplate("headerComponent.ui.xml")
	interface HeaderComponentUiBinder extends UiBinder<HTMLPanel, HeaderComponent> {}
	private static HeaderComponentUiBinder uiBinder = GWT.create(HeaderComponentUiBinder.class);
	
	private HeaderPresenter presenter;
    
    @UiField
    ListBox languageSelector;
    @UiField
    Label alert;
    @UiField
    TextBox emailBox;
    @UiField
    PasswordBox passwordBox;
    
    public HeaderComponent() {
    	initWidget(uiBinder.createAndBindUi(this));
    }
   
    @UiConstructor
    public HeaderComponent(String identifier) {
    	this();
        getElement().setId(identifier);
    }
    
    @Inject 
    void init(HeaderComponentUiBinder binder, HeaderPresenter presenter) {
      this.presenter = presenter;
    }

    @UiHandler("login")
    void login(ClickEvent event) {
        presenter.handleLogin(emailBox.getText(), passwordBox.getText());
    }

    @Override
    public void showError(String message) {
        alert.setText();
    }
}
