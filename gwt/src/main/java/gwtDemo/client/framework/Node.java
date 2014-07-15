package gwtDemo.client.framework;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class Node extends AbstractGwtLogicAware implements IsWidget {
	// package private so only accessible inside the framework
	final Element wrapped;
	
	public Node(String tag) {
		this(new HTMLPanel(tag, "").getElement());
	}
	
	public Node(Element element) {
		this.wrapped = element;
	}
	
	public Node add(Node node) {
		wrapped.appendChild(node.wrapped);
		return this;
	}
	
	public void addClassName(String name) {
		wrapped.addClassName(name);
	}
	
	@Override
	public Widget asWidget() {
		return HTMLPanel.wrap(wrapped);
	}
	
	/**
	 * Clear child elements from the current node
	 */
	public void clear() {
		wrapped.setInnerHTML(null);
	}
	
	/**
	 * Retrieve the value of the named attribute from the current node.
	 */
	public String getAttribute(String name) {
		return getAttribute(name);
	}
	
	public String getId() {
		return wrapped.getId();
	}
	
	public Node getParent() {
		return new Node(wrapped.getParentElement());
	}
	
	public NodeStream<Event> onChange() {
		// TODO http://stackoverflow.com/questions/16542266/handler-on-dom-elements-in-gwt
		return null;
	}
	
    public Node query(String query) {
    	// TODO wrap GQuery
    	return null;
    }
    
    /**
     * Remove node from DOM
     */
    public void removeFromParent() {
    	wrapped.removeFromParent();
    }
    
    /**
     * Set 
     * @param name
     * @param value
     */
    public void setAttribute(String name, String value) {
    	wrapped.setAttribute(name, value);
    }
    
	public void setId(String id) {
		wrapped.setId(id);
	}
    
    public void setText(String text) {
    	wrapped.setInnerText(text);
    }
}
