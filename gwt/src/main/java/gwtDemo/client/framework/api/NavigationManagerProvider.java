package gwtDemo.client.framework.api;

import com.google.inject.Provider;

public class NavigationManagerProvider implements Provider<NavigationManager> {
	public static NavigationManager INSTANCE;

	@Override
	public NavigationManager get() {
		if(INSTANCE == null) {
			throw new IllegalStateException("navigation manager must be initialized");
		}
		return INSTANCE;
	}
}
