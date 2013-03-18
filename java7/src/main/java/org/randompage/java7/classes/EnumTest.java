package org.randompage.java7.classes;

public enum EnumTest {
	BIG, HUGE, OVERWHELMING("OVERRIDING CONSTRUCTOR");
	String friendlyName;
	
	EnumTest(String s) {
		this.friendlyName = s;
	}
	
	EnumTest() {
		// name is name of ENUM and ordinal value is its position
		this.friendlyName = name() + ordinal();
	}
}
