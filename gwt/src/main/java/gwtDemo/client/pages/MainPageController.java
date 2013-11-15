package gwtDemo.client.pages;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.PageController;
import gwtDemo.shared.domain.Role;

public class MainPageController extends PageController<MainPage> {
    public MainPageController(MainPage page, AppInjector injector) {
    	super(page, injector);
    }

	@Override
	public boolean isPageAllowedFor(Role role) {
		return role == Role.GUEST || role == Role.ADMIN;
	}
}
