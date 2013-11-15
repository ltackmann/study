package gwtDemo.client.framework;

import com.google.gwt.event.shared.EventHandler;

public interface PageChangedHandler extends EventHandler {
	void onPageChanged(PageChanged event);
}
