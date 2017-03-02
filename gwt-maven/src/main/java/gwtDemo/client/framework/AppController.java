package gwtDemo.client.framework;

import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.Event.Type;

public abstract class AppController extends AbstractGwtLogic {
	protected final NavigationManager navigationManager;
	
    public AppController() {
    	this.navigationManager = get(NavigationManager.class);
    	History.addValueChangeHandler(navigationManager);
        registerEventHandlers();
    }
    
    public <H> HandlerRegistration addHandler(Type<H> type, H handler) {
    	return getEventBus().addHandler(type, handler);
    }

    protected abstract Class<? extends Page> getDefaultPage();
    
    protected abstract void registerEventHandlers();
    
    /** Start application */
    public void start() {
        if (navigationManager.getCurrentPage() == null) {
        	navigationManager.showPage(getDefaultPage());
        } else {
        	navigationManager.reloadPage();
        }
    }
}
