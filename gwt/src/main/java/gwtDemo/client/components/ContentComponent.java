package gwtDemo.client.ui.component;

import gwtDemo.client.framework.AppInjector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Composite;

public class ContentComponent extends Composite implements ContentPresenter.Display {
	private final ContentPresenter presenter;
   
    public ContentComponent(String identifier) {
        presenter = new ContentPresenter((AppInjector)GWT.create(AppInjector.class), this);
    	getElement().setId(identifier);
    }
}
