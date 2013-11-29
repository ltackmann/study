package gwtDemo.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;

import gwtDemo.client.framework.Component;

public class ToggleLinkComponent extends Component {
	private final ToggleLinkEventHandler eventHandler;
	private boolean active;
	
    public ToggleLinkComponent(String text, ToggleLinkEventHandler toggleLinkEventHandler) {
    	super("a");
    	this.eventHandler = toggleLinkEventHandler;
    	initComponent();
    	setText(text);
    }
    
	private void initComponent() {
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
    
    public void setText(String text) {
    	super.getElement().setInnerText(text);
    }
    
	interface ToggleLinkEventHandler {
		void onToggle();
		
		void onUnToggle();
    }
}
