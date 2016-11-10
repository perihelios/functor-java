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

	public static boolean palindrome(BigInteger n) {
		return palindrome(n.toString().toCharArray());
	}

	public static boolean palindrome(long n) {
		return palindrome(Long.toString(n).toCharArray());
	}

	public static boolean palindrome(String str) {
		return palindrome(str.toCharArray());
	}

	public static boolean palindrome(char[] chars) {
		int length = chars.length;

		if (length < 2) {
			return true;
		}

		int remainder = length & 1;
		int right = length >>> 1;
		int left = right - 1;
		right += remainder;

		while (left >= 0) {
			if (chars[left] != chars[right]) {
				return false;
			}

			left--;
			right++;
		}

		return true;
	}
}
