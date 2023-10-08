package lang.classes;

import org.testng.annotations.Test;

@Test
public class ClassTest  {
	// try to expose non public interface through a public class
	public NonPublicInterface1 getNonPublicInterface1() {
		return new NonPublicInterface1() {
		};
	}
}

class NonPublicClass1 {
	protected String name;
}

// default access class
class NonPublicClass2 {
	// default access member
	String name;
}

interface NonPublicInterface1 {
	String name = "NonPublicInterface1";
}

interface NonPublicInterface2 {
	String name = "NonPublicInterface2";
}
