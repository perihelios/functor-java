package com.perihelios.math.functor;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RationalNumberTest {
	@Test
	public void constructor_reduces() {
		RationalNumber r;

		r = new RationalNumber(2, 2);
		assertThat(r.numerator(), is(1L));
		assertThat(r.denominator(), is(1L));

		r = new RationalNumber(4, 6);
		assertThat(r.numerator(), is(2L));
		assertThat(r.denominator(), is(3L));

		r = new RationalNumber(10000, 2000);
		assertThat(r.numerator(), is(5L));
		assertThat(r.denominator(), is(1L));
	}

	@Test
	public void add_long() {
		RationalNumber r;

		r = new RationalNumber(1, 1).add(1L);
		assertThat(r.numerator(), is(2L));
		assertThat(r.denominator(), is(1L));
		assertThat(r.isNegative(), is(false));

		r = new RationalNumber(1, 2).add(1L);
		assertThat(r.numerator(), is(3L));
		assertThat(r.denominator(), is(2L));
		assertThat(r.isNegative(), is(false));

		r = new RationalNumber(5, 3).add(2L);
		assertThat(r.numerator(), is(11L));
		assertThat(r.denominator(), is(3L));
		assertThat(r.isNegative(), is(false));

		r = new RationalNumber(5, 3).add(-2L);
		assertThat(r.numerator(), is(1L));
		assertThat(r.denominator(), is(3L));
		assertThat(r.isNegative(), is(true));

		r = new RationalNumber(-5, 3).add(-2L);
		assertThat(r.numerator(), is(11L));
		assertThat(r.denominator(), is(3L));
		assertThat(r.isNegative(), is(true));

		r = new RationalNumber(-5, 3).add(2L);
		assertThat(r.numerator(), is(1L));
		assertThat(r.denominator(), is(3L));
		assertThat(r.isNegative(), is(false));
	}

	@Test
	public void add_RationalNumber() {
		RationalNumber r;

		r = new RationalNumber(1, 1).add(new RationalNumber(1, 1));
		assertThat(r.numerator(), is(2L));
		assertThat(r.denominator(), is(1L));
		assertThat(r.isNegative(), is(false));

		r = new RationalNumber(1, 1).add(new RationalNumber(1, 2));
		assertThat(r.numerator(), is(3L));
		assertThat(r.denominator(), is(2L));
		assertThat(r.isNegative(), is(false));

		r = new RationalNumber(1, 2).add(new RationalNumber(1, 2));
		assertThat(r.numerator(), is(1L));
		assertThat(r.denominator(), is(1L));
		assertThat(r.isNegative(), is(false));

		r = new RationalNumber(1, 2).add(new RationalNumber(1, 3));
		assertThat(r.numerator(), is(5L));
		assertThat(r.denominator(), is(6L));
		assertThat(r.isNegative(), is(false));

		r = new RationalNumber(2, 1).add(new RationalNumber(-1, 3));
		assertThat(r.numerator(), is(5L));
		assertThat(r.denominator(), is(3L));
		assertThat(r.isNegative(), is(false));

		r = new RationalNumber(-3, 1).add(new RationalNumber(1, 4));
		assertThat(r.numerator(), is(11L));
		assertThat(r.denominator(), is(4L));
		assertThat(r.isNegative(), is(true));

		r = new RationalNumber(-2, 3).add(new RationalNumber(-3, 4));
		assertThat(r.numerator(), is(17L));
		assertThat(r.denominator(), is(12L));
		assertThat(r.isNegative(), is(true));
	}

	@Test
	public void reciprocal_works() {
		RationalNumber r;

		r = new RationalNumber(1, 2).reciprocal();
		assertThat(r.numerator(), is(2L));
		assertThat(r.denominator(), is(1L));
		assertThat(r.isNegative(), is(false));

		r = new RationalNumber(3, -17).reciprocal();
		assertThat(r.numerator(), is(17L));
		assertThat(r.denominator(), is(3L));
		assertThat(r.isNegative(), is(true));

		r = new RationalNumber(4, 6).reciprocal();
		assertThat(r.numerator(), is(3L));
		assertThat(r.denominator(), is(2L));
		assertThat(r.isNegative(), is(false));
	}

	@Test
	public void intValue_works() {
		assertThat(new RationalNumber(0, 1).intValue(), is(0));
		assertThat(new RationalNumber(1, 1).intValue(), is(1));
		assertThat(new RationalNumber(-1, 1).intValue(), is(-1));
		assertThat(new RationalNumber(1, -1).intValue(), is(-1));
		assertThat(new RationalNumber(-1, -1).intValue(), is(1));
		assertThat(new RationalNumber(1, 2).intValue(), is(0));
		assertThat(new RationalNumber(3, 4).intValue(), is(0));
		assertThat(new RationalNumber(4, 4).intValue(), is(1));
		assertThat(new RationalNumber(5, 4).intValue(), is(1));
		assertThat(new RationalNumber(7, 4).intValue(), is(1));
		assertThat(new RationalNumber(8, 4).intValue(), is(2));
		assertThat(new RationalNumber(Long.MAX_VALUE, 1).intValue(), is(-1));
	}
	
	@Test
	public void longValue_works() {
		assertThat(new RationalNumber(0, 1).longValue(), is(0L));
		assertThat(new RationalNumber(1, 1).longValue(), is(1L));
		assertThat(new RationalNumber(-1, 1).longValue(), is(-1L));
		assertThat(new RationalNumber(1, -1).longValue(), is(-1L));
		assertThat(new RationalNumber(-1, -1).longValue(), is(1L));
		assertThat(new RationalNumber(1, 2).longValue(), is(0L));
		assertThat(new RationalNumber(3, 4).longValue(), is(0L));
		assertThat(new RationalNumber(4, 4).longValue(), is(1L));
		assertThat(new RationalNumber(5, 4).longValue(), is(1L));
		assertThat(new RationalNumber(7, 4).longValue(), is(1L));
		assertThat(new RationalNumber(8, 4).longValue(), is(2L));
		assertThat(new RationalNumber(Long.MAX_VALUE, 1).longValue(), is(Long.MAX_VALUE));
	}
	
	@Test
	public void floatValue_works() {
		assertThat(new RationalNumber(0, 1).floatValue(), is(0f));
		assertThat(new RationalNumber(1, 1).floatValue(), is(1f));
		assertThat(new RationalNumber(-1, 1).floatValue(), is(-1f));
		assertThat(new RationalNumber(1, -1).floatValue(), is(-1f));
		assertThat(new RationalNumber(-1, -1).floatValue(), is(1f));
		assertThat(new RationalNumber(1, 2).floatValue(), is(0.5f));
		assertThat(new RationalNumber(3, 4).floatValue(), is(0.75f));
		assertThat(new RationalNumber(4, 4).floatValue(), is(1f));
		assertThat(new RationalNumber(5, 4).floatValue(), is(1.25f));
		assertThat(new RationalNumber(7, 4).floatValue(), is(1.75f));
		assertThat(new RationalNumber(8, 4).floatValue(), is(2f));
		assertThat(new RationalNumber(Long.MAX_VALUE, 1).floatValue(), is((float) Long.MAX_VALUE));
	}
	
	@Test
	public void doubleValue_works() {
		assertThat(new RationalNumber(0, 1).doubleValue(), is(0.0));
		assertThat(new RationalNumber(1, 1).doubleValue(), is(1.0));
		assertThat(new RationalNumber(-1, 1).doubleValue(), is(-1.0));
		assertThat(new RationalNumber(1, -1).doubleValue(), is(-1.0));
		assertThat(new RationalNumber(-1, -1).doubleValue(), is(1.0));
		assertThat(new RationalNumber(1, 2).doubleValue(), is(0.5));
		assertThat(new RationalNumber(3, 4).doubleValue(), is(0.75));
		assertThat(new RationalNumber(4, 4).doubleValue(), is(1.0));
		assertThat(new RationalNumber(5, 4).doubleValue(), is(1.25));
		assertThat(new RationalNumber(7, 4).doubleValue(), is(1.75));
		assertThat(new RationalNumber(8, 4).doubleValue(), is(2.0));
		assertThat(new RationalNumber(Long.MAX_VALUE, 1).doubleValue(), is((double) Long.MAX_VALUE));
	}

	@Test
	public void isInteger_works() {
		assertThat(new RationalNumber(0, 1).isInteger(), is(true));
		assertThat(new RationalNumber(1, 1).isInteger(), is(true));
		assertThat(new RationalNumber(1, 2).isInteger(), is(false));
		assertThat(new RationalNumber(3, 4).isInteger(), is(false));
		assertThat(new RationalNumber(4, 4).isInteger(), is(true));
		assertThat(new RationalNumber(5, 4).isInteger(), is(false));
		assertThat(new RationalNumber(7, 4).isInteger(), is(false));
		assertThat(new RationalNumber(8, 4).isInteger(), is(true));
		assertThat(new RationalNumber(Long.MAX_VALUE, 1).isInteger(), is(true));
	}

	@Test
	public void isNegative_works() {
		assertThat(new RationalNumber(0, 1).isNegative(), is(false));
		assertThat(new RationalNumber(0, -1).isNegative(), is(false));
		assertThat(new RationalNumber(-1, -1).isNegative(), is(false));
		assertThat(new RationalNumber(1, -1).isNegative(), is(true));
		assertThat(new RationalNumber(-1, 1).isNegative(), is(true));
		assertThat(new RationalNumber(1, 1).isNegative(), is(false));
	}

	@Test
	public void signum_works() {
		assertThat(new RationalNumber(0, 1).signum(), is(1L));
		assertThat(new RationalNumber(0, -1).signum(), is(1L));
		assertThat(new RationalNumber(1, 2).signum(), is(1L));
		assertThat(new RationalNumber(1, -2).signum(), is(-1L));
		assertThat(new RationalNumber(-1, 2).signum(), is(-1L));
		assertThat(new RationalNumber(-1, -2).signum(), is(1L));
	}

	@Test
	public void toString_works() {
		assertThat(new RationalNumber(1, 1).toString(), is("1"));
		assertThat(new RationalNumber(-1, 1).toString(), is("-1"));
		assertThat(new RationalNumber(-14, 2).toString(), is("-7"));
		assertThat(new RationalNumber(2, 14).toString(), is("1/7"));
		assertThat(new RationalNumber(2, -3).toString(), is("-2/3"));
		assertThat(new RationalNumber(-2, 3).toString(), is("-2/3"));
	}

	@Test
	public void compareTo_works() {
		assertThat(new RationalNumber(1, 1).compareTo(new RationalNumber(-1, 1)), is(1));
		assertThat(new RationalNumber(-1, 1).compareTo(new RationalNumber(1, 1)), is(-1));
		assertThat(new RationalNumber(1, 1).compareTo(new RationalNumber(1, 1)), is(0));
		assertThat(new RationalNumber(2, 1).compareTo(new RationalNumber(1, 1)), is(1));
		assertThat(new RationalNumber(1, 1).compareTo(new RationalNumber(2, 1)), is(-1));
		assertThat(new RationalNumber(1, 1).compareTo(new RationalNumber(1, 2)), is(1));
		assertThat(new RationalNumber(1, 2).compareTo(new RationalNumber(1, 1)), is(-1));
		assertThat(new RationalNumber(1, 2).compareTo(new RationalNumber(1, 3)), is(1));
		assertThat(new RationalNumber(1, 3).compareTo(new RationalNumber(1, 2)), is(-1));
		assertThat(new RationalNumber(10, 11).compareTo(new RationalNumber(11, 12)), is(-1));
		assertThat(new RationalNumber(10, 11).compareTo(new RationalNumber(-11, 12)), is(1));
		assertThat(new RationalNumber(11, 12).compareTo(new RationalNumber(10, 11)), is(1));
		assertThat(new RationalNumber(-11, 12).compareTo(new RationalNumber(10, 11)), is(-1));
	}
}
