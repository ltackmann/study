package co.tackmann.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class BookmarkingEntryPoint implements EntryPoint {
    @Override
	public void onModuleLoad() {
        // setup resources
        Resources.INSTANCE.style().ensureInjected();
        // start application
        BookmarkingController appController = new BookmarkingController(EventBus.INSTANCE);
        appController.invoke(RootPanel.get());
    }
}
