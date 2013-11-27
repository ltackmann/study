package gwtDemo.client.components;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.Page;

import com.google.gwt.event.dom.client.ClickEvent;

public class LinkButtonComponent extends ButtonComponent {
	public LinkButtonComponent(String text, final Class<? extends Page> pageType) {
		super(text, new ButtonEventHandler() {
			@Override
			public void onClick(ClickEvent event, AppInjector injector) {
				injector.getNavigationManager().showPage(pageType);
			}
		});
    }
}
