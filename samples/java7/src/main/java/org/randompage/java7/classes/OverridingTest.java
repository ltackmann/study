package org.randompage.java7.classes;

import junit.framework.TestCase;


public class OverridingTest extends TestCase {
	public void testOverriding() {
		
	}
	
	// object type determines which override method is used
}

class ReturnType  {
	private final String val;
	
	public ReturnType(String val) {
		this.val = val;
	}
	
	public String getVal() {
		return val;
	}
}

class CovariantReturnType extends ReturnType {

	public CovariantReturnType(String val) {
		super("Covariant" + val);
	}
}

class Base {
	protected final ReturnType returnType = new ReturnType("base");
	
	protected ReturnType getReturnType() {
		return returnType;
	}
}

class Extender extends Base {
	// shadow 
	 final ReturnType returnType = new ReturnType("extender");
}