package gwtDemo.client.ui.presenter;

import gwtDemo.client.framework.AppInjector;

import com.google.web.bindery.event.shared.EventBus;

public class ContentPresenter {
	private final EventBus eventBus;
    private final Display display;
	
	public ContentPresenter(AppInjector injector, Display display) {
		this.eventBus = injector.getEventBus();
		this.display = display;
	}
	
	public interface Display {
		
	}
}
