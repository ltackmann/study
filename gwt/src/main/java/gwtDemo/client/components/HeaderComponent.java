package gwtDemo.client.components;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.Component;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public class HeaderComponent extends Component {
	private final HeaderPresenter presenter;
	private TextBox alertBox;

	public HeaderComponent(AppInjector injector) {
		this("header", injector);
	}
	
	public HeaderComponent(String id, AppInjector injector) {
		super(id, injector);
		presenter = new HeaderPresenter(this, injector);
		initComponent();
	}

	@Override
	public void initComponent() {
		final ListBox languageSelector = new ListBox();
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

		final LoginComponent loginComponent = new LoginComponent(injector, new LoginComponent.LoginHandler() {
			@Override
			public void onLogin(String email, String password) {
				presenter.handleLogin(email, password);
			}
		});
		add(loginComponent);
		
		alertBox = new TextBox();
		add(alertBox);
	}

	public void showError(String message) {
		alertBox.setText(message);
	}
}
