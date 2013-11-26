package gwtDemo.client.frames;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;

import gwtDemo.client.components.HeaderComponent;
import gwtDemo.client.components.MenuComponent;
import gwtDemo.client.framework.Frame;
import gwtDemo.client.framework.Page;

public class AppFrame extends Frame {
	private HasWidgets pageWrapper; 
	
	public AppFrame(HasWidgets container) {
		super(container);
		initFrame();
	}
	
	@Override
	protected void initFrame() {
		FlowPanel pageWrapperPanel = new FlowPanel();
		pageWrapperPanel.getElement().setId("page-wrapper");
		
		frameWrapper.add(new MenuComponent());
		frameWrapper.add(new HeaderComponent());
		frameWrapper.add(pageWrapperPanel);
		
		pageWrapper = pageWrapperPanel;
	}

	@Override
	public void showPage(Page page) {
		pageWrapper.clear();
		pageWrapper.add(page);
	}
}
