package javapuzzlers.expressive.puzzle2;

import java.math.BigDecimal;

public class Change {
	public static double change() {
		return (2.00 - 1.10);
	}

	public static double fixedChange() {
		BigDecimal x = new BigDecimal("2.00");
		BigDecimal y = new BigDecimal("1.10");
		return x.subtract(y).doubleValue();
	}
}
