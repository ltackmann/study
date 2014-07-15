package gwtDemo.client.framework;

import com.google.web.bindery.event.shared.EventBus;

/**
 * Util class for providing access to GwtLogic classes
 */
public class AbstractGwtLogicAware implements GwtLogicAware {
	@Override
	public <T extends GwtLogic> T get(Class<T> type) {
		return GwtFactory.Get.INSTANCE.get(type);
	}

	@Override
	public EventBus getEventBus() {
		return GwtFactory.Get.INSTANCE.getEventBus();
	}
}
