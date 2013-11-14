package gwtDemo.client.components;

import gwtDemo.client.framework.api.Component;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MenuComponent extends Composite implements Component {
	public MenuComponent(String identifier) {
		VerticalPanel panel = new VerticalPanel();
		initWidget(panel);
    }

	@Override
	public void initComponent() {
		// TODO Auto-generated method stub
	}
}
