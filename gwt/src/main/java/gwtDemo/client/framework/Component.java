package gwtDemo.client.framework;

import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Components are raw GUI constructs without URLs or security restriction. Pages
 * are build from one or more components. Components have Presenters which take
 * care of interacting with the backend. 
 */
public abstract class Component extends FlowPanel {
	protected AppInjector injector;
	
	public Component(String id, AppInjector injector) {
		this(injector);
		getElement().setId(id);
	}
	
	public Component(AppInjector injector) {
		this.injector = injector;
	}
	
	protected abstract void initComponent();
}
