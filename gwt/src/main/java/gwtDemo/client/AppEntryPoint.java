package gwtDemo.client;

import gwtDemo.client.framework.AppController;
import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.NavigationManagerProvider;
import gwtDemo.client.framework.api.NavigationManager;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;

public class AppEntryPoint implements EntryPoint {
	private final AppInjector injector = GWT.create(AppInjector.class);
	
    @Override
	public void onModuleLoad() {
    	HasWidgets container = RootPanel.get();
    	NavigationManagerProvider.INSTANCE = new NavigationManager(container, injector);
    	
        // start application
        AppController appController = new AppController(injector);
        appController.start();
    }
}
