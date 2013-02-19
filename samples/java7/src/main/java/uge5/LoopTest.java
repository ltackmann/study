package uge5;

import org.testng.annotations.Test;

public class LoopTest {
	@Test
	public void testIncrement() {
		int j = 0;
		for (int i = 0; i < 100; i++)
			j = j++;
		System.out.println(j);
	}
	
	@Test
	public void testShiftLoop() {
		int i = 0;
		while (-1 << i != 0)
			i++;
		System.out.println(i);
	}

	@Test
	public void testDoubleLoop() {
		double i = 1.0 / 0.0;
		while(i == i + 1) {
			System.out.println(i);
		}
	}
	
	@Test 
	public void testStringLoop() {
		String i = "C# rocks, Java sucks";
		while(i != i + 0) {
			System.out.println(i);
		}
	}
}
