package uge5;

import org.testng.annotations.Test;

public class CompoundTest {
	@Test
	public void compoundTest() {
		short x = 0;
		int i = 123456;
		
		//x = x + i;
		x += i;
		
		System.out.println(x);
	}
}
