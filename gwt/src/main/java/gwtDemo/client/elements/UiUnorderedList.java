package gwtDemo.client.elements;

import gwtDemo.client.framework.UiElement;

/**
 * The HTML unordered list element (<ul>) represents an unordered list of items
 */
public class UiUnorderedList extends UiElement {
	public UiUnorderedList() {
		super("ul");
		initElement();
	}
	
	private void initElement() {
		addClassName("ui-list");
	}
}
