package org.randompage.java7;

import junit.framework.TestCase;

public class DataTypes extends TestCase {
	private static final double nonStrictFloat = Math.PI * Math.E * (1.0 / 3.0);

	public void testLong() {
		// representing longs in hex, decimal and binary notation
		final long hexNum = 0xFFFF;
		final long longNum = 65535L;
		final long binNum = (long) 0b1111_1111_1111_1111;

		
		assertEquals(hexNum, longNum);
		assertEquals(binNum, longNum);
	}

	public void testUnicode() {
		// unicode
		final String integers = "\uD835\uDD6B";
		System.out.println(integers);
		
		// TODO add a assert here that this is indeed the integer symbol
		assertTrue(false);
	}

	/*
	 * strictfp ensures that floating point operations are portable by making
	 * sure they are restricted to 64bit JVM precision rather than processor
	 * specific float registers (80bit on x86)
	 */
	public strictfp void testStrictFP() {
		final double strictFloat = Math.PI * Math.E * 1.0 / 3.0;
		assertTrue(nonStrictFloat != strictFloat);
	}
	
	public void testRounding() {
		final double val = 9.9;
		
		// discards fractional part
		int nine = (int) val;
		assertEquals(9, nine);
		
		// rounds to nearest integer
		int ten = (int) Math.round(val);
		assertEquals(10, ten);
	}
}
