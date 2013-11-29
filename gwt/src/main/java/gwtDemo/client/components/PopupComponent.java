package gwtDemo.client.components;

import gwtDemo.client.framework.Component;

public class PopupComponent extends Component {
	public PopupComponent() {
		super("div");
		initComponent();
	}

	private void initComponent() {
		addClassName("ui-popup");
	}
}
