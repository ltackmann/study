package gwtDemo.client.components;

import com.google.gwt.user.client.ui.Label;

import gwtDemo.client.framework.Component;

public class MenuComponent extends Component {
	public MenuComponent() {
		super("menu");
		initComponent();
    }

	@Override
	public void initComponent() {
		add(new Label("menu"));
		// TODO Auto-generated method stub
	}
}
