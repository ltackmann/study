package gwtDemo.client.pages;

import gwtDemo.client.framework.PageController;
import gwtDemo.shared.domain.Role;

public class InputDemoPageController extends PageController<InputDemoPage> {

	public InputDemoPageController(InputDemoPage page) {
		super(page);
	}

	@Override
	public boolean isPageAllowedFor(Role role) {
		return true;
	}
}
