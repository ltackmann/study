package gwtDemo.client.framework.api;

import gwtDemo.client.framework.AppInjector;

/**
 * Registers a page for navigation
 */
public interface PageRegistration<PC extends PageController<P>, P extends Page> {
	PC getPageController(P page, AppInjector injector);
	
	P getPage(AppInjector injector);
	
	Class<P> getPageType();
	
	boolean isSingleton();
}
