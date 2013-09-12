package co.tackmann.vaadin.ui.components;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class HeaderComponent extends CustomComponent {
	private final HorizontalLayout layout = new HorizontalLayout();
	
	public HeaderComponent() {
		setCompositionRoot(layout);
	}
}
