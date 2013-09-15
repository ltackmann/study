package co.tackmann.gwt.client.ui.presenter;

import com.google.web.bindery.event.shared.EventBus;

public class ContentPresenter {
	private final EventBus eventBus;
    private final Display display;
	
	public interface Display {
		
	}
	
	public ContentPresenter(EventBus eventBus, Display display) {
		this.eventBus = eventBus;
		this.display = display;
	}
}
