package gwtDemo.client.framework;

public class UiElement extends Node {
	public UiElement(String tag) {
		super(tag);
	}
	
	public UiElement(Node node) {
		super(node.wrapped);
	}
	
	public UiElement add(UiElement element) {
		wrapped.add(element.wrapped);
		return this;
	}
}
