package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;

public class PolygonalSequences {
	public static Stream<BigInteger> polygonalSequence(BigInteger sides) {
		return StreamSupport.stream(new PolygonalSpliterator(sides), false);
	}

	public static Stream<BigInteger> polygonalSequenceFromIndex(BigInteger sides, BigInteger index) {
		return StreamSupport.stream(new PolygonalSpliterator(sides, index), false);
	}

	public static LongStream polygonalSequence(long sides) {
		return StreamSupport.longStream(new PolygonalSpliteratorOfLong(sides), false);
	}

	public static LongStream polygonalSequenceFromIndex(long sides, long index) {
		return StreamSupport.longStream(new PolygonalSpliteratorOfLong(sides, index), false);
	}

	private static class PolygonalSpliterator extends SplitlessSpliterator<BigInteger> {
		private static final BigInteger TWO = valueOf(2L);
		private static final BigInteger FOUR = valueOf(4L);

		private final BigInteger incrementIncrement;

		private BigInteger next;
		private BigInteger increment;

		private PolygonalSpliterator(BigInteger sides) {
			incrementIncrement = sides.subtract(TWO);
			next = ONE;
			increment = ONE;
		}

		private PolygonalSpliterator(BigInteger sides, BigInteger startingIndex) {
			BigInteger incrementIncrement = sides.subtract(TWO);

			this.incrementIncrement = incrementIncrement;
			this.next = (startingIndex.multiply(incrementIncrement.multiply(startingIndex).subtract(sides).add(FOUR))).shiftRight(1);
			this.increment = ONE.add(incrementIncrement.multiply(startingIndex.subtract(ONE)));
		}

		@Override
		public boolean tryAdvance(Consumer<? super BigInteger> action) {
			action.accept(next);

			increment = increment.add(incrementIncrement);
			next = next.add(increment);

			return true;
		}
	}

	private static class PolygonalSpliteratorOfLong extends SplitlessSpliteratorOfLong {
		private final long incrementIncrement;

		private long next;
		private long increment;

		private PolygonalSpliteratorOfLong(long sides) {
			incrementIncrement = sides - 2L;
			next = 1L;
			increment = 1L;
		}

		private PolygonalSpliteratorOfLong(long sides, long startingIndex) {
			long incrementIncrement = sides - 2L;

			this.incrementIncrement = incrementIncrement;
			this.next = (startingIndex * (incrementIncrement * startingIndex - (sides - 4L))) >>> 1L;
			this.increment = 1L + incrementIncrement * (startingIndex - 1L);
		}

		@Override
		public boolean tryAdvance(LongConsumer action) {
			action.accept(next);

			increment += incrementIncrement;
			next += increment;

			return true;
		}
	}

	// Prevent construction of this class
	private PolygonalSequences() {}
}
