package gwtDemo.client.elements;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;

import gwtDemo.client.framework.UiElement;

public class UiButton extends UiElement {
	private ButtonEventHandler eventHandler;
	
    public UiButton(String text, ButtonEventHandler eventHandler) {
    	this(text);
    	this.eventHandler = eventHandler;
    }
    
    public UiButton(String text) {
    	super("button");
    	initElement();
    	setText(text);
    }
    
    public void setEventHandler(ButtonEventHandler eventHandler) {
    	this.eventHandler = eventHandler;
    }
    
	private void initElement() {
    	addClassName("ui-button");
    	sinkEvents(Event.ONCLICK);
    	addHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventHandler.onClick(event);
			}
    	}, ClickEvent.getType());
    }
    
	public interface ButtonEventHandler {
    	void onClick(ClickEvent event);
    }
}
