package gwtDemo.client.components;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import gwtDemo.client.framework.Component;

public class ListComponent extends Component {
	public ListComponent(ListType listType) {
		super(listType.listTag);
		initComponent();
	}
	
	private void initComponent() {
		addClassName("ui-list");
	}
	
	@Override
	public void add(Widget widget) {
		HTMLPanel listEntry = new HTMLPanel("li","");
		listEntry.add(widget);
		super.add(listEntry);
	}
	
	enum ListType {
		ORDERED("ol"), UNOREDERED("ul");
		
		ListType(String tag) {
			this.listTag = tag;
		}
		
		final String listTag;
	}
}
