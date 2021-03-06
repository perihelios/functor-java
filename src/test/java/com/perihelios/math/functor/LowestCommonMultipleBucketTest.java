package com.perihelios.math.functor;

import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class LowestCommonMultipleBucketTest {
	private LowestCommonMultipleBucket bucket = new LowestCommonMultipleBucket(new SimplePrimeEngine(new BinarySearchSquareRootEngine()));

	@Test
	public void add_minimum() {
		try {
			bucket.add(1L);
			fail("Expected exception " + IllegalStateException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Number added must be 2 or greater; got 1"));
		}
	}

	@Test
	public void primeFactors_requires_at_least_one_item() {
		try {
			bucket.primeFactors();
			fail("Expected exception " + IllegalStateException.class.getName());
		} catch (IllegalStateException expected) {
			assertThat(expected.getMessage(), is("At least one number must be added"));
		}
	}

	@Test
	public void primeFactorCounts_requires_at_least_one_item() {
		try {
			bucket.primeFactorCounts();
			fail("Expected exception " + IllegalStateException.class.getName());
		} catch (IllegalStateException expected) {
			assertThat(expected.getMessage(), is("At least one number must be added"));
		}
	}

	@Test
	public void lcm_requires_at_least_one_item() {
		try {
			bucket.lcm();
			fail("Expected exception " + IllegalStateException.class.getName());
		} catch (IllegalStateException expected) {
			assertThat(expected.getMessage(), is("At least one number must be added"));
		}
	}

	@Test
	public void primeFactorCounts_works() {
		SortedMap<Long, Long> expected = new TreeMap<>();

		bucket.add(2);
		expected.put(2L, 1L);
		assertThat(bucket.primeFactorCounts(), is(expected));

		bucket.add(3);
		expected.put(3L, 1L);
		assertThat(bucket.primeFactorCounts(), is(expected));

		bucket.add(4);
		expected.put(2L, 2L);
		assertThat(bucket.primeFactorCounts(), is(expected));

		bucket.add(5);
		expected.put(5L, 1L);
		assertThat(bucket.primeFactorCounts(), is(expected));

		bucket.add(6);
		assertThat(bucket.primeFactorCounts(), is(expected));

		bucket.add(7);
		expected.put(7L, 1L);
		assertThat(bucket.primeFactorCounts(), is(expected));

		bucket.add(8);
		expected.put(2L, 3L);
		assertThat(bucket.primeFactorCounts(), is(expected));

		bucket.add(9);
		expected.put(3L, 2L);
		assertThat(bucket.primeFactorCounts(), is(expected));

		bucket.add(7200);
		expected.put(2L, 5L);
		expected.put(5L, 2L);
		assertThat(bucket.primeFactorCounts(), is(expected));
	}

	@Test
	public void primeFactors_works() {
		bucket.add(2);
		bucket.add(3);
		bucket.add(4);
		bucket.add(5);
		bucket.add(6);
		bucket.add(7);
		bucket.add(8);
		bucket.add(9);

		assertThat(bucket.primeFactors(), is(new TreeSet<>(asList(2L, 3L, 5L, 7L))));
	}

	@Test
	public void lcm_works() {
		bucket.add(2);
		bucket.add(3);
		bucket.add(4);
		bucket.add(5);
		bucket.add(6);
		bucket.add(7);
		bucket.add(8);
		bucket.add(9);

		assertThat(bucket.lcm(), is(2520L));
	}
}
