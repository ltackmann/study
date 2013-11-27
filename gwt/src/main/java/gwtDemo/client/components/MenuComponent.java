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
		list.add(new LinkButtonComponent(AdminPage.class));
		list.add(new LinkButtonComponent(MainPage.class));
		
		add(list);
	}
}
