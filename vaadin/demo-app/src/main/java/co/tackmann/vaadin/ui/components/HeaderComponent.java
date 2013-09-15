package co.tackmann.vaadin.ui.components;

import com.vaadin.ui.ComboBox;
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
		titleLabel.setImmediate(true);
		layout.addComponent(titleLabel);
		// TODO center
		
		final ComboBox languageSelect = new ComboBox("Language");
		layout.addComponent(languageSelect);
		// TODO add languages and handle change
		// TODO move right
	}
	
	public void setTitle(String title) {
		ui.getPage().setTitle(title);
		titleLabel.setValue(title);
	}
}
