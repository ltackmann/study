package gwtDemo.client.framework.api;

import com.google.gwt.event.shared.EventHandler;

public interface PageChangedHandler extends EventHandler {
	void onPageChanged(PageChanged<Page> event);
}
