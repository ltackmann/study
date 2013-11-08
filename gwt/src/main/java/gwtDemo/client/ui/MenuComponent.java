package gwtDemo.client.ui;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.ui.presenter.MenuPresenter;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Composite;

public class MenuComponent extends Composite implements MenuPresenter.Display {
	private final MenuPresenter presenter;

	public MenuComponent(String identifier) {
    	presenter = new MenuPresenter((AppInjector)GWT.create(AppInjector.class), this);
        getElement().setId(identifier);
    }
}
