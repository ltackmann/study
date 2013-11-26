package gwtDemo.client.framework;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.FlowPanel;

/** Pages are GUI constructs that have a URL and possible security restrictions */
public abstract class Page extends FlowPanel {
	protected final AppInjector injector = GWT.create(AppInjector.class);
	
	public Page(String id) {
		getElement().setId(id);
		getElement().setClassName("page");
	}
	
	public abstract void initPage();
}
