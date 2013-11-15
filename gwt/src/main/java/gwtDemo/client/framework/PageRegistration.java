package gwtDemo.client.framework;


/**
 * Registers a page for navigation
 */
public interface PageRegistration<PC extends PageController<P>, P extends Page> {
	PC getPageController(P page, AppInjector injector);
	
	P getPage(AppInjector injector);
	
	Class<P> getPageType();
	
	boolean isSingleton();
}

