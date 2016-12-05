package com.perihelios.math.functor;

import org.junit.Test;

import java.math.BigInteger;

import static com.perihelios.math.functor.NumberUtil.bigInt;
import static com.perihelios.math.functor.NumberUtil.bigInts;
import static com.perihelios.math.functor.NumberUtil.digitSum;
import static com.perihelios.math.functor.NumberUtil.distinctFactorCount;
import static com.perihelios.math.functor.NumberUtil.factorial;
import static com.perihelios.math.functor.NumberUtil.gridFromString;
import static com.perihelios.math.functor.NumberUtil.properDivisors;
import static com.perihelios.math.functor.NumberUtil.triangleFromString;
import static com.perihelios.math.functor.TestUtil.treeSet;
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
	public void bigInts_long_works() {
		assertThat(bigInts(new long[0]).length, is(0));
		assertThat(bigInts(1), is(arrayContaining(BigInteger.valueOf(1L))));
		assertThat(bigInts(1, 2), is(arrayContaining(BigInteger.valueOf(1L), BigInteger.valueOf(2L))));
	}

	@Test
	public void bigInts_String_works() {
		assertThat(bigInts(new String[0]).length, is(0));
		assertThat(bigInts("1"), is(arrayContaining(BigInteger.valueOf(1L))));
		assertThat(bigInts("1", "2"), is(arrayContaining(BigInteger.valueOf(1L), BigInteger.valueOf(2L))));
	}

	@Test
	public void gridFromString_works() {
		assertThat(gridFromString("").length, is(0));

		assertThat(gridFromString(" ").length, is(0));

		assertThat(gridFromString("1"), is(arrayContaining(new BigInteger[][] {
			bigInts(1),
		})));

		assertThat(gridFromString("1 2"), is(arrayContaining(new BigInteger[][] {
			bigInts(1, 2),
		})));

		assertThat(gridFromString("1\t2"), is(arrayContaining(new BigInteger[][] {
			bigInts(1, 2),
		})));

		assertThat(gridFromString("   1  \t  2 \t"), is(arrayContaining(new BigInteger[][] {
			bigInts(1, 2),
		})));

		assertThat(gridFromString("1 2\n3 4\r\n5 6"), is(arrayContaining(new BigInteger[][] {
			bigInts(1, 2),
			bigInts(3, 4),
			bigInts(5, 6),
		})));

		assertThat(gridFromString("\n1 2\n3 4\n"), is(arrayContaining(new BigInteger[][] {
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

	@Test
	public void triangleFromString_works() {
		assertThat(triangleFromString("").length, is(0));

		assertThat(triangleFromString(" ").length, is(0));

		assertThat(triangleFromString("1"), is(arrayContaining(new BigInteger[][] {
			bigInts(1),
		})));

		assertThat(triangleFromString("1\n2 3"), is(arrayContaining(new BigInteger[][] {
			bigInts(1),
			bigInts(2, 3),
		})));

		assertThat(triangleFromString("  \t 1  \r\n  \t2  \t  3  "), is(arrayContaining(new BigInteger[][] {
			bigInts(1),
			bigInts(2, 3),
		})));

		assertThat(triangleFromString("\n1\n2 3\n"), is(arrayContaining(new BigInteger[][] {
			bigInts(1),
			bigInts(2, 3),
		})));

		assertThat(triangleFromString("1\n2 3\n4 5 6"), is(arrayContaining(new BigInteger[][] {
			bigInts(1),
			bigInts(2, 3),
			bigInts(4, 5, 6),
		})));
	}

	@Test
	public void triangleFromString_expects_rows_to_have_consistently_increasing_columns() {
		try {
			triangleFromString("1\n2");
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Row 1 has 1 column(s); expected 2"));
		}

		try {
			triangleFromString("1\n2 3 4");
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Row 1 has 3 column(s); expected 2"));
		}

		try {
			triangleFromString("1\n2 3\n 4 5");
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Row 2 has 2 column(s); expected 3"));
		}
	}

	@Test
	public void triangleFromString_expects_decimal_integers() {
		try {
			triangleFromString("a");
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Value at 0, 0 not a decimal integer: a"));
		}

		try {
			triangleFromString("1\n2 3.1");
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Value at 1, 1 not a decimal integer: 3.1"));
		}
	}

	@Test
	public void factorial_works() {
		assertThat(factorial(bigInt(0)), is(bigInt(1)));
		assertThat(factorial(bigInt(1)), is(bigInt(1)));
		assertThat(factorial(bigInt(2)), is(bigInt(2)));
		assertThat(factorial(bigInt(3)), is(bigInt(6)));
		assertThat(factorial(bigInt(4)), is(bigInt(24)));
		assertThat(factorial(bigInt(5)), is(bigInt(120)));
	}

	@Test
	public void factorial_minimum() {
		try {
			factorial(BigInteger.valueOf(-1L));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Factorial not possible for negative number; got -1"));
		}
	}

	@Test
	public void distinctFactorCount_works() {
		assertThat(distinctFactorCount(0), is(1L));
		assertThat(distinctFactorCount(1), is(1L));
		assertThat(distinctFactorCount(2), is(2L));
		assertThat(distinctFactorCount(3), is(2L));
		assertThat(distinctFactorCount(4), is(3L));
		assertThat(distinctFactorCount(5), is(2L));
		assertThat(distinctFactorCount(6), is(4L));
		assertThat(distinctFactorCount(76476100), is(36L));
	}

	@Test
	public void properDivisors_works() {
		assertThat(properDivisors(1L), is(treeSet()));
		assertThat(properDivisors(2L), is(treeSet(1L)));
		assertThat(properDivisors(3L), is(treeSet(1L)));
		assertThat(properDivisors(4L), is(treeSet(1L, 2L)));
		assertThat(properDivisors(28L), is(treeSet(1L, 2L, 4L, 7L, 14L)));
	}

	@Test
	public void properDivisors_minimum() {
		try {
			properDivisors(0L);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Argument must be greater than 0; got 0"));
		}
	}

	@Test
	public void digitSum_long_works() {
		assertThat(digitSum(0L), is(0L));
		assertThat(digitSum(1L), is(1L));
		assertThat(digitSum(9L), is(9L));
		assertThat(digitSum(10L), is(1L));
		assertThat(digitSum(19L), is(10L));
		assertThat(digitSum(135L), is(9L));
		assertThat(digitSum(513L), is(9L));
	}

	@Test
	public void digitSum_BigInteger_works() {
		assertThat(digitSum(bigInt(0L)), is(0L));
		assertThat(digitSum(bigInt(1L)), is(1L));
		assertThat(digitSum(bigInt(9L)), is(9L));
		assertThat(digitSum(bigInt(10L)), is(1L));
		assertThat(digitSum(bigInt(19L)), is(10L));
		assertThat(digitSum(bigInt(135L)), is(9L));
		assertThat(digitSum(bigInt(513L)), is(9L));
	}

	@Test
	public void digitSum_long_minimum() {
		try {
			digitSum(-1L);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Only non-negative numbers supported; got -1"));
		}
	}

	@Test
	public void digitSum_BigInteger_minimum() {
		try {
			digitSum(bigInt(-1L));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Only non-negative numbers supported; got -1"));
		}
	}
}
