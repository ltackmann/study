package gwtDemo.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LanguageChanged extends GwtEvent<LanguageChangedHandler> {
	public static final Type<LanguageChangedHandler> TYPE = new Type<LanguageChangedHandler>();

	private final String language;

	public LanguageChanged(String language) {
		this.language = language;
	}

	@Override
	public Type<LanguageChangedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LanguageChangedHandler handler) {
		handler.onLanguageChanged(this);
	}

	public String getLanguage() {
		return language;
	}
}