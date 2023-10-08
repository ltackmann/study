package gwtDemo.client.framework;

import com.google.web.bindery.event.shared.EventBus;

/**
 * Base class for GwtLogic classes, classes that extends this must be registered
 */
public class AbstractGwtLogic implements GwtLogic {
	@Override
	public <T extends GwtLogic> T get(Class<T> type) {
		return GwtFactory.Get.INSTANCE.get(type);
	}
	
	@Override
	public EventBus getEventBus() {
		return GwtFactory.Get.INSTANCE.getEventBus();
	}
}
