package gwtDemo.client.elements;

import gwtDemo.client.framework.UiElement;

public class UiLabel extends UiElement {
	public UiLabel(String text) {
		super("div");
		setText(text);
	}
}
