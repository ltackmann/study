package co.tackmann.vaadin.ui;

public enum ActionType {
	NOTIFICATION("Notification"), FIELD("Field");
	
	final String name;
	
	ActionType(String name) {
		this.name = name;
	}
}
