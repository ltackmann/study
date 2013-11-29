package gwtDemo.client.components;

public class VerticalListComponent extends ListComponent {
	public VerticalListComponent() {
		super(ListType.UNOREDERED);
		initComponent();
	}

	private void initComponent() {
		addClassName("ui-vertical-list");
	}
}
