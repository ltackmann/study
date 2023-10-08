package gwtDemo.client.framework;

public class UiElement extends Node {
	public UiElement(String tag) {
		super(tag);
	}
	
	public UiElement(Node node) {
		super(node.wrapped);
	}
	
	public UiElement add(UiElement element) {
		wrapped.appendChild(element.wrapped);
		return this;
	}
}
