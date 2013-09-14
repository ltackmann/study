package lang.classes;

import junit.framework.TestCase;

public class DefaultAccessTest extends TestCase {
	public void testDefaultAccessToNonPublicClassesInSamePackage() {
		// grab non public classes from same package
		NonPublicClass1 np1 = new NonPublicClass1();
		NonPublicClass2 np2 = new NonPublicClass2();

		assertNotNull(np1);
		assertNotNull(np2);

		// we also have access to default and protected members we can do this
		// equal comparison as class members are initialized to default value
		assertEquals(np1.name, np2.name);
	}
	
	public void testDefaultAccessToNonPublicInterfacesInSamePackage() {
		assertNotNull(NonPublicInterface1.name);
		assertNotNull(NonPublicInterface2.name);
	}
}
