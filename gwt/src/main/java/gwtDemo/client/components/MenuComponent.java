package gwtDemo.client.components;

import gwtDemo.client.framework.Component;
import gwtDemo.client.pages.AdminPage;
import gwtDemo.client.pages.MainPage;

public class MenuComponent extends Component {
	public MenuComponent() {
		initComponent();
    }

	@Override
	public void initComponent() {
		setId("ui-menu");
		
		VerticalListComponent list = new VerticalListComponent();
		// TODO only add admin after login
		list.add(new LinkButtonComponent("Admin", AdminPage.class));
		list.add(new LinkButtonComponent("Main", MainPage.class));
		
		add(list);
	}
}
