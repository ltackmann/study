package org.randompage.java7;

import java.text.DecimalFormat;

public class Formatting {
	public static void main(String[] args) {
		double d = 2.0501;
		String formattedDouble = new DecimalFormat("#.##").format(d);
		assert formattedDouble.equals("2.05");
	}
}
