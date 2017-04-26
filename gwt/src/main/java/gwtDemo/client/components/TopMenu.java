package gwtDemo.client.components;

import gwtDemo.client.elements.UiLinkButton;
import gwtDemo.client.elements.UiVerticalList;
import gwtDemo.client.framework.Component;
import gwtDemo.client.pages.AdminPage;
import gwtDemo.client.pages.MainPage;

public class TopMenu extends Component {
	public TopMenu() {
		super("div");
		initComponent();
    }

	private void initComponent() {
		setId("ui-menu");
		
		UiVerticalList list = new UiVerticalList();
		// TODO only add admin after login
		list.add(new UiLinkButton("Admin", AdminPage.class));
		list.add(new UiLinkButton("Main", MainPage.class));
		
		add(list);
	}
}
