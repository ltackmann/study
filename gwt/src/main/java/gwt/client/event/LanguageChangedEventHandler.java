package gwt.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface LanguageChangedEventHandler extends EventHandler {
    void onLanguageChanged(LanguageChangedEvent event);
}