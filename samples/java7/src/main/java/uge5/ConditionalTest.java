package uge5;

import org.testng.annotations.Test;

public class ConditionalTest {
	@Test
	public void testConditional() {
		char x = 'X';
		int i = 0;
		System.out.println(true ? x : 0);
		System.out.println(false ? i : x);
	}
}
