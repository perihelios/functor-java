package com.perihelios.math.functor;

import org.junit.Test;

import java.math.BigInteger;

import static com.perihelios.math.functor.NumberUtil.bigInt;
import static com.perihelios.math.functor.NumberUtil.bigInts;
import static com.perihelios.math.functor.NumberUtil.gridFromString;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class NumberUtilTest {
	@Test
	public void bigInt_works() {
		assertThat(bigInt(2), is(BigInteger.valueOf(2L)));
	}

	@Test
	public void bigInts_works() {
		assertThat(bigInts().length, is(0));
		assertThat(bigInts(1), is(arrayContaining(BigInteger.valueOf(1L))));
		assertThat(bigInts(1, 2), is(arrayContaining(BigInteger.valueOf(1L), BigInteger.valueOf(2L))));
	}

	@Test
	public void gridFromString_works() {
		assertThat(gridFromString("").length, is(0));

		assertThat(gridFromString("1"), is(arrayContaining(new BigInteger[][]{
			bigInts(1),
		})));

		assertThat(gridFromString("1 2"), is(arrayContaining(new BigInteger[][]{
			bigInts(1, 2),
		})));

		assertThat(gridFromString("1\t2"), is(arrayContaining(new BigInteger[][]{
			bigInts(1, 2),
		})));

		assertThat(gridFromString("   1  \t  2 \t"), is(arrayContaining(new BigInteger[][]{
			bigInts(1, 2),
		})));

		assertThat(gridFromString("1 2\n3 4\r\n5 6"), is(arrayContaining(new BigInteger[][]{
			bigInts(1, 2),
			bigInts(3, 4),
			bigInts(5, 6),
		})));

		assertThat(gridFromString("1 2\n3 4\n"), is(arrayContaining(new BigInteger[][]{
			bigInts(1, 2),
			bigInts(3, 4),
		})));
	}

	@Test
	public void gridFromString_expects_rows_to_have_consistent_columns() {
		try {
			gridFromString("1\n\n2");
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Row 1 has 0 column(s); expected 1"));
		}

		try {
			gridFromString("1 2\n3");
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Row 1 has 1 column(s); expected 2"));
		}
	}

	@Test
	public void gridFromString_expects_decimal_integers() {
		try {
			gridFromString("a");
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Value at 0, 0 not a decimal integer: a"));
		}

		try {
			gridFromString("1 2\n3 4.1");
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Value at 1, 1 not a decimal integer: 4.1"));
		}
	}
}
