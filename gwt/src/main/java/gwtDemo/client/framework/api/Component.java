package gwtDemo.client.framework.api;

import com.google.gwt.user.client.ui.Widget;

/**
 * Components are raw GUI constructs without URLs or security restriction. Pages
 * are build from one or more components. Components have Presenters which take
 * care of interacting with the backend. 
 */
public interface Component {
	Widget asWidget();
}
