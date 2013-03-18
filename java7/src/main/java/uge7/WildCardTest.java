package uge7;

import java.util.*;

public class WildCardTest {
	public static void main(String args[]) {
		List<String> listOfStrings = new ArrayList<String>();
		listOfStrings.add("foo");

		List<?> listOfAnything = listOfStrings;

		// retrieve the first element
		Object c = listOfAnything.get(0);

		// Add an Integer to b.
		//listOfAnything.add(new Integer(1));
	}
}
