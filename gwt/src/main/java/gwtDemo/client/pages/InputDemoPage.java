package gwtDemo.client.pages;

import com.google.gwt.user.client.ui.Label;

import gwtDemo.client.framework.Page;

public class InputDemoPage extends Page {
	public InputDemoPage() {
		super("ui-input-demo-page");
		initPage();
	}

	@Override
	public void initPage() {
		add(new Label("input demo"));
	}
}
