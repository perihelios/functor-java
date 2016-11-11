package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.SortedMap;
import java.util.stream.Stream;

public interface PrimeEngine {
	SortedMap<BigInteger, Long> primeFactorsOf(BigInteger n);
	Stream<BigInteger> primes();
}
