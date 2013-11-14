package gwtDemo.client.framework.api;

import com.google.gwt.user.client.ui.FlowPanel;

/** Pages are GUI constructs that have a URL and possible security restrictions */
public abstract class Page extends FlowPanel {
	public Page(String id) {
		getElement().setId(id);
	}
	
	public abstract void initPage();
}
