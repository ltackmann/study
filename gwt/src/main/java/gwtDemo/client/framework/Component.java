package gwtDemo.client.framework;

/**
 * Components are raw GUI constructs without URLs or security restriction. Pages
 * are build from one or more components. Components have Presenters which take
 * care of interacting with the backend.
 */
public abstract class Component extends Node {
	/**
	 * Create component with the given tag 
	 * 
	 * @param tag
	 */
	public Component(String tag) {
		super(tag);
		addClassName("ui-component");
	}
	
	public Component add(UiElement element) {
		wrapped.add(element.wrapped);
		return this;
	}
}
