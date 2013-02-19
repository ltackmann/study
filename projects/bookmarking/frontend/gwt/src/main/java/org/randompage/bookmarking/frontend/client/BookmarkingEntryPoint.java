package org.randompage.bookmarking.frontend.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import org.randompage.bookmarking.frontend.client.resource.Resources;

public class BookmarkingEntryPoint implements EntryPoint {
    public void onModuleLoad() {
        // setup resources
        Resources.INSTANCE.style().ensureInjected();
        // start application
        BookmarkingController appController = new BookmarkingController(EventBus.INSTANCE);
        appController.invoke(RootPanel.get());
    }
}
