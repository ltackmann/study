package gwtDemo.client.components;

import gwtDemo.client.elements.UiSelect;
import gwtDemo.client.elements.UiToggleLink;
import gwtDemo.client.framework.Component;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class HeaderComponent extends Component {
	private final HeaderPresenter presenter;
	private TextBox alertBox;

	public HeaderComponent() {
		super("div");
		presenter = new HeaderPresenter(this);
		initComponent();
	}
	
	private void initComponent() {
		setId("ui-header");
		
		final UiSelect languageSelector = new UiSelect();
		languageSelector.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				int selected = languageSelector.getSelectedIndex();
				if (selected > 0) {
					String language = languageSelector.getValue(selected);
					presenter.changeLanguage(language);
				}
			}
		});
		add(languageSelector);

		final UiToggleLink loginLink = new UiToggleLink("Login", new UiToggleLink.ToggleLinkEventHandler() {
			LoginComponent loginComponent;
			
			@Override
			public void onToggle() {
				loginComponent = new LoginComponent(new LoginComponent.LoginHandler() {
					@Override
					public void onLogin(String email, String password) {
						presenter.handleLogin(email, password);
					}
				});
				//RootPanel.get().add(loginComponent);
			}

			@Override
			public void onUnToggle() {
				if(loginComponent != null) {
					loginComponent.removeFromParent();
					loginComponent = null;
				}
			}
		});
		add(loginLink);
		
		//alertBox = new TextBox();
		//add(alertBox);
	}

	public void showError(String message) {
		alertBox.setText(message);
	}
}
