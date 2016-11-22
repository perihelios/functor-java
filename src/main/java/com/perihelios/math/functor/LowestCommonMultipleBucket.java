package com.perihelios.math.functor;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class LowestCommonMultipleBucket {
	private final PrimeEngine primeEngine;
	private final SortedMap<Long, Long> factors = new TreeMap<>();

	public LowestCommonMultipleBucket(PrimeEngine primeEngine) {
		this.primeEngine = primeEngine;
	}

	public void add(long n) {
		if (n <= 1) {
			throw new IllegalArgumentException("Number added must be 2 or greater; got " + n);
		}

		SortedMap<Long, Long> newFactors = primeEngine.primeFactorsOf(n);

		for (Map.Entry<Long, Long> entry: newFactors.entrySet()) {
			Long factor = entry.getKey();
			Long count = entry.getValue();

			factors.merge(factor, count, Long::max);
		}
	}

	public Set<Long> primeFactors() {
		if (factors.isEmpty()) {
			throw new IllegalStateException("At least one number must be added");
		}

		return factors.keySet();
	}

	public Map<Long, Long> primeFactorCounts() {
		if (factors.isEmpty()) {
			throw new IllegalStateException("At least one number must be added");
		}

		return factors;
	}

	public long lcm() {
		if (factors.isEmpty()) {
			throw new IllegalStateException("At least one number must be added");
		}

		return factors.entrySet().stream()
			.map(e -> pow(e.getKey(), e.getValue()))
			.reduce(1L, (a, b) -> a * b)
		;
	}

	private static long pow(long base, long exponent) {
		long value = base;

		while (exponent > 1L) {
			value *= base;
			exponent--;
		}

		return value;
	}
}
