package gwtDemo.client.framework;

/**
 * ComponentPresenters are responsible for handling business logic exposed the components
 */
public abstract class ComponentPresenter<C extends Component> extends AbstractGwtLogic {
	protected final C component;
	
	public ComponentPresenter(C component) {
		this.component = component;
	}
}
