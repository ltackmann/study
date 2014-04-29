package gwtDemo.client.framework;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.Widget;

/** Pages are GUI constructs that have a URL and possible security restrictions */
public abstract class Page extends Node implements GwtLogic {
	protected final AppInjector injector = GWT.create(AppInjector.class);
	
	public Page(String id) {
		super("div");
		setId(id);
		addClassName("ui-page");
	}
	
	public Page add(Component component) {
		wrapped.add(component.wrapped);
		return this;
	}
	
	// package private so only accessible inside the framework
	Widget asWidget() {
		return wrapped;
	}
	
	@Override
	public <T extends GwtLogic> T get(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public abstract void initPage();
	
	public UiElement asUiElement() {
		return new UiElement(this);
	}
}
