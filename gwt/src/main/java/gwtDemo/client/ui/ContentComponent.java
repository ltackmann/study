package gwtDemo.client.ui;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.ui.presenter.ContentPresenter;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Composite;

public class ContentComponent extends Composite implements ContentPresenter.Display {
	private final ContentPresenter presenter;
   
    public ContentComponent(String identifier) {
        presenter = new ContentPresenter((AppInjector)GWT.create(AppInjector.class), this);
    	getElement().setId(identifier);
    }
}
