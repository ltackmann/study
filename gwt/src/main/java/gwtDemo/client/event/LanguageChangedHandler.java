package gwtDemo.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface LanguageChangedHandler extends EventHandler {
    void onLanguageChanged(LanguageChanged event);
}