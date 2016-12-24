package com.perihelios.math.functor;

import org.junit.Test;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.perihelios.math.functor.Memoization.memoize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MemoizationTest {
	@Test
	public void memoize_function_works() {
		Function<Integer, String> memoize = memoize(n -> String.valueOf(n * 2));
		assertThat(memoize.apply(1), is("2"));
		assertThat(memoize.apply(1), is("2"));

		@SuppressWarnings("unchecked")
		Map<Integer, String> cache = (Map<Integer, String>) TestUtil.getField(memoize, "cache");

		assertThat(cache.get(1), is("2"));

		cache.put(1, "3");

		assertThat(memoize.apply(1), is("3"));
	}

	@Test
	public void memoize_bifunction_works() {
		BiFunction<Integer, Integer, String> memoize = memoize((a, b) -> String.valueOf(a * b));
		assertThat(memoize.apply(1, 2), is("2"));
		assertThat(memoize.apply(2, 3), is("6"));

		@SuppressWarnings("unchecked")
		Map<Pair<Integer, Integer>, String> cache = (Map<Pair<Integer, Integer>, String>) TestUtil.getField(memoize, "cache");

		assertThat(cache.get(new Pair<>(1, 2)), is("2"));

		cache.put(new Pair<>(1, 2), "3");

		assertThat(memoize.apply(1, 2), is("3"));
	}
}
