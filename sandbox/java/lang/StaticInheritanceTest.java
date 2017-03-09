package lang;

public class StaticInheritanceTest {
	// --- note how inner classes with static methods must them self be static (as otherwise we have no static type to attach them to)
	
	// --- show how inheritance works with static methods on classes ---
	static class A {
		public static String val() {
			return "a";
		}
	}

	static class B extends A {
		public static String val() {
			return "b";
		}
	}

	@SuppressWarnings("static-access")
	public void staticClassTest() {
		A a = new A();
		assert a.val().equals("a");

		A ab = new B();
		assert ab.val().equals("a");

		B b = new B();
		assert b.val().equals("b");
	}

	// --- show how inheritance works with static values on interfaces ---
	static interface X {
		String val = "X";
	}

	static interface Y extends X {
		String val = "Y";
	}

	static class Z implements Y {
		String val = "Z";
	}

	@SuppressWarnings("static-access")
	public void staticInterfaceTest() {
		X x = new Z();
		assert x.val.equals("X");
		
		Y y = (Y) x;
		assert y.val.equals("Y");
		
		Z z = new Z();
		assert z.val.equals("Z");
	}

	// --------
	
	public static void main(String[] args) {
		StaticInheritanceTest test = new StaticInheritanceTest();
		
		test.staticClassTest();
		test.staticInterfaceTest();
		
		System.out.println("done");
	}
}
