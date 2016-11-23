package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.LongConsumer;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

import static java.math.BigInteger.valueOf;
import static java.util.Arrays.asList;

public class SimplePrimeEngine implements PrimeEngine {
	private static final long MAX_PRIME = Long.MAX_VALUE - 1L;

	private final SquareRootEngine squareRootEngine;

	private final SortedSet<Long> knownPrimes = new TreeSet<>(asList(2L, 3L, 5L, 7L));
	private long largestCheckedForPrimeness = 7L;

	public SimplePrimeEngine(SquareRootEngine squareRootEngine) {
		this.squareRootEngine = squareRootEngine;
	}

	@Override
	public SortedMap<Long, Long> primeFactorsOf(long n) {
		if (n < 0L) {
			throw new IllegalArgumentException("Minimum allowed argument is 0; got " + n);
		}

		SortedMap<Long, Long> factors = new TreeMap<>();

		if (n < 4L) {
			factors.put(n, 1L);
			return factors;
		}

		long max = squareRootEngine.sqrtFloor(BigInteger.valueOf(n)).longValue();
		buildPrimesTo(max, Long.MAX_VALUE);

		outer:
		while (n != 1L) {
			for (long knownPrime : knownPrimes) {
				if (knownPrime > max) {
					break outer;
				}

				if (n % knownPrime == 0L) {
					n = n / knownPrime;
					factors.merge(knownPrime, 1L, (a, b) -> a + b);
					break;
				}
			}

			max = squareRootEngine.sqrtFloor(BigInteger.valueOf(n)).longValue();
		}

		if (n != 1L) {
			factors.merge(n, 1L, (a, b) -> a + b);
		}

		return factors;
	}

	@Override
	public LongStream primes() {
		return StreamSupport.longStream(new SplitlessSpliteratorOfLong() {
			Iterator<Long> iterator = knownPrimes.iterator();

			@Override
			public boolean tryAdvance(LongConsumer action) {
				if (iterator != null) {
					if (iterator.hasNext()) {
						action.accept(iterator.next());
						return true;
					} else {
						iterator = null;
					}
				}

				buildPrimesTo(MAX_PRIME, 1L);

				action.accept(knownPrimes.last());
				return true;
			}

			@Override
			public int characteristics() {
				return Spliterator.NONNULL | Spliterator.ORDERED;
			}
		}, false);
	}

	@Override
	public boolean isPrime(long n) {
		buildPrimesTo(n, Long.MAX_VALUE);

		return knownPrimes.contains(n);
	}

	private void buildPrimesTo(long ceiling, long maxCountToFind) {
		if (ceiling > MAX_PRIME) {
			throw new IllegalStateException("Cannot compute primes past " + MAX_PRIME);
		}

		long lim = ceiling;

		if ((lim & 1L) == 0L) {
			lim++;
		}

		long checked = largestCheckedForPrimeness;
		checked++;

		if ((checked & 1L) == 0L) {
			checked++;
		}

		outer:
		for ( ; checked <= lim; checked += 2) {
			long upperBound = squareRootEngine.sqrtFloor(valueOf(checked)).longValue();

			for (long knownPrime : knownPrimes) {
				if (knownPrime > upperBound) break;

				if (checked % knownPrime == 0) {
					continue outer;
				}
			}

			knownPrimes.add(checked);
			if (--maxCountToFind == 0) {
				break;
			}
		}

		largestCheckedForPrimeness = checked > lim ? lim : checked;
	}
}
