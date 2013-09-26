package gwtDemo.client.ui;

import gwtDemo.client.resource.i18n.Messages;
import gwtDemo.client.ui.presenter.HeaderPresenter;

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
	private HeaderPresenter presenter;
	private Messages messages;
	
    @UiTemplate("headerComponent.ui.xml")
    interface HeaderComponentUiBinder extends UiBinder<HTMLPanel, HeaderComponent> {}
    @UiField
    ListBox languageSelector;
    @UiField
    Label alert;
   
    @UiConstructor
    public HeaderComponent(String identifier) {
        getElement().setId(identifier);
    }
    
    @Inject 
    void init(HeaderComponentUiBinder binder, HeaderPresenter presenter, Messages messages) {
      initWidget(binder.createAndBindUi(this));
      this.presenter = presenter;
      this.messages = messages;
    }

    //@UiHandler("login")
    //void login(ClickEvent event) {
        //presenter.handleLogin(emailBox.getText(), passwordBox.getText());
    //}

    @Override
    public void systemError() {
        alert.setText(messages.systemError());
    }
}
