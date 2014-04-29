package gwtDemo.client.framework;

import java.util.HashMap;
import java.util.Map;

public class GwtFactoryImpl implements GwtFactory {
	private Map<Class<? extends GwtLogic>, GwtCreator<?>> map = new HashMap<Class<? extends GwtLogic>, GwtCreator<?>>();

	GwtFactoryImpl() {
		// package private so it can only be created inside framework
	}

	@Override
	public <T extends GwtLogic, K extends T> void register(Class<T> clazz,
			GwtCreator<K> creator) {
		map.put(clazz, creator);
	}

	@Override
	public <T extends GwtLogic> T get(Class<T> clazz) {
		GwtCreator<T> creator = getCreator(clazz);
		if (creator == null) {
			throw new RuntimeException(
					"No creator found for "
							+ clazz.getName()
							+ ". Please register a creator in your project specific GwtRegistrator.");
		}
		T instance = creator.get();
		if (instance == null) {
			throw new RuntimeException("Creator failed "
					+ creator.getClass().getName() + " failed creating "
					+ clazz.getName());
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	private <T extends GwtLogic, K extends T> GwtCreator<K> getCreator(
			Class<T> clazz) {
		return (GwtCreator<K>) map.get(clazz);
	}
}
