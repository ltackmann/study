package gwtDemo.client.framework;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;

public abstract class Frame {
	protected HasWidgets frameWrapper;
	
	public Frame(HasWidgets container) {
		FlowPanel frameWrapperWidget = new FlowPanel();
		frameWrapperWidget.getElement().setId("frame");
		container.add(frameWrapperWidget);
		
		frameWrapper = frameWrapperWidget;
	}
	
	protected abstract void initFrame();
	
	public abstract void showPage(Page page);
}
