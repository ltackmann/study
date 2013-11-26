package gwtDemo.client.framework;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Components are raw GUI constructs without URLs or security restriction. Pages
 * are build from one or more components. Components have Presenters which take
 * care of interacting with the backend.
 */
public abstract class Component extends FlowPanel {
	protected final AppInjector injector = GWT.create(AppInjector.class);

	public Component(String id) {
		this();
		getElement().setId(id);
	}

	public Component() {
		getElement().setClassName("component");
	}

	protected abstract void initComponent();
}
