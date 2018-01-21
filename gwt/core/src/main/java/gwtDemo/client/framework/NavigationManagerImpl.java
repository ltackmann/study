package gwtDemo.client.framework;

import gwtDemo.shared.domain.Role;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.History;

/**
 * Handles bidirectional mapping between pages and URLs. Allows you to retrive a page for a URL and to navigate to
 */
public class NavigationManagerImpl extends AbstractGwtLogic implements NavigationManager {
	private final Map<String, Class<? extends Page>> urlMapping = new HashMap<String, Class<? extends Page>>();
	private final Map<Class<? extends Page>, String> pageMapping = new HashMap<Class<? extends Page>, String>();
	private final Map<Class<? extends Page>, PageRegistration<?,?>> pageRegistration = new HashMap<Class<? extends Page>, PageRegistration<?,?>>();
	private final Map<Class<? extends Page>, Page> pageCache = new HashMap<Class<? extends Page>, Page>();
	private final AppFrame frame;
	
	public NavigationManagerImpl(AppFrame frame) {
		this.frame = frame;
	}
	
	@Override
	public Class<? extends Page> getCurrentPage() {
		String currentUrl = History.getToken();
		if(urlMapping.containsKey(currentUrl)) {
			return urlMapping.get(currentUrl);
		}
		return null;
	}
	
	@Override
	public void onPageChanged(PageChangedHandler handler) {
		getEventBus().addHandler(PageChanged.TYPE, handler);
	}
	
	@Override
	public <PC extends PageController<P>, P extends Page> NavigationManager registerHandler(String pageUrl, PageRegistration<PC, P> handler) {
		urlMapping.put(pageUrl, handler.getPageType());
		pageMapping.put(handler.getPageType(), pageUrl);
		pageRegistration.put(handler.getPageType(), handler);
		return this;
	}
	
    
    @Override
    public void reloadPage() {
    	History.fireCurrentHistoryState();
    }
	
	@Override
	public <P extends Page> void showPage(Class<P> pageType) {
		if(pageMapping.containsKey(pageType)) {
			String pageUrl = pageMapping.get(pageType);
			History.newItem(pageUrl);
		}
	}
	
    @Override
    public void showPage(String url) {
		if(urlMapping.containsKey(url)) {
			displayPage(urlMapping.get(url));
		}
	}

	 /**
     * dispatch between history events (history anchor tokens) and views
     * 
     * @param event
     */
    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        if (token != null) {
            GWT.log("trying to lookup page for URL: " + token);
            showPage(token);
        }
    }

	private <P extends Page> void displayPage(Class<P> pageType) {
		P page = getPage(pageType);
		PageController<P> controller = getRegistration(pageType).createPageController(page);
		
		assertCurrentUserCanViewPage(controller, get(ClientSession.class));
		
		frame.showPage(page);  
		getEventBus().fireEvent(new PageChanged(controller));
	}
	
	@SuppressWarnings("unchecked")
	private <PC extends PageController<P>, P extends Page> PageRegistration<PC, P> getRegistration(Class<P> pageType) {
		return (PageRegistration<PC, P>) pageRegistration.get(pageType);
	}
	
	private <P extends Page> void assertCurrentUserCanViewPage(PageController<P> controller, ClientSession clientSession) {
		Role userRole = clientSession.getUser().getRole();
		if(!controller.isPageAllowedFor(userRole)) {
			throw new IllegalStateException("current user with role " + userRole + " cannot view page " + controller.page);
		}
	}
	
	@SuppressWarnings("unchecked")
	private <PC extends PageController<P>, P extends Page> P getPage(Class<P> pageType) {
		PageRegistration<PC, P> registration = getRegistration(pageType);
		if(registration.isSingleton()) {
			if(!pageCache.containsKey(pageType)) {
				P page = get(pageType);
				pageCache.put(pageType, page);
			}
			return (P) pageCache.get(pageType);
		} else {
			return get(pageType);
		}
	}
}
