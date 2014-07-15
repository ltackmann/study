package gwtDemo.client.framework;

import com.google.gwt.user.client.ui.Widget;

/** Pages are GUI constructs that have a URL and possible security restrictions */
public abstract class Page extends Node implements GwtLogic {
	public Page(String id) {
		super("div");
		setId(id);
		addClassName("ui-page");
	}
	
	// package private so only accessible inside the framework
	Widget asWidget() {
		return wrapped;
	}
	
	public abstract void initPage();
}
