package gwtDemo.client.framework;

public interface GwtLogic {
	<T extends GwtLogic> T get(Class<T> type);
}
