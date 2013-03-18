package uge6;

import org.testng.annotations.Test;

public class ClassTest {
	@Test
	public void precedensTest() {
		System.out.println(X.Y.Z);
	}
}

class X {
	static class Y {
		static String Z = "black";
	}

	static C Y = new C();
}

class C {
	String Z = "White";
}