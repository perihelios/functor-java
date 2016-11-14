package com.perihelios.math.functor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Memoization {
	public static <T, R> Function<T, R> memoize(Function<T, R> function) {
		return new Function<T, R>() {
			private Map<T, R> cache = new HashMap<>(1_048_576);

			@Override
			public R apply(T t) {
				return cache.compute(t, (key, oldValue) -> {
					if (oldValue != null) return oldValue;

					return function.apply(t);
				});
			}
		};
	}
}
