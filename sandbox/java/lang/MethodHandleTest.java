package lang;

import java.lang.invoke.MethodType;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Closures via method handles
 * 
 * http://weblogs.java.net/blog/2009/01/02/method-handles-closures
 * 
 * Run: mvn clean compile exec:java
 * -Dexec.mainClass="org.randompage.java7.MethodHandleTest"
 */
public class MethodHandleTest {
	public static void each(Collection<?> c, MethodHandle mh) throws Throwable {
		for (Object element : c) {
			mh.invoke(element);
		}
	}

	public static void sayHello(Object message) {
		System.out.println("hello " + message);
	}

	public static void main(String[] args) throws Throwable {
		MethodType mt = MethodType.methodType(void.class, Object.class);
		MethodHandle function = MethodHandles.lookup().findStatic(MethodHandleTest.class, "sayHello", mt);

		List<String> list = Arrays.asList("davinci", "closure");
		each(list, function);
	}
}
