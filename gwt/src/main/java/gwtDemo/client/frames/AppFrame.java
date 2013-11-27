package gwtDemo.client.frames;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;

import gwtDemo.client.components.HeaderComponent;
import gwtDemo.client.components.MenuComponent;
import gwtDemo.client.framework.Frame;
import gwtDemo.client.framework.Page;

public class AppFrame extends Frame {
	private Panel pageWrapper;
	
	public AppFrame(HasWidgets container) {
		super(container);
		initFrame();
	}
	
	@Override
	protected void initFrame() {
		pageWrapper = new FlowPanel();
		pageWrapper.getElement().setId("ui-page-wrapper");
		FlowPanel containerPanel = new FlowPanel();
		containerPanel.getElement().setId("ui-container");
		
		containerPanel.add(new MenuComponent());
		containerPanel.add(pageWrapper);
		
		add(new HeaderComponent());
		add(containerPanel);
	}

	@Override
	public void showPage(Page page) {
		pageWrapper.clear();
		pageWrapper.add(page);
	}
}
