package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.math.BigInteger.ONE;

public class Sequences {
	public static Stream<BigInteger> rangeProducts(BigInteger startInclusive, BigInteger endExclusive) {
		int comp = startInclusive.compareTo(endExclusive);

		if (comp > 0) {
			throw new IllegalArgumentException("Range start (inclusive) must be less than or equal to range end (exclusive); got start=" +
				startInclusive + ", end=" + endExclusive);
		}

		if (comp == 0) {
			return Stream.empty();
		}

		return StreamSupport.stream(new SplitlessSpliterator<BigInteger>() {
			BigInteger n = startInclusive;
			BigInteger m = startInclusive;

			@Override
			public boolean tryAdvance(Consumer<? super BigInteger> action) {
				if (m.compareTo(n) > 0) {
					m = startInclusive;
					n = n.add(ONE);
				}

				if (n.equals(endExclusive)) {
					return false;
				}

				action.accept(n.multiply(m));
				m = m.add(ONE);

				return true;
			}
		}, false);
	}
}
