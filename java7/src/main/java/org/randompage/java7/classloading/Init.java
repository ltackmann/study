package org.randompage.java7.classloading;

/*
 * Demonstrates order of initialization, static blocks goes first in the order
 * they are listed in followed by initialization of any instance variables (in
 * this case blocks of code) finally followed by the constructor.
 * 
 * Notice that the static and initialization blocks are not able to use
 * instance/static variables declared after them, mirroring the behavior found
 * inside in a method where everything must be defined before its use (but
 * unlike the method concept inside a class where method B can call method C
 * defined after it)
 * 
 * Notice how if you run make multiple instance of the class then static blocks
 * are only executed once in the same class loader 
 */
public class Init {

	public Init(int x) {
		System.out.println("1-arg const");
	}

	public Init() {
		System.out.println("no-arg const\n");
		// constructors are really methods on the class invoked automatically by
		// new
		// and thus can access any instance and static variables as they are
		// initialized
		// prior to the constructor running
		assert init1 != init2;

		// load order is used to keep count of the order in which things are
		// initialized
		LoadOrder.add(5);
	}

	public static String static1 = "1st static init";
	// this static block can access the static1 static variable but not the
	// static2 as its defined after
	static {
		System.out.println(static1);
		LoadOrder.add(1);
	}

	String init1 = "1st instance init";
	// this block can access the init1 instance variable but not the init2
	// variable as its defined after
	{
		System.out.println(init1);
		LoadOrder.add(3);
		// but it can access static content defined after since all static
		// initializers are run before instance initializers do
		assert static1 != static2;
	}

	String init2 = "2nd instance init";
	{
		System.out.println(init2);
		LoadOrder.add(4);
	}

	static String static2 = "2nd static init";
	static {
		System.out.println(static2);
		LoadOrder.add(2);
	}
}