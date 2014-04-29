package gwtDemo.client.framework;

public abstract class AbstractGwtLogic implements GwtLogic {
	@Override
	public <T extends GwtLogic> T get(Class<T> type) {
		return GwtFactory.Get.INSTANCE.get(type);
	}
}
