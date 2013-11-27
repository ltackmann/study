package gwtDemo.client.framework;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public abstract class Frame {
	private final Panel framePanel;
	
	public Frame(HasWidgets container) {
		framePanel = new FlowPanel();
		framePanel.getElement().setId("frame");
		container.add(framePanel);
	}
	
	protected void add(Widget widget) {
		framePanel.add(widget);
	}
	
	protected abstract void initFrame();
	
	public abstract void showPage(Page page);
}
