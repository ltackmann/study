package gwtDemo.client.ui;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.ui.presenter.HeaderPresenter;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class HeaderComponent extends Composite implements HeaderPresenter.Display {
	private final HeaderPresenter presenter;
	@UiTemplate("headerComponent.ui.xml")
	interface HeaderComponentUiBinder extends UiBinder<HTMLPanel, HeaderComponent> {}
	private static HeaderComponentUiBinder uiBinder = GWT.create(HeaderComponentUiBinder.class);
    
    @UiField
    ListBox languageSelector;
    @UiField
    Label alert;
    @UiField
    TextBox emailBox;
    @UiField
    PasswordTextBox passwordBox;
    
    @UiConstructor
    public HeaderComponent(String identifier) {
    	presenter = new HeaderPresenter((AppInjector)GWT.create(AppInjector.class), this);
    	initWidget(uiBinder.createAndBindUi(this));
        getElement().setId(identifier);
    }
    
    @UiHandler("login")
    void login(ClickEvent event) {
        presenter.handleLogin(emailBox.getText(), passwordBox.getText());
    }

    @Override
    public void showError(String message) {
        alert.setText(message);
    }
}
