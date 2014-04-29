package gwtDemo.client.framework;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;

public abstract class Node {
	// package private so only accessible inside the framework
	final Panel wrapped;
	
	public Node(String tag) {
		this(new HTMLPanel(tag, ""));
	}
	
	public Node(Panel panel) {
		this.wrapped = panel;
	}
	
	public void addClassName(String name) {
		getElement().addClassName(name);
	}
	
	public void clear() {
		wrapped.clear();
	}
    
    public Node query(String query) {
    	// TODO wrap GQuery
    	return null;
    }
    
	public void setId(String id) {
		getElement().setId(id);
	}
	
    public void setText(String text) {
    	getElement().setInnerText(text);
    }
    
    public void setAttribute(String name, String value) {
    	getElement().setAttribute(name, value);
    }
    
    public void sinkEvents(int eventBitsToAdd) {
    	wrapped.sinkEvents(eventBitsToAdd);
    }
    
    public <H extends EventHandler> HandlerRegistration addHandler(H handler, GwtEvent.Type<H> type) {
		return wrapped.addHandler(handler, type);	
	}
	
	private Element getElement() {
		return wrapped.getElement();
	}
}
