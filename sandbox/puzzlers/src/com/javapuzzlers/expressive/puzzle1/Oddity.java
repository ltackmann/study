package com.javapuzzlers.expressive.puzzle1;

public class Oddity {
	public static boolean isOdd(int i) {
		return i % 2 == 1;
	}

	public static boolean fixedOdd(int i) {
		return i % 2 != 0;
	}
}
