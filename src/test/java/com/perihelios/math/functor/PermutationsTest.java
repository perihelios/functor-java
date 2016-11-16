package com.perihelios.math.functor;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.perihelios.math.functor.NumberUtil.bigInt;
import static com.perihelios.math.functor.NumberUtil.factorial;
import static com.perihelios.math.functor.Permutations.permutations;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class PermutationsTest {
	@Test
	public void permutations_minimum_choose() {
		try {
			permutations(0, asList(1));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Items to choose must be greater than 0; got 0"));
		}
	}

	@Test
	public void permutations_choose_within_size() {
		try {
			permutations(4, asList(1, 2, 3));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Items to choose must be no greater than total items (3); got 4"));
		}
	}

	@Test
	public void permutations_at_least_one_item() {
		try {
			permutations(1, asList());
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("At least one item must be available"));
		}
	}

	@Test
	public void permutations_works() {
		assertThat(P(1, 1), is(set(
			asList(1)
		)));

		assertThat(P(1, 1, 2), is(set(
			asList(1),
			asList(2)
		)));

		assertThat(P(1, 1, 2, 3), is(set(
			asList(1),
			asList(2),
			asList(3)
		)));

		assertThat(P(2, 1, 2), is(set(
			asList(1, 2),
			asList(2, 1)
		)));

		assertThat(P(2, 1, 2, 3), is(set(
			asList(1, 2),
			asList(1, 3),
			asList(2, 1),
			asList(2, 3),
			asList(3, 1),
			asList(3, 2)
		)));

		assertThat(P(3, 1, 2, 3), is(set(
			asList(1, 2, 3),
			asList(1, 3, 2),
			asList(2, 1, 3),
			asList(2, 3, 1),
			asList(3, 1, 2),
			asList(3, 2, 1)
		)));

		Integer[] values = { 1, 2, 3, 4, 5, 6, 7 };
		for (int choose = 1; choose <= 7; choose++) {
			Set<List<Integer>> permutations = P(choose, values);

			int expectedSize = factorial(bigInt(values.length)).divide(factorial(bigInt(values.length - choose))).intValue();

			assertThat(permutations.size(), is(expectedSize));

			for (List<Integer> permutation : permutations) {
				assertThat(new HashSet<>(permutation).size(), is(permutation.size()));
			}
		}
	}

	@SafeVarargs
	private static <T> Set<List<T>> P(int choose, T... items) {
		return permutations(choose, asList(items)).collect(toSet());
	}

	@SafeVarargs
	private static <T> Set<T> set(T... items) {
		return new HashSet<>(asList(items));
	}
}
