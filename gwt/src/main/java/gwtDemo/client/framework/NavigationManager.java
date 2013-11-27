package gwtDemo.client.framework;

import com.google.gwt.event.logical.shared.ValueChangeHandler;

public interface NavigationManager extends ValueChangeHandler<String> {
	<PC extends PageController<P>, P extends Page> NavigationManager registerHandler(String pageUrl, PageRegistration<PC, P> handler);
	
	<P extends Page> void showPage(Class<P> pageType);
	
	void onPageChanged(PageChangedHandler handler);
}
