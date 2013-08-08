package co.tackmann;

public enum ActionType {
	NOTIFICATION("Notification"), FIELD("Field");
	
	final String name;
	
	ActionType(String name) {
		this.name = name;
	}
}
