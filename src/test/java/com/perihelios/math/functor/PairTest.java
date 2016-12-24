package com.perihelios.math.functor;

import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PairTest {
	@SuppressWarnings({"ObjectEqualsNull", "EqualsBetweenInconvertibleTypes"})
	@Test
	public void equals_hashCode_implemented() {
		HashSet<Pair> pairs = new HashSet<>();
		pairs.add(new Pair<>(1, 1));

		assertThat(pairs.contains(new Pair<>(1, 1)), is(true));

		assertThat(new Pair<>(1, 2).equals(new Pair<>(1, 1)), is(false));
		assertThat(new Pair<>(1, 1).equals(new Pair<>(1, 2)), is(false));
		assertThat(new Pair<>(1, 2).equals(null), is(false));
		assertThat(new Pair<>(1, 2).equals("blah"), is(false));
	}
}
