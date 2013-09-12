package co.tackmann.vaadin.ui.components;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
public class HeaderComponent extends CustomComponent {
	private final UI ui;
	private Label titleLabel;
	
	public HeaderComponent(UI ui) {
		final HorizontalLayout layout = new HorizontalLayout();
		setCompositionRoot(layout);
		initLayout(layout);
		this.ui = ui;
	}
	
	private void initLayout(HorizontalLayout layout) {
		titleLabel = new Label();
		layout.addComponent(titleLabel);
		// TODO center
		titleLabel.setImmediate(true);
	}
	
	public void setTitle(String title) {
		ui.getPage().setTitle(title);
		titleLabel.setValue(title);
	}
}
