package gwtDemo.client.elements;

import gwtDemo.client.framework.NavigationManager;
import gwtDemo.client.framework.Page;

import com.google.gwt.event.dom.client.ClickEvent;

/** Button that navigates to a page when clicked */
public class UiLinkButton extends UiButton {
	public UiLinkButton(String text, final Class<? extends Page> pageType) {
		super(text);
		setEventHandler(new ButtonEventHandler() {
			@Override
			public void onClick(ClickEvent event) {
				get(NavigationManager.class).showPage(pageType);
			}
		});
    }
}
