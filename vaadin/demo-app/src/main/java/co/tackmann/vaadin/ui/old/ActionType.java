package co.tackmann.vaadin.ui.old;

public enum ActionType {
	SHOUT("Shout"), WISPER("Wisper");
	
	final String name;
	
	ActionType(String name) {
		this.name = name;
	}
}
