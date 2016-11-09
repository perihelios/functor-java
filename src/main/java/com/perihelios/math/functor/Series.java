package com.perihelios.math.functor;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;

@SuppressWarnings("WeakerAccess")
public class Series {
	private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);

	public static BigInteger arithmeticSeries1toN(BigInteger n) {
		return arithmeticSeriesNtoM(ONE, n, ONE);
	}

	public static BigInteger arithmeticSeries1toN(BigInteger n, BigInteger step) {
		return arithmeticSeriesNtoM(ONE, n, step);
	}

	public static BigInteger arithmeticSeriesNtoM(BigInteger n, BigInteger m) {
		return arithmeticSeriesNtoM(n, m, ONE);
	}

	public static BigInteger arithmeticSeriesNtoM(BigInteger n, BigInteger m, BigInteger step) {
		if (n.compareTo(ONE) < 0) {
			throw new IllegalArgumentException("Starting value, n, must be 1 or greater; got " + n);
		}

		if (n.compareTo(m) > 0) {
			throw new IllegalArgumentException("Starting value, n, must be less than or equal to ending value, m; got n=" + n + ", m=" + m);
		}

		if (step.compareTo(ONE) < 0) {
			throw new IllegalArgumentException("Step must be 1 or greater; got " + step);
		}

		BigInteger k = m.subtract(n).divide(step).add(ONE);
		m = n.add(k.subtract(ONE).multiply(step));

		return n.add(m).multiply(k).shiftRight(1);
	}

	public static long arithmeticSeries1toN(long n) {
		return arithmeticSeriesNtoM(1L, n, 1L);
	}

	public static long arithmeticSeries1toN(long n, long step) {
		return arithmeticSeriesNtoM(1L, n, step);
	}

	public static long arithmeticSeriesNtoM(long n, long m) {
		return arithmeticSeriesNtoM(n, m, 1L);
	}

	public static long arithmeticSeriesNtoM(long n, long m, long step) {
		BigInteger result = arithmeticSeriesNtoM(valueOf(n), valueOf(m), valueOf(step));

		if (result.compareTo(MAX_LONG) > 0) {
			throw new ArithmeticException("Result of arithmetic series from " + n + " to " + m + " with step of " + step +
				" would overflow long type");
		}

		return result.longValue();
	}
}
