package co.tackmann.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AccessGrantedEventHandler extends EventHandler {
    void onAccess(AccessGrantedEvent event);
}