package gwtDemo.client.ui.component;

import gwtDemo.client.framework.AppInjector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Composite;

public class MenuComponent extends Composite implements MenuPresenter.Display {
	private final MenuPresenter presenter;

	public MenuComponent(String identifier) {
    	presenter = new MenuPresenter((AppInjector)GWT.create(AppInjector.class), this);
        getElement().setId(identifier);
    }
}
