package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;

public class Sequences {
	private static final BigInteger THREE = valueOf(3L);

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

	public static Stream<String> substrings(String str, int length) {
		if (length < 1) {
			throw new IllegalArgumentException("Length argument must be greater than 0; got " + length);
		}

		int strLength = str.length();

		if (length > strLength) {
			throw new IllegalArgumentException("Length argument must be no greater than string's length (" + strLength + "); got " + length);
		}

		return StreamSupport.stream(new SplitlessSpliterator<String>() {
			private int start = 0;
			private int end = length;

			@Override
			public boolean tryAdvance(Consumer<? super String> action) {
				if (end > strLength) {
					return false;
				}

				action.accept(str.substring(start, end));
				start++;
				end++;

				return true;
			}
		}, false);
	}

	public static Stream<BigInteger> triangularNumbers() {
		return StreamSupport.stream(new PolygonalSpliterator(3L), false);
	}

	public static Stream<BigInteger> pentagonalNumbers() {
		return StreamSupport.stream(new PolygonalSpliterator(5L), false);
	}

	public static Stream<BigInteger> hexagonalNumbers() {
		return StreamSupport.stream(new PolygonalSpliterator(6L), false);
	}

	public static Stream<BigInteger> collatz(BigInteger n) {
		return StreamSupport.stream(new SplitlessSpliterator<BigInteger>() {
			BigInteger next = n;

			@Override
			public boolean tryAdvance(Consumer<? super BigInteger> action) {
				if (next.equals(ZERO)) {
					return false;
				}

				action.accept(next);

				if (next.equals(ONE)) {
					next = ZERO;
				} else if (next.testBit(0)) {
					next = next.multiply(THREE).add(ONE);
				} else {
					next = next.shiftRight(1);
				}

				return true;
			}
		}, false);
	}

	private static class PolygonalSpliterator extends SplitlessSpliterator<BigInteger> {
		private final BigInteger incrementIncrement;
		private BigInteger next = ONE;
		private BigInteger increment = ONE;

		private PolygonalSpliterator(long sides) {
			incrementIncrement = valueOf(sides - 2L);
		}

		@Override
		public boolean tryAdvance(Consumer<? super BigInteger> action) {
			action.accept(next);

			increment = increment.add(incrementIncrement);
			next = next.add(increment);

			return true;
		}
	}
}
