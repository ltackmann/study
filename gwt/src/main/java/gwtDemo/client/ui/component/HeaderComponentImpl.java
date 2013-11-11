package gwtDemo.client.ui.component;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.ui.presenter.HeaderPresenter;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
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
import com.google.gwt.event.dom.client.ChangeHandler;

public class HeaderComponentImpl extends Composite implements HeaderComponent {
	private final HeaderPresenter presenter;
    
    private LoginComponent loginComponent;
    
    public HeaderComponentImpl(String id) {
    	presenter = new HeaderPresenter(this, (AppInjector) GWT.create(AppInjector.class));
    	getElement().setId(id);
        init();
    }
    
    private void init() {
    	final ListBox languageSelector = new ListBox();
    	languageSelector.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selected = languageSelector.getSelectedIndex();
				if(selected > 0) {
					String language = languageSelector.getValue(selected);
					presenter.changeLanguage(language);
				}
			}
    	});
    }
    
    void login(ClickEvent event) {
        presenter.handleLogin(emailBox.getText(), passwordBox.getText());
    }

    @Override
    public void showError(String message) {
        alert.setText(message);
    }
}
