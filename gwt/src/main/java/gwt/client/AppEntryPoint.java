package gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;

public class AppEntryPoint implements EntryPoint {
	private final AppInjector injector = GWT.create(AppInjector.class);
	
    @Override
	public void onModuleLoad() {
        // setup resources
        //Resources.INSTANCE.style().ensureInjected();
    	EventBus eventBus = injector.getEventBus();
        // start application
        AppController appController = new AppController(eventBus);
        appController.invoke(RootPanel.get());
    }
}
