package com.perihelios.math.functor;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.perihelios.math.functor.Combinations.combinations;
import static com.perihelios.math.functor.NumberUtil.bigInt;
import static com.perihelios.math.functor.NumberUtil.factorial;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CombinationsTest {
	@Test
	public void combinations_minimum_choose() {
		try {
			combinations(0, asList(1));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Items to choose must be greater than 0; got 0"));
		}
	}

	@Test
	public void combinations_choose_within_size() {
		try {
			combinations(4, asList(1, 2, 3));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Items to choose must be no greater than total items (3); got 4"));
		}
	}

	@Test
	public void combinations_at_least_one_item() {
		try {
			combinations(1, asList());
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("At least one item must be available"));
		}
	}

	@Test
	public void combinations_non_Collection() {
		assertThat(combinations(2, () -> new Iterator<Integer>() {
			private int[] values = { 1, 2, 3 };
			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < values.length;
			}

			@Override
			public Integer next() {
				return values[index++];
			}
		}).collect(toSet()), is(set(
			asList(1, 2),
			asList(1, 3),
			asList(2, 3)
		)));
	}

	@Test
	public void combinations_works() {
		assertThat(C(1, 1), is(set(
			asList(1)
		)));

		assertThat(C(1, 1, 2), is(set(
			asList(1),
			asList(2)
		)));

		assertThat(C(1, 1, 2, 3), is(set(
			asList(1),
			asList(2),
			asList(3)
		)));

		assertThat(C(2, 1, 2, 3), is(set(
			asList(1, 2),
			asList(1, 3),
			asList(2, 3)
		)));

		assertThat(C(2, 1, 2, 3, 4), is(set(
			asList(1, 2),
			asList(1, 3),
			asList(1, 4),
			asList(2, 3),
			asList(2, 4),
			asList(3, 4)
		)));

		assertThat(C(3, 1, 2, 3, 4), is(set(
			asList(1, 2, 3),
			asList(1, 2, 4),
			asList(1, 3, 4),
			asList(2, 3, 4)
		)));

		assertThat(C(4, 1, 2, 3, 4), is(set(
			asList(1, 2, 3, 4)
		)));

		Integer[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int choose = 1; choose <= 10; choose++) {
			Set<List<Integer>> combinations = C(choose, values);

			int expectedSize = factorial(bigInt(values.length))
				.divide(factorial(bigInt(values.length - choose)))
				.divide(factorial(bigInt(choose)))
				.intValue();

			assertThat(combinations.size(), is(expectedSize));

			for (List<Integer> combination : combinations) {
				assertThat(new HashSet<>(combination).size(), is(combination.size()));
			}
		}
	}

	@SafeVarargs
	private static <T> Set<List<T>> C(int choose, T... items) {
		return combinations(choose, asList(items)).collect(toSet());
	}

	@SafeVarargs
	private static <T> Set<T> set(T... items) {
		return new HashSet<>(asList(items));
	}
}
