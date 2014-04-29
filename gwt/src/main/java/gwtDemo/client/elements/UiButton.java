package gwtDemo.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.Component;
import gwtDemo.client.framework.GwtLogic;
import gwtDemo.client.framework.Node;
import gwtDemo.client.framework.UiElement;

public class UiButton extends UiElement {
	private final ButtonEventHandler eventHandler;
	
    public UiButton(String text, ButtonEventHandler eventHandler) {
    	super("button");
    	this.eventHandler = eventHandler;
    	initElement();
    	setText(text);
    }
    
	private void initElement() {
    	addClassName("ui-button");
    	sinkEvents(Event.ONCLICK);
    	addHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventHandler.onClick(event, injector);
			}
    	}, ClickEvent.getType());
    }
    
	interface ButtonEventHandler {
    	void onClick(ClickEvent event, final GwtLogic logic);
    }
}
