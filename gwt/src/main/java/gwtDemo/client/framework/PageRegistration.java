package gwtDemo.client.framework;

/**
 * Registers a page for navigation
 */
public interface PageRegistration<PC extends PageController<P>, P extends Page> {
	PC createPageController(P page, AppInjector injector);
	
	P createPage(AppInjector injector);
	
	Class<P> getPageType();
	
	boolean isSingleton();
}

