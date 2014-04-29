package gwtDemo.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;

import gwtDemo.client.framework.UiElement;

public class UiToggleLink extends UiElement {
	private final ToggleLinkEventHandler eventHandler;
	private boolean active;
	
    public UiToggleLink(String text, ToggleLinkEventHandler toggleLinkEventHandler) {
    	super("a");
    	this.eventHandler = toggleLinkEventHandler;
    	initElement();
    	setText(text);
    }
    
	private void initElement() {
    	active = false;
    	addClassName("ui-toggle-link");
    	sinkEvents(Event.ONCLICK);
    	addHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(!active) {
					active = true;
					eventHandler.onToggle();
				} else {
					active = false;
					eventHandler.onUnToggle();
				}
			}
    	}, ClickEvent.getType());
    }

	interface ToggleLinkEventHandler {
		void onToggle();
		
		void onUnToggle();
    }
}
