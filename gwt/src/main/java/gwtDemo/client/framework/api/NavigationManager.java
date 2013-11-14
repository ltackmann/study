package gwtDemo.client.framework.api;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.shared.domain.Role;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Handles bidirectional mapping between pages and URLs. Allows you to retrive a page for a URL and to navigate to
 */
public class NavigationManager {
	private final Map<String, Class<? extends Page>> urlMapping = new HashMap<String, Class<? extends Page>>();
	private final Map<Class<? extends Page>, PageRegistration<?,?>> pageRegistration = new HashMap<Class<? extends Page>, PageRegistration<?,?>>();
	private final Map<Class<? extends Page>, Page> pageCache = new HashMap<Class<? extends Page>, Page>();
	private final HasWidgets container;
	private final AppInjector injector;
	private final EventBus eventBus;
	
	public NavigationManager(HasWidgets container, AppInjector injector) {
		this.container = container;
		this.injector = injector;
		this.eventBus = injector.getEventBus();
	}
	
	public <PC extends PageController<P>, P extends Page> NavigationManager registerHandler(String pageUrl, PageRegistration<PC, P> handler) {
		urlMapping.put(pageUrl, handler.getPageType());
		pageRegistration.put(handler.getPageType(), handler);
		return this;
	}
	
	public <P extends Page> void showPage(Class<P> pageType) {
		P page = getPage(pageType);
		PageController<P> controller = getRegistration(pageType).getPageController(page, injector);
		
		assertCurrentUserCanViewPage(controller, injector.getClientSession());
		
		controller.renderOn(container);
		eventBus.fireEvent(new PageChanged(controller));
	}
	
	public void showPageForUrl(String pageUrl) {
		if(urlMapping.containsKey(pageUrl)) {
			showPage(urlMapping.get(pageUrl));
		}
	}
	
	public void onPageChanged(PageChangedHandler handler) {
		eventBus.addHandler(PageChanged.TYPE, handler);
	}
	
	@SuppressWarnings("unchecked")
	private <PC extends PageController<P>, P extends Page> PageRegistration<PC, P> getRegistration(Class<P> pageType) {
		return (PageRegistration<PC, P>) pageRegistration.get(pageType);
	}
	
	private <P extends Page> void assertCurrentUserCanViewPage(PageController<P> controller, ClientSession clientSession) {
		Role userRole = clientSession.getCurrentUser().getRole();
		if(!controller.isPageAllowedFor(userRole)) {
			throw new SecurityException("current user with role " + userRole + " cannot view page " + controller.page);
		}
	}
	
	@SuppressWarnings("unchecked")
	private <PC extends PageController<P>, P extends Page> P getPage(Class<P> pageType) {
		PageRegistration<PC, P> registration = getRegistration(pageType);
		if(registration.isSingleton()) {
			if(!pageCache.containsKey(pageType)) {
				P page = registration.getPage(injector);
				pageCache.put(pageType, page);
			}
			return (P) pageCache.get(pageType);
		} else {
			return registration.getPage(injector);
		}
	}
}
