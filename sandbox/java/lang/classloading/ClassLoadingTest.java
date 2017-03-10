package lang.classloading;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import lang.classloading.utils.ConfigurableClassLoader;
import lang.classloading.utils.Init;
import lang.classloading.utils.LoadOrder;

// http://www.martinlippert.org/events/WJAX2008-ClassloadingTypeVisibilityOSGi.pdf
@SuppressWarnings("unused")
public class ClassLoadingTest {
	@BeforeClass
	public void runBefore() {
		prepareLoading();
	}

	// Demonstrate that static initializers are only run once
	@Test
	public void testLoadOrder() {
		// declaring a type of the class does not load anything
		Init init;
		assertEquals(0, LoadOrder.loadCount());

		// all initializers are run the first time we use the class
		init = new Init();
		assertEquals(5, LoadOrder.loadCount());

		// only instance initializers are run second time we use the class
		prepareLoading();
		Init other = new Init();
		printClassInfo(other);
		assertEquals(3, LoadOrder.loadCount());
	}

	// Demonstrate that static access forces static initializers to run
	@Test
	public void testStaticAccess() {
		String val = Init.static1;
		assertNotNull(val);
		assertEquals(2, LoadOrder.loadCount());
	}

	// Demonstrate that a class is actually first loaded when new is invoked
	@Test
	public void testInitializationPoint() throws Throwable {
		ClassLoader cl = this.getClass().getClassLoader();
		Class<?> clazz = cl.loadClass(Init.class.getName());

		// TODO assert that class is in classpath

		// adding class definition into the classpath does not initialize
		// anything
		assertEquals(0, LoadOrder.loadCount());

		clazz.newInstance();
		assertTrue(LoadOrder.loadCount() >= 3);
	}

	// Demonstrate that classes loaded in the same class loader hierarchy shares
	// static content and are considered equals
	@Test
	public void testParentClassLoaders() throws Throwable {
		ClassLoader parent = this.getClass().getClassLoader();
		ClassLoader myLoader = new ConfigurableClassLoader(parent);

		Object oParent1 = parent.loadClass(
				"lang.classloading.utils.Init").newInstance();
		Object oParent2 = parent.loadClass(
				"lang.classloading.utils.Init").newInstance();

		Object oLoader1 = myLoader.loadClass(
				"lang.classloading.utils.Init").newInstance();
		Object oLoader2 = myLoader.loadClass(
				"lang.classloading.utils.Init").newInstance();

		// 4 * 3 instance methods + 2 static methods are invoked by the above
		assertEquals(14, LoadOrder.loadCount());

		// classes loaded in the same class loader hierarchy are equals
		assertEquals(oParent1.getClass(), oParent2.getClass());
		assertEquals(oLoader1.getClass(), oLoader2.getClass());
		assertEquals(oLoader1.getClass(), oParent1.getClass());
		assertEquals(oLoader2.getClass(), oParent2.getClass());

		// casting works
		Init aInit1 = (Init) oParent1;
		Init aInit2 = (Init) oLoader1;

		// TODO is string constant pool also dependant of class signature ?
	}

	// Demonstrate that static content is reloaded in decoupled class loaders
	// and that class identity consists of the fully qualified name and
	// the class loader.
	@Test
	public void testDecoupledClassLoaders() throws Throwable {
		ClassLoader parent = this.getClass().getClassLoader();
		ClassLoader myLoader = new ConfigurableClassLoader();

		Object oParent1 = parent.loadClass(
				"lang.classloading.utils.Init").newInstance();
		Object oParent2 = parent.loadClass(
				"lang.classloading.utils.Init").newInstance();

		Object oLoader1 = myLoader.loadClass(
				"lang.classloading.utils.Init").newInstance();
		Object oLoader2 = myLoader.loadClass(
				"lang.classloading.utils.Init").newInstance();

		// 2 * 3 instance + 2 static's accessed in this class loader
		assertEquals(8, LoadOrder.loadCount());
		// but also 2 * 3 instance + 2 static's accessed in the other loader (16 in all)
		{
			Class<?> counterClass = myLoader.loadClass("lang.classloading.utils.LoadOrder");
	        Method countMethod = counterClass.getDeclaredMethod("loadCount");
	        assertEquals(8, (int) countMethod.invoke(null));
		}

		// classes loaded in the same class loader are equals
		assertEquals(oParent1.getClass(), oParent2.getClass());
		assertEquals(oLoader1.getClass(), oLoader2.getClass());

		// same class name loaded in different loaders are not equal
		assertNotSame(oLoader1.getClass(), oParent1.getClass());
		assertNotSame(oLoader2.getClass(), oParent2.getClass());

		// casting works for class loader used to construct this class
		Init aInit = (Init) oParent1;
		// but not for
		boolean caughtException = false;
		try {
			aInit = (Init) oLoader1;
		} catch (ClassCastException e) {
			caughtException = true;
		}
		assertTrue(caughtException);
	}
	
	@Test
	public void testClassCaching() {
		// TODO class.forName caches in initiating classLoader.loadClass caches in defining
		// so if you have a parent and two children then caching and equality will be different 
		// based on how you do it
	}

	// Demonstrate difference between AppClassLoader, ContextClassLoader
	@Test
	public void testClassLoaderTypes() {
		String aString = new String("test");
		System.out.println(aString.getClass());
	}

	// TODO demo class loading with inheritance

	private static void printClassInfo(Object o) {
		System.out.println("\nclass loader for: " + o.getClass().getName()
				+ " was: " + o.getClass().getClassLoader());
	}

	private static void prepareLoading() {
		LoadOrder.reset();
	}
}
