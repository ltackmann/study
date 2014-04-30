package gwtDemo.client.framework;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

public abstract class AppEntryPoint extends AbstractGwtLogic implements EntryPoint {
	@Override
	public void onModuleLoad() {
		final AppFrame frame = get(AppFrame.class);
		final AppController controller = get(AppController.class);
		
	    GWT.setUncaughtExceptionHandler(new GwtExceptionHandler());
	    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	        @Override
	        public void execute() {
	        	startApplication(frame, controller);
	        }
	    });
	}
	
	protected abstract void startApplication(AppFrame frame, AppController controller);
}