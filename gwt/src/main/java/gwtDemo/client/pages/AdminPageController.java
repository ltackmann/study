package gwtDemo.client.pages;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.PageController;
import gwtDemo.shared.domain.Role;

public class AdminPageController extends PageController<AdminPage> {
    public AdminPageController(AdminPage page, AppInjector injector) {
    	super(page, injector);
    }

	@Override
	public boolean isPageAllowedFor(Role role) {
		//return role == Role.GUEST || role == Role.ADMIN;
		return true;
	}
}
