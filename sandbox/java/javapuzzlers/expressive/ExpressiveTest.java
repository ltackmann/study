package javapuzzlers.expressive;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

import javapuzzlers.expressive.puzzle1.Oddity;
import javapuzzlers.expressive.puzzle2.Change;
import javapuzzlers.expressive.puzzle3.LongDivision;
import javapuzzlers.expressive.puzzle4.Elementary;

public class ExpressiveTest {

	@Test
	public void puzzle1Test() {
		int[] values = { -3, -2, -1, 0, 1, 2, 3 };
		int numOdd = 0;
		int numEven = 0;
		for (int i : values) {
			// test broken implementation
			if (i < 0)
				assertFalse(Oddity.isOdd(i));
			// test fixed one
			if (Oddity.fixedOdd(i))
				numOdd++;
			else
				numEven++;

		}
		assertEquals(4, numOdd);
		assertEquals(3, numEven);
	}

	@Test
	public void puzzle2Test() {
		double res = 0.90;
		assertThat(Change.change(), is(not(res)));
		assertThat(Change.change() < res, is(true));
		assertThat(Change.fixedChange(), is(res));
	}

	@Test
	public void puzzle3Test() {
		long res = 1000;
		assertThat(LongDivision.divide(), is(not(res)));
		assertThat(LongDivision.divide(), is((long) 5));
		assertThat(LongDivision.fixedDivide(), is(res));
	}

	@Test
	public void puzzle4Test() {
		assertThat(Elementary.addition(), is(not((long)66666)));
		assertThat(Elementary.fixedAddition(), is((long)17777));
	}
	
	@Test
	public void puzzle5Test() {
		
	}
	
	@Test
	public void puzzle6Test() {
		
	}
}
