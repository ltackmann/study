package gwtDemo.client.components;

import gwtDemo.client.framework.Component;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class VerticalListComponent extends Component {
	public VerticalListComponent() {
		super("ul");
		initComponent();
	}

	@Override
	protected void initComponent() {
		setClassName("ui-vertical-list");
	}
	
	@Override
	public void add(Widget widget) {
		HTMLPanel listEntry = new HTMLPanel("li","");
		listEntry.add(widget);
		super.add(listEntry);
	}
}
