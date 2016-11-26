package com.perihelios.math.functor;

import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.stream.LongStream;

public interface PrimeEngine {
	SortedMap<Long, Long> primeFactorsCountsOf(long n);
	List<Long> primeFactorsOf(long n);
	SortedSet<Long> distinctPrimeFactorsOf(long n);
	LongStream primes();
	boolean isPrime(long n);
}
