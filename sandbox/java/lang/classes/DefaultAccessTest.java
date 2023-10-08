package lang.classes;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

public class DefaultAccessTest {
	@Test
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
	
	@Test
	public void testDefaultAccessToNonPublicInterfacesInSamePackage() {
		assertNotNull(NonPublicInterface1.name);
		assertNotNull(NonPublicInterface2.name);
	}
}
