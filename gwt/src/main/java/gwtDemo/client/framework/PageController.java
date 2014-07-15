package gwtDemo.client.framework;

import gwtDemo.shared.domain.Role;

import com.google.gwt.user.client.ui.HasWidgets;


/**
 * PageControllers are responsible for handling business logic exposed the pages
 * and enforcing page security
 */
public abstract class PageController<P extends Page> extends AbstractGwtLogic{
	protected final P page;
	
	public PageController(P page) {
		this.page = page;
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
