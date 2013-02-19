package com.javapuzzlers.expressive;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.javapuzzlers.expressive.puzzle1.Oddity;
import com.javapuzzlers.expressive.puzzle2.Change;
import com.javapuzzlers.expressive.puzzle3.LongDivision;
import com.javapuzzlers.expressive.puzzle4.Elementary;

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
		assertTrue(Change.change() < res);
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
	@Ignore
	public void puzzle6Test() {
		
	}
}
