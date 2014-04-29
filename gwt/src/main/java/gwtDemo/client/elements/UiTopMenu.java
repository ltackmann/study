package gwtDemo.client.elements;

import gwtDemo.client.framework.UiElement;
import gwtDemo.client.pages.AdminPage;
import gwtDemo.client.pages.MainPage;

public class UiTopMenu extends UiElement {
	public UiTopMenu() {
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
