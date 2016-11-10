package com.perihelios.math.functor;

import java.math.BigInteger;

public class NumberUtil {
	public static BigInteger bigInt(long num) {
		return BigInteger.valueOf(num);
	}

	public static BigInteger[] bigInts(long... nums) {
		BigInteger[] bigIntegers = new BigInteger[nums.length];

		for (int i = 0; i < nums.length; i++) {
			bigIntegers[i] = BigInteger.valueOf(nums[i]);
		}

		return bigIntegers;
	}

	// Prevent instantiation of this class
	private NumberUtil() {}
}
