package lang;

import java.lang.reflect.Field;

import junit.framework.TestCase;
import lang.classes.ClassTest;

public class AccessTest extends TestCase {
	// test that we cannot circumvent access rules to non public code
	public void testNonPublicAccessCannotBeCircumvented() throws Exception {
		ClassTest clazzTest = new ClassTest();
		Object o = clazzTest.getNonPublicInterface1();
		assertNotNull(o);

		boolean caughtException = false;
		try {
			Field nonPublicfield = o.getClass().getField("name");
			nonPublicfield.get(new String());
		} catch (IllegalAccessException e) {
			caughtException = true;
		}
		assertTrue(caughtException);
	}

	// TODO are there other ways through reflection at actually getting at the
	// non public member
}
