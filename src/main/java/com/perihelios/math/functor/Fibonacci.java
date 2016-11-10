package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.math.BigInteger.valueOf;

public class Fibonacci {
	public static Stream<BigInteger> fibonacci(final BigInteger firstTerm, final BigInteger secondTerm) {
		return StreamSupport.stream(new BaseSpliterator<BigInteger>() {
			private BigInteger next = firstTerm;
			private BigInteger future = secondTerm;

			@Override
			public boolean tryAdvance(Consumer<? super BigInteger> action) {
				BigInteger current = next;
				next = future;
				future = next.add(current);

				action.accept(current);
				return true;
			}
		}, false);
	}

	public static Stream<BigInteger> fibonacci(long firstTerm, long secondTerm) {
		return fibonacci(valueOf(firstTerm), valueOf(secondTerm));
	}

	public static Stream<BigInteger> fibonacci() {
		return fibonacci(BigInteger.ONE, BigInteger.ONE);
	}

	// Prevent instantiation of this class
	private Fibonacci() {
	}
}
