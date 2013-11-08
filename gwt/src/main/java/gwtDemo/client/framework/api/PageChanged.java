package gwtDemo.client.framework.api;

import com.google.gwt.event.shared.GwtEvent;

public class PageChanged<P extends Page> extends GwtEvent<PageChangedHandler> {
	public static final Type<PageChangedHandler> TYPE = new Type<PageChangedHandler>();
	private final Class<P> pageType;
	
	public PageChanged(Class<P> pageType) {
		this.pageType = pageType;
	}

	@Override
	public Type<PageChangedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PageChangedHandler handler) {
		handler.onPageChanged(this);
	}

	public Class<P> getPageType() {
		return pageType;
	}
}
