package gwtDemo.client.framework;

import com.google.gwt.user.client.ui.FlowPanel;

/** Pages are GUI constructs that have a URL and possible security restrictions */
public abstract class Page extends FlowPanel {
	protected final AppInjector injector;
	
	public Page(String id, AppInjector injector) {
		this.injector = injector;
		getElement().setId(id);
	}
	
	public abstract void initPage();
}
