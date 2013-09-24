package gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LanguageChangedEvent extends GwtEvent<LanguageChangedEventHandler> {
	 public static final Type<LanguageChangedEventHandler> TYPE = new Type<LanguageChangedEventHandler>();
   
	 private final String language;

    public LanguageChangedEvent(String language) {
        this.language = language;
    }

    @Override
    public Type<LanguageChangedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(LanguageChangedEventHandler handler) {
        handler.onLanguageChanged(this);
    }

    public String getLanguage() {
        return language;
    }
}