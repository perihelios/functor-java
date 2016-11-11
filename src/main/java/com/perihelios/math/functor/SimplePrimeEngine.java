package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.Arrays.asList;

public class SimplePrimeEngine implements PrimeEngine {
	private static final BigInteger FOUR = BigInteger.valueOf(4L);
	private static final BigInteger MAX_PRIME = BigInteger.valueOf(Long.MAX_VALUE - 1L);

	private final SquareRootEngine squareRootEngine;

	private final SortedSet<Long> knownPrimes = new TreeSet<>(asList(2L, 3L, 5L, 7L));
	private long largestCheckedForPrimeness = 7L;

	public SimplePrimeEngine(SquareRootEngine squareRootEngine) {
		this.squareRootEngine = squareRootEngine;
	}

	@Override
	public SortedMap<BigInteger, Long> primeFactorsOf(BigInteger n) {
		if (n.signum() < 0) {
			throw new IllegalArgumentException("Minimum allowed argument is 0; got " + n);
		}

		SortedMap<BigInteger, Long> factors = new TreeMap<>();

		if (n.compareTo(FOUR) < 0) {
			factors.put(n, 1L);
			return factors;
		}

		BigInteger max = squareRootEngine.sqrtFloor(n);
		buildPrimesTo(max);
		long maxAsLong = max.longValue();

		outer:
		while (!n.equals(ONE)) {
			for (long knownPrime : knownPrimes) {
				if (knownPrime > maxAsLong) {
					break outer;
				}

				BigInteger bigKnownPrime = BigInteger.valueOf(knownPrime);
				BigInteger[] quotientAndRemainder = n.divideAndRemainder(bigKnownPrime);

				if (quotientAndRemainder[1].equals(ZERO)) {
					n = quotientAndRemainder[0];
					factors.merge(bigKnownPrime, 1L, (a, b) -> a + b);
					break;
				}
			}

			max = squareRootEngine.sqrtFloor(n);
			maxAsLong = max.longValue();
		}

		if (!n.equals(ONE)) {
			factors.merge(n, 1L, (a, b) -> a + b);
		}

		return factors;
	}

	private void buildPrimesTo(BigInteger limit) {
		if (limit.compareTo(MAX_PRIME) > 0) {
			throw new IllegalStateException("Cannot compute primes past " + MAX_PRIME);
		}

		long lim = limit.longValue();

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
			for (long knownPrime : knownPrimes) {
				if (checked % knownPrime == 0) {
					continue outer;
				}
			}

			knownPrimes.add(checked);
		}

		largestCheckedForPrimeness = lim;
	}
}
