package com.perihelios.math.functor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class Consecutives {
	public static <T> Stream<T> consecutive(Stream<T> stream, int n, BiPredicate<T, T> predicate) {
		if (n < 2) throw new IllegalArgumentException("Number of consecutive items must be 2 or greater; got " + n);

		Iterator<T> iterator = stream.iterator();

		if (!iterator.hasNext()) {
			stream.close();

			return Stream.empty();
		}

		Deque<T> deque = new ArrayDeque<>(n);
		deque.addLast(iterator.next());

		if (!iterator.hasNext()) {
			stream.close();

			return Stream.empty();
		}

		while (iterator.hasNext()) {
			T element = iterator.next();

			if (predicate.test(deque.getLast(), element)) {
				deque.addLast(element);

				if (deque.size() == n) {
					stream.close();

					return deque.stream();
				}
			} else {
				deque.clear();
				deque.add(element);
			}
		}

		stream.close();

		return Stream.empty();
	}

	// Prevent instantiation of this class
	private Consecutives() {}
}
