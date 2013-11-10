package gwtDemo.client.framework.api;

import gwtDemo.client.framework.AppInjector;

/**
 * ComponentPresenters are responsible for handling business logic exposed the components
 */
public abstract class ComponentPresenter<C extends Component> {
	protected final C component;
	protected final AppInjector injector;
	
	public ComponentPresenter(C component, AppInjector injector) {
		this.component = component;
		this.injector = injector;
	}
}
