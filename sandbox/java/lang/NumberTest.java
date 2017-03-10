package lang;

import java.math.BigDecimal;

import org.testng.annotations.Test;

@Test
public class NumberTest  {
	public void testHex() {
		System.out.println(0xcafebabe);
	}

	public void testMultiCast() {
		System.out.println((int) (char) (byte) -1);
	}

	public void testReverseMultiCase() {
		short s = 148;
		// (byte) 1001 0100 = -(110 1011 + 1) = -1101100 = -108)
		System.out.println((byte) s);
	}

	public void testIEE754() {
		System.out.println(2.00 - 1.10);
	}

	public void testBigInteger() {
		System.out.println(new BigDecimal(2.00).subtract(new BigDecimal(1.10)));
	}

	public void testBigDecimalStringConstructor() {
		System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.10")));
	}
	
	public void testDivision() {
		final long MICORS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
		final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
		System.out.println(MICORS_PER_DAY / MILLIS_PER_DAY);
	}
}
