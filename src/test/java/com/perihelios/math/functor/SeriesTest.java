package com.perihelios.math.functor;

import org.junit.Test;

import java.math.BigInteger;

import static com.perihelios.math.functor.Series.arithmeticSeries1toN;
import static com.perihelios.math.functor.Series.arithmeticSeriesNtoM;
import static java.math.BigInteger.valueOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class SeriesTest {
	@Test
	public void arithmeticSeries() {
		assertThat(arithmeticSeriesNtoM(valueOf(1), valueOf(1), valueOf(1)), is(valueOf(1)));
		assertThat(arithmeticSeriesNtoM(valueOf(1), valueOf(1), valueOf(100)), is(valueOf(1)));
		assertThat(arithmeticSeriesNtoM(valueOf(1), valueOf(100), valueOf(1)), is(valueOf(5050)));
		assertThat(arithmeticSeriesNtoM(valueOf(1), valueOf(10), valueOf(2)), is(valueOf(25)));
		assertThat(arithmeticSeriesNtoM(valueOf(1), valueOf(10), valueOf(3)), is(valueOf(22)));
		assertThat(arithmeticSeriesNtoM(valueOf(50), valueOf(100), valueOf(50)), is(valueOf(150)));
		assertThat(arithmeticSeriesNtoM(valueOf(51), valueOf(100), valueOf(50)), is(valueOf(51)));
		assertThat(arithmeticSeriesNtoM(valueOf(1), valueOf(1_000_000_000_000L), valueOf(1)), is(new BigInteger("500000000000500000000000")));
	}

	@Test
	public void arithmeticSeries_n_minimum() {
		try {
			arithmeticSeriesNtoM(valueOf(0), valueOf(1), valueOf(1));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Starting value, n, must be 1 or greater; got 0"));
		}
	}

	@Test
	public void arithmeticSeries_n_m_order() {
		try {
			arithmeticSeriesNtoM(valueOf(2), valueOf(1), valueOf(1));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Starting value, n, must be less than or equal to ending value, m; got n=2, m=1"));
		}
	}

	@Test
	public void arithmeticSeries_step_minimum() {
		try {
			arithmeticSeriesNtoM(valueOf(1), valueOf(1), valueOf(0));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Step must be 1 or greater; got 0"));
		}
	}

	@Test
	public void arithmeticSeriesNtoM_no_step_BigInteger() {
		assertThat(arithmeticSeriesNtoM(valueOf(1), valueOf(100)), is(valueOf(5050)));
	}

	@Test
	public void arithmeticSeries1toN_no_step_BigInteger() {
		assertThat(arithmeticSeries1toN(valueOf(100)), is(valueOf(5050)));
	}

	@Test
	public void arithmeticSeries1toN_step_BigInteger() {
		assertThat(arithmeticSeries1toN(valueOf(100), valueOf(2)), is(valueOf(2500)));
	}

	@Test
	public void arithmeticSeriesNtoM_step_long() {
		assertThat(arithmeticSeriesNtoM(1L, 100L, 2L), is(2500L));
	}

	@Test
	public void arithmeticSeriesNtoM_long_return_maximum() {
		try {
			arithmeticSeriesNtoM(1L, Long.MAX_VALUE, Long.MAX_VALUE - 1);
			fail("Expected exception " + ArithmeticException.class.getName());
		} catch (ArithmeticException expected) {
			assertThat(expected.getMessage(), is("Result of arithmetic series from 1 to " + Long.MAX_VALUE +
				" with step of " + (Long.MAX_VALUE - 1) + " would overflow long type"));
		}
	}

	@Test
	public void arithmeticSeriesNtoM_no_step_long() {
		assertThat(arithmeticSeriesNtoM(1L, 100L), is(5050L));
	}

	@Test
	public void arithmeticSeries1toN_no_step_long() {
		assertThat(arithmeticSeries1toN(100L), is(5050L));
	}

	@Test
	public void arithmeticSeries1toN_step_long() {
		assertThat(arithmeticSeries1toN(100L, 2L), is(2500L));
	}
}
