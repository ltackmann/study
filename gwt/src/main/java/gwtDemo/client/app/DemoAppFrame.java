package gwtDemo.client.app;

import com.google.gwt.user.client.ui.HasWidgets;

import gwtDemo.client.components.HeaderComponent;
import gwtDemo.client.elements.UiDiv;
import gwtDemo.client.elements.UiTopMenu;
import gwtDemo.client.framework.Frame;
import gwtDemo.client.framework.UiElement;
import gwtDemo.client.framework.Page;

public class DemoAppFrame extends Frame {
	private UiElement pageWrapper;
	
	public DemoAppFrame(HasWidgets container) {
		super(container);
		initFrame();
	}
	
	private void initFrame() {
		pageWrapper = new UiDiv();
		pageWrapper.setId("ui-page-wrapper");
		UiElement container = new UiDiv();
		container.setId("ui-container");
		
		container.add(new UiTopMenu());
		container.add(pageWrapper);
		
		add(new HeaderComponent());
		add(container);
	}

	@Override
	public void showPage(Page page) {
		pageWrapper.clear();
		pageWrapper.add(page.asUiElement());
	}
}
