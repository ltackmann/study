package gwtDemo.client.pages;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.PageController;
import gwtDemo.shared.domain.Role;

public class InputDemoPageController extends PageController<InputDemoPage> {

	public InputDemoPageController(InputDemoPage page, AppInjector injector) {
		super(page, injector);
	}

	@Override
	public boolean isPageAllowedFor(Role role) {
		return true;
	}
}
