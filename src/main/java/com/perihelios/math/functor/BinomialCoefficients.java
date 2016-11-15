package com.perihelios.math.functor;

import java.math.BigInteger;

import static com.perihelios.math.functor.NumberUtil.factorial;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;

public class BinomialCoefficients {
	public static BigInteger centralBinomialCoefficient(long n) {
		if (n == 0L) {
			return ONE;
		}

		BigInteger bigN = valueOf(n);
		BigInteger big2N = valueOf(n << 1);
		BigInteger factorialN = factorial(bigN);

		return factorial(big2N).divide(factorialN).divide(factorialN);
	}

	// Prevent instantiation of this class
	private BinomialCoefficients() {}
}
