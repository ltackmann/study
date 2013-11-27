package gwtDemo.client.components;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import gwtDemo.client.framework.Component;

public class HorizontalListComponent extends Component {
	public HorizontalListComponent() {
		super("ul");
		initComponent();
	}

	@Override
	protected void initComponent() {
		setClassName("ui-horizontal-list");
	}
	
	@Override
	public void add(Widget widget) {
		HTMLPanel listEntry = new HTMLPanel("li","");
		listEntry.add(widget);
		super.add(listEntry);
	}
}
