package uge5;

import org.testng.annotations.Test;

public class ExceptionTest {
	@Test
	public void testFinallyException() {
		System.out.println(decision());
	}

	static boolean decision() {
		try {
			return true;
		} finally {
			System.out.println("hey");
		}
	}
	
	@Test
	public void testConstructorException() {
		try {
			ConstructMe c = new ConstructMe();
			System.out.println("hello world");
		} catch (Exception e) {
			System.out.println("goodbye world");
		}
	}
}

class ConstructMe {
	private ConstructMe instance = new ConstructMe();

	public ConstructMe() throws Exception {
		throw new Exception("not implemented yet");
	}
}
