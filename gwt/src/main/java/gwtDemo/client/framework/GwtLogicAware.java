package gwtDemo.client.framework;

import com.google.web.bindery.event.shared.EventBus;

/**
 * Implemented by classes that can access the GwtLogic but are not 
 * themselves GwtLogic's and thus need not be registered
 */
public interface GwtLogicAware {
	<T extends GwtLogic> T get(Class<T> type);
	
	EventBus getEventBus();
}
