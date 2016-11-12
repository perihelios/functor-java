package com.perihelios.math.functor;

import org.junit.Test;

import java.util.stream.Collectors;

import static com.perihelios.math.functor.NumberUtil.bigInt;
import static com.perihelios.math.functor.NumberUtil.bigInts;
import static com.perihelios.math.functor.Sequences.rangeProducts;
import static com.perihelios.math.functor.Sequences.substrings;
import static com.perihelios.math.functor.Sequences.triangleNumbers;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class SequencesTest {
	@Test
	public void rangeProducts_BigInteger() {
		assertThat(rangeProducts(bigInt(1), bigInt(1)).collect(toList()), is(emptyList()));
		assertThat(rangeProducts(bigInt(1), bigInt(2)).collect(toList()), is(asList(bigInts(1))));
		assertThat(rangeProducts(bigInt(1), bigInt(3)).collect(toList()), is(asList(bigInts(1, 2, 4))));
		assertThat(rangeProducts(bigInt(1), bigInt(4)).collect(toList()), is(asList(bigInts(1, 2, 4, 3, 6, 9))));
	}

	@Test
	public void rangeProducts_BigInteger_order() {
		try {
			rangeProducts(bigInt(2), bigInt(1));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Range start (inclusive) must be less than or equal to range end (exclusive); got start=2, end=1"));
		}
	}

	@Test
	public void substrings_works() {
		assertThat(substrings("a", 1).collect(Collectors.toList()), is(asList("a")));
		assertThat(substrings("ab", 1).collect(Collectors.toList()), is(asList("a", "b")));
		assertThat(substrings("abc", 2).collect(Collectors.toList()), is(asList("ab", "bc")));
		assertThat(substrings("abcdefghi", 4).collect(Collectors.toList()), is(asList("abcd", "bcde", "cdef", "defg", "efgh", "fghi")));
	}

	@Test
	public void substrings_minimum_length() {
		try {
			substrings("a", 0);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Length argument must be greater than 0; got 0"));
		}

	}

	@Test
	public void substrings_length_not_greater_than_String_length() {
		try {
			substrings("a", 2);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Length argument must be no greater than string's length (1); got 2"));
		}
	}

	@Test
	public void triangleNumbers_works() {
		assertThat(
			triangleNumbers().limit(20).collect(Collectors.toList()),
			is(asList(bigInts(1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78, 91, 105, 120, 136, 153, 171, 190, 210)))
		);
	}
}
