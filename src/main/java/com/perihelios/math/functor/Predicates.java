package com.perihelios.math.functor;

import java.math.BigInteger;

public class Predicates {
	public static boolean even(BigInteger n) {
		return !n.testBit(0);
	}

	public static boolean even(long n) {
		return (n & 1L) == 0L;
	}

	public static boolean odd(BigInteger n) {
		return n.testBit(0);
	}

	public static boolean odd(long n) {
		return (n & 1L) == 1L;
	}
}
