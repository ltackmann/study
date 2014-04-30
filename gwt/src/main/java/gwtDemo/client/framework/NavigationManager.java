package gwtDemo.client.framework;

import com.google.gwt.event.logical.shared.ValueChangeHandler;

public interface NavigationManager extends ValueChangeHandler<String>, GwtLogic {
	Class<? extends Page> getCurrentPage();
	
	void onPageChanged(PageChangedHandler handler);
	
	<PC extends PageController<P>, P extends Page> NavigationManager registerHandler(String pageUrl, PageRegistration<PC, P> handler);
	
	void reloadPage();
	
	<P extends Page> void showPage(Class<P> pageType);
	
	void showPage(String url);
}
