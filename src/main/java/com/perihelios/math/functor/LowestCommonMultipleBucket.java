package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.math.BigInteger.ONE;

public class LowestCommonMultipleBucket {
	private final PrimeEngine primeEngine;
	private final SortedMap<BigInteger, Long> factors = new TreeMap<>();

	public LowestCommonMultipleBucket(PrimeEngine primeEngine) {
		this.primeEngine = primeEngine;
	}

	public void add(BigInteger n) {
		if (n.compareTo(ONE) <= 0) {
			throw new IllegalArgumentException("Number added must be 2 or greater; got " + n);
		}

		SortedMap<BigInteger, Long> newFactors = primeEngine.primeFactorsOf(n);

		for (Map.Entry<BigInteger, Long> entry: newFactors.entrySet()) {
			BigInteger factor = entry.getKey();
			Long count = entry.getValue();

			factors.merge(factor, count, Long::max);
		}
	}

	public void add(long n) {
		add(BigInteger.valueOf(n));
	}

	public Set<BigInteger> primeFactors() {
		if (factors.isEmpty()) {
			throw new IllegalStateException("At least one number must be added");
		}

		return factors.keySet();
	}

	public Map<BigInteger, Long> primeFactorCounts() {
		if (factors.isEmpty()) {
			throw new IllegalStateException("At least one number must be added");
		}

		return factors;
	}

	public BigInteger lcm() {
		if (factors.isEmpty()) {
			throw new IllegalStateException("At least one number must be added");
		}

		//noinspection OptionalGetWithoutIsPresent
		return factors.entrySet().stream()
			.map(e -> e.getKey().pow(e.getValue().intValue()))
			.reduce(BigInteger::multiply)
			.get();
	}
}
