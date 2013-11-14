package gwtDemo.client.framework.api;

import gwtDemo.client.framework.AppInjector;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Components are raw GUI constructs without URLs or security restriction. Pages
 * are build from one or more components. Components have Presenters which take
 * care of interacting with the backend. 
 */
public abstract class Component extends FlowPanel {
	protected AppInjector injector;
	
	public Component(String id) {
		this();
		getElement().setId(id);
	}
	
	public Component() {
		this.injector = (AppInjector) GWT.create(AppInjector.class);
	}
	
	protected abstract void initComponent();
}
