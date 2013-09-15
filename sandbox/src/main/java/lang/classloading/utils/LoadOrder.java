package lang.classloading.utils;

import java.util.LinkedList;
import java.util.List;

//
public class LoadOrder {
	// notice how a static variable is able to call a method defined after it
	// (unlike it not being able to access a variable defined after it)
	private static List<Integer> loadOrder = getEmptyList();

	public static int loadCount() {
		return loadOrder.size();
	}

	public static void add(Integer place) {
		try {
			throw new RuntimeException();
		} catch(RuntimeException e) {
			StackTraceElement[] stackTrace = e.getStackTrace();
			StackTraceElement calledFrom = stackTrace[1];
			loadOrder.add(calledFrom.getLineNumber());
		}
	}

	public static void reset() {
		loadOrder = getEmptyList();
	}

	private static List<Integer> getEmptyList() {
		return new LinkedList<Integer>();
	}
}