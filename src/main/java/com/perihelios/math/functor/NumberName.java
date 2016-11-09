package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.stream.Stream;

import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;

@SuppressWarnings("WeakerAccess")
public class NumberName {
	private static final BigInteger MIN = ZERO;
	private static final BigInteger MAX = valueOf(1000L);

	public static Stream<String> numberNames(Stream<BigInteger> numStream) {
		return numStream.map(NumberName::numberName);
	}

	public static String numberName(BigInteger number) {
		if (number.compareTo(MIN) < 0 || number.compareTo(MAX) > 0) {
			throw new IllegalArgumentException("Argument must be in range [" + MIN + ", " + MAX + "], but got " + number);
		}

		if (number.equals(ZERO)) {
			return "zero";
		}

		int num = number.intValue();

		if (num < 1000) {
			return hundreds(num);
		}

		return "one-thousand";
	}

	private static String ones(int num) {
		switch (num) {
			case 0:
				return "zero";
			case 1:
				return "one";
			case 2:
				return "two";
			case 3:
				return "three";
			case 4:
				return "four";
			case 5:
				return "five";
			case 6:
				return "six";
			case 7:
				return "seven";
			case 8:
				return "eight";
			case 9:
				return "nine";
			default:
				throw new IllegalArgumentException("Expected argument in range [0-9]; got " + num);
		}
	}

	private static String tens(int num) {
		if (num < 10) {
			return ones(num);
		}

		switch (num) {
			case 10:
				return "ten";
			case 11:
				return "eleven";
			case 12:
				return "twelve";
			case 13:
				return "thirteen";
			case 14:
				return "fourteen";
			case 15:
				return "fifteen";
			case 16:
				return "sixteen";
			case 17:
				return "seventeen";
			case 18:
				return "eighteen";
			case 19:
				return "nineteen";
			case 20:
				return "twenty";
			case 30:
				return "thirty";
			case 40:
				return "forty";
			case 50:
				return "fifty";
			case 60:
				return "sixty";
			case 70:
				return "seventy";
			case 80:
				return "eighty";
			case 90:
				return "ninety";
		}

		if (num < 30) {
			return "twenty-" + ones(num - 20);
		}

		if (num < 40) {
			return "thirty-" + ones(num - 30);
		}

		if (num < 50) {
			return "forty-" + ones(num - 40);
		}

		if (num < 60) {
			return "fifty-" + ones(num - 50);
		}

		if (num < 70) {
			return "sixty-" + ones(num - 60);
		}

		if (num < 80) {
			return "seventy-" + ones(num - 70);
		}

		if (num < 90) {
			return "eighty-" + ones(num - 80);
		}

		if (num < 100) {
			return "ninety-" + ones(num - 90);
		}

		throw new IllegalArgumentException("Expected argument in range [10, 99]; got " + num);
	}

	private static String hundreds(int num) {
		if (num < 100) {
			return tens(num);
		}

		switch (num) {
			case 100:
				return "one-hundred";
			case 200:
				return "two-hundred";
			case 300:
				return "three-hundred";
			case 400:
				return "four-hundred";
			case 500:
				return "five-hundred";
			case 600:
				return "six-hundred";
			case 700:
				return "seven-hundred";
			case 800:
				return "eight-hundred";
			case 900:
				return "nine-hundred";
		}

		if (num < 200) {
			return "one-hundred and " + tens(num - 100);
		}

		if (num < 300) {
			return "two-hundred and " + tens(num - 200);
		}

		if (num < 400) {
			return "three-hundred and " + tens(num - 300);
		}

		if (num < 500) {
			return "four-hundred and " + tens(num - 400);
		}

		if (num < 600) {
			return "five-hundred and " + tens(num - 500);
		}

		if (num < 700) {
			return "six-hundred and " + tens(num - 600);
		}

		if (num < 800) {
			return "seven-hundred and " + tens(num - 700);
		}

		if (num < 900) {
			return "eight-hundred and " + tens(num - 800);
		}

		if (num < 1000) {
			return "nine-hundred and " + tens(num - 900);
		}

		throw new IllegalArgumentException("Expected argument in range [100, 999]; got " + num);
	}

	// Prevent instantiation of this utility class
	private NumberName() {}
}
