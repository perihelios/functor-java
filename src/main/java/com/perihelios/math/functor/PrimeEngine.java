package com.perihelios.math.functor;

import java.util.SortedMap;
import java.util.stream.LongStream;

public interface PrimeEngine {
	SortedMap<Long, Long> primeFactorsOf(long n);
	LongStream primes();
	boolean isPrime(long n);
}
