package gwtDemo.client.framework;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * Components are raw GUI constructs without URLs or security restriction. Pages
 * are build from one or more components. Components have Presenters which take
 * care of interacting with the backend.
 */
public abstract class Component extends HTMLPanel {
	protected final AppInjector injector = GWT.create(AppInjector.class);

	/**
	 * Create component with the given tag 
	 * 
	 * @param tag
	 */
	public Component(String tag) {
		super(tag, "");
		addClassName("ui-component");
	}
	
	public void addClassName(String name) {
		getElement().addClassName(name);
	}
	
	public void setId(String id) {
		getElement().setId(id);
	}
}
