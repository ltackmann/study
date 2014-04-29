package gwtDemo.client.elements;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.Page;

import com.google.gwt.event.dom.client.ClickEvent;

/** Button that navigates to a page when clicked */
public class UiLinkButton extends UiButton {
	public UiLinkButton(String text, final Class<? extends Page> pageType) {
		super(text, new ButtonEventHandler() {
			@Override
			public void onClick(ClickEvent event, AppInjector injector) {
				injector.getNavigationManager().showPage(pageType);
			}
		});
    }
}
