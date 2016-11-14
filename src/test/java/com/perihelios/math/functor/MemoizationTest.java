package com.perihelios.math.functor;

import org.junit.Test;

import java.util.Map;
import java.util.function.Function;

import static com.perihelios.math.functor.Memoization.memoize;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class MemoizationTest {
	@Test
	public void memoize_works() {
		Function<Integer, String> memoize = memoize(n -> String.valueOf(n * 2));
		assertThat(memoize.apply(1), is("2"));
		assertThat(memoize.apply(1), is("2"));

		@SuppressWarnings("unchecked")
		Map<Integer, String> cache = (Map<Integer, String>) TestUtil.getField(memoize, "cache");

		assertThat(cache.get(1), is("2"));

		cache.put(1, "3");

		assertThat(memoize.apply(1), is("3"));
	}
}
