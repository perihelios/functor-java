package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.SortedMap;

public interface PrimeEngine {
	SortedMap<BigInteger, Long> primeFactorsOf(BigInteger n);
}
