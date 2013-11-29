package gwtDemo.client.components;

public class HorizontalListComponent extends ListComponent {
	public HorizontalListComponent() {
		super(ListType.UNOREDERED);
		initComponent();
	}

	private void initComponent() {
		addClassName("ui-horizontal-list");
	}
}
