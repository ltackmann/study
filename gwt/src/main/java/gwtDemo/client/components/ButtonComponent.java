package gwtDemo.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;

import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.Component;

public class ButtonComponent extends Component {
	private final ButtonEventHandler eventHandler;
	
    public ButtonComponent(String text, ButtonEventHandler eventHandler) {
    	super("button");
    	this.eventHandler = eventHandler;
    	initComponent();
    	setText(text);
    }
    
    @Override
	public void initComponent() {
    	setClassName("ui-button");
    	sinkEvents(Event.ONCLICK);
    	addHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventHandler.onClick(event, injector);
			}
    	}, ClickEvent.getType());
    }
    
    public void setText(String text) {
    	super.getElement().setInnerText(text);
    }
    
	interface ButtonEventHandler {
    	void onClick(ClickEvent event, AppInjector injector);
    }
}
