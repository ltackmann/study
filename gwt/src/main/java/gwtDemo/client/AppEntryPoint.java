package gwtDemo.client;

import gwtDemo.client.framework.AppController;
import gwtDemo.client.framework.AppExceptionHandler;
import gwtDemo.client.framework.AppInjector;
import gwtDemo.client.framework.ClientSession;
import gwtDemo.client.framework.ClientSessionProvider;
import gwtDemo.client.framework.NavigationManager;
import gwtDemo.client.framework.NavigationManagerProvider;
import gwtDemo.shared.domain.Role;
import gwtDemo.shared.domain.User;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class AppEntryPoint implements EntryPoint {
	@Override
	public void onModuleLoad() {
	    GWT.setUncaughtExceptionHandler(new AppExceptionHandler());
	    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	        @Override
	        public void execute() {
	        	startApplication();
	        }
	    });
	}
	
	private void startApplication() {
		AppInjector injector = GWT.create(AppInjector.class);

		// initialize singleton providers
		NavigationManagerProvider.INSTANCE = new NavigationManager(RootPanel.get(), injector);
		ClientSessionProvider.INSTANCE = new ClientSession(new User(Role.GUEST), injector.getEventBus());

		// start application
		AppController appController = new AppController(injector);
		appController.start();
	}
}
