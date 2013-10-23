package gwtDemo.client.ui.presenter;

import gwtDemo.client.AppInjector;

import com.google.web.bindery.event.shared.EventBus;

public class MenuPresenter {
	private final EventBus eventBus;
    private final Display display;
	
	public MenuPresenter(AppInjector injector, Display display) {
		this.eventBus = injector.getEventBus();
		this.display = display;
	}
	
	public interface Display {
		
	}
}
