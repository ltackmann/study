package gwtDemo.client.elements;

import gwtDemo.client.framework.UiElement;

public class UiInput extends UiElement {
	public UiInput(InputType type) {
		this(type.typeName);
	}
	
	public UiInput(String type) {
		this();
		setAttribute("type", type);
	}
	
	public UiInput() {
		super("input");
	}
	
	public enum InputType {
		DATE("date"), EMAIL("email"), PASSWORD("password"), TEXT("text");
		
		final String typeName;
		
		InputType(String typeName) {
			this.typeName = typeName;
		}
	}
}
