package gwtDemo.client.app;

import gwtDemo.client.components.TopMenu;
import gwtDemo.client.elements.UiDiv;
import gwtDemo.client.framework.AppFrame;
import gwtDemo.client.framework.Node;
import gwtDemo.client.framework.Page;

public class DemoAppFrame extends AppFrame {
	private Node pageWrapper;
	
	@Override
	public Node buildFrame() {
		Node frame = new UiDiv();
		
		Node container = new UiDiv();
		container.setId("ui-container");
		container.add(new TopMenu());
		
		pageWrapper = new UiDiv();
		pageWrapper.setId("ui-page-wrapper");
		container.add(pageWrapper);
		
		frame.add(container);
		
		return frame;
	}

	@Override
	public void showPage(Page page) {
		pageWrapper.clear();
		pageWrapper.add(page);
	}
}
