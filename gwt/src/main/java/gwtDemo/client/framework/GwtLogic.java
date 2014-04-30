package gwtDemo.client.framework;

import com.google.web.bindery.event.shared.EventBus;

public interface GwtLogic {
	<T extends GwtLogic> T get(Class<T> type);
	
	EventBus getEventBus();
}
