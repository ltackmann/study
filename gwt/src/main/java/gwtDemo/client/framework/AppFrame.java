package gwtDemo.client.framework;

import com.google.gwt.user.client.ui.HasWidgets;

public abstract class AppFrame extends AbstractGwtLogic {
	public void attachFrameTo(HasWidgets container) {
		Node frame = buildFrame();
		frame.setId("ui-frame");
		container.add(frame.wrapped); 
	}
	
	protected abstract Node buildFrame();
	
	public abstract void showPage(Page page);
}
