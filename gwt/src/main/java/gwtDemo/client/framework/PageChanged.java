package gwtDemo.client.framework;

import com.google.gwt.event.shared.GwtEvent;

public class PageChanged extends GwtEvent<PageChangedHandler> {
	public static final Type<PageChangedHandler> TYPE = new Type<PageChangedHandler>();
	private final PageController<?> pageController;
	
	public PageChanged(PageController<?> pageController) {
		this.pageController = pageController;
	}

	@Override
	public Type<PageChangedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PageChangedHandler handler) {
		handler.onPageChanged(this);
	}

	public PageController<?> getPageController() {
		return pageController;
	}
}
