package gwtDemo.client.framework;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract class Frame extends Node {
	public Frame(HasWidgets container) {
		super("div");
		setId("ui-frame");
		container.add(wrapped);
	}
	
	public Frame add(Node node) {
		wrapped.add(node.wrapped);
		return this;
	}
	
	public abstract void showPage(Page page);
}
