package gwtDemo.client.pages;

import com.google.gwt.user.client.ui.Label;

import gwtDemo.client.framework.Page;

// TODO add admin page to menu when user is logged in - remove when logged out
// TODO only admin can view admin-page
public class AdminPage extends Page {
	public AdminPage() {
		super("ui-admin-page");
		initPage();
	}
	
	@Override
	public void initPage() {
		add(new Label("admin page"));
	}
}
