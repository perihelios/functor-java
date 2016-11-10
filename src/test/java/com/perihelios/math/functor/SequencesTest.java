package com.perihelios.math.functor;

import org.junit.Test;

import static com.perihelios.math.functor.NumberUtil.bigInt;
import static com.perihelios.math.functor.NumberUtil.bigInts;
import static com.perihelios.math.functor.Sequences.rangeProducts;
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
}
