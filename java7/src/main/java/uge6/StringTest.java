package uge6;

import org.testng.annotations.Test;

public class StringTest {
	@Test
	public void stringCharTest() {
		System.out.println("H" + "a");
		System.out.println('H' + 'a');
	}
	
	
	@Test
	public void stringValueOfTest() {
		String letters = "ABC";
		char[] numbers = { '1', '2', '3' };
		System.out.println(letters + " easy as " + numbers);
	}
	
	final String pig = "length: 10";
	final String dog = "length: " + pig.length();
	
	@Test
	public void constantPoolTest() {
		System.out.println("equals: " + (pig == dog));
	}
	
	@Test
	public void bytePrintTest() {
		byte[] bytes = new byte[256];
		for(int i = 0; i< 256; i++) 
			bytes[i] = (byte)i;
		String str = new String(bytes);
		for(int i=0, n=str.length(); i<n; i++)
			System.out.println((int)str.charAt(i) + " at index: " + i + " ");
	}
}
