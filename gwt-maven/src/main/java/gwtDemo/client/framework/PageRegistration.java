package gwtDemo.client.framework;

/**
 * Registers a page for navigation
 */
public interface PageRegistration<PC extends PageController<P>, P extends Page> {
	PC createPageController(P page);
	
	Class<P> getPageType();
	
	boolean isSingleton();
}

