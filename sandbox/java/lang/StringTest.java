package lang;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

@Test
public class StringTest  {
	static final String integerSymbol = "𝕫";

	public void testUTF8() {
		System.out.println(String.format("symbol: %s with length %d",
				integerSymbol, integerSymbol.length()));

		final String integerSymbol2 = "\uD835\uDD6B";
		assertEquals(integerSymbol, integerSymbol2);
	}
	
	public void testCodePoints() {
		final String aMixedString = "𝕫æ1𝕫2ø4";
		System.out.println(aMixedString.length());
		
		System.out.println("===== CHAR PRINTING =====");
		for(int j = 0; j<aMixedString.length(); j++) {
			System.out.println(aMixedString.charAt(j));
		}
	
		System.out.println("===== CODE POINT PRINTING =====");
		
		int i = 0;
		while (i < aMixedString.length()) {
			int cp = aMixedString.codePointAt(i);
			int step = 1;
			if (Character.isSupplementaryCodePoint(cp)) {
				step = 2;
			}
			System.out.println(aMixedString.substring(i, i+step));
			i += step;
		}
	}

	public void testStringReferences() {
		String greeting = "Hello";
		if (greeting == "Hello") {
			// shared in string constant pool
			System.out.println("probably true");
		}
		if (greeting.substring(0, 3) == "Hel") {
			// only string constants are shared, not results of operations
			System.out.println("probably false");
		}
	}
}
