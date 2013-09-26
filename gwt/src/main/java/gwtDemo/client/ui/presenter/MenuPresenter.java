package gwtDemo.client.ui.presenter;

import com.google.web.bindery.event.shared.EventBus;

public class MenuPresenter {
	private final EventBus eventBus;
    private final Display display;
	
	public interface Display {
		
	}
	
	public MenuPresenter(EventBus eventBus, Display display) {
		this.eventBus = eventBus;
		this.display = display;
	}
}
