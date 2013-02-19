package uge7;

import java.util.Arrays;
import java.util.List;

public class RawTest {
	public static void main(String[] args) {
		// javap -l -s -c -private RawTest
		Pair<?> p = new Pair<Object>(23, "skidoo");
		System.out.println(p.getFirst() + " " + p.getSecond());
		for(String s : p.stringList()) {
			System.out.println(s + " ");
		}
	}
}

class Pair<T> {
	private final T first;
	private final T second;
	
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}
	
    public List<String> stringList() {
    	return Arrays.asList(String.valueOf(first), String.valueOf(second));
    }
}


