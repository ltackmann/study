package co.tackmann.vaadin.ui.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class StartView extends VerticalLayout implements View {
	private final Navigator navigator;
	
	public StartView(Navigator navigator) {
		this.navigator = navigator;
		addComponent(new Label("Loading..."));
	}

	@Override
	public void enter(ViewChangeEvent event) {
		navigator.navigateTo("main");
	}
}
