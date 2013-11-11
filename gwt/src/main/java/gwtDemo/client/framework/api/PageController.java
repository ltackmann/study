package gwtDemo.client.framework.api;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.shared.Role;

import com.google.gwt.user.client.ui.HasWidgets;


/**
 * PageControllers are responsible for handling business logic exposed the pages
 * and enforcing page security
 */
public abstract class PageController<P extends Page> {
	protected final P page;
	protected final AppInjector injector;
	
	public PageController(P page, AppInjector injector) {
		this.page = page;
		this.injector = injector;
	}
	
    public void renderOn(HasWidgets container) {
        container.clear();
        container.add(page.asWidget());
    }
    
    public P getPage() {
    	return page;
    }
    
	public abstract boolean isPageAllowedFor(Role role);
}
