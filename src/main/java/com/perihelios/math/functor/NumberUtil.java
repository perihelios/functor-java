package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import static java.math.BigInteger.ONE;

public class NumberUtil {
	private static final PrimeEngine PRIME_ENGINE = new SimplePrimeEngine(new BinarySearchSquareRootEngine());

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

	public static BigInteger[] bigInts(String... nums) {
		BigInteger[] bigIntegers = new BigInteger[nums.length];

		for (int i = 0; i < nums.length; i++) {
			bigIntegers[i] = new BigInteger(nums[i]);
		}

		return bigIntegers;
	}

	public static BigInteger[][] gridFromString(String grid) {
		grid = grid.trim();

		if (grid.isEmpty()) {
			return new BigInteger[0][];
		}

		String[] rows = grid.split("\r\n|\n");
		int rowCount = rows.length;
		int columnCount = -1;

		BigInteger[][] arr = new BigInteger[rowCount][];

		for (int r = 0; r < rowCount; r++) {
			String row = rows[r].trim();

			String[] columns;
			if (row.length() == 0) {
				columns = new String[0];
			} else {
				columns = row.split("\\s+");
			}

			if (columnCount < 0) {
				columnCount = columns.length;
			} else {
				if (columns.length != columnCount) {
					throw new IllegalArgumentException("Row " + r + " has " + columns.length + " column(s); expected " + columnCount);
				}
			}

			arr[r] = new BigInteger[columnCount];

			for (int c = 0; c < columnCount; c++) {
				BigInteger value;

				try {
					value = new BigInteger(columns[c]);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("Value at " + r + ", " + c + " not a decimal integer: " + columns[c]);
				}

				arr[r][c] = value;
			}
		}

		return arr;
	}

	public static BigInteger[][] triangleFromString(String triangle) {
		triangle = triangle.trim();

		if (triangle.isEmpty()) {
			return new BigInteger[0][];
		}

		String[] rows = triangle.split("\r\n|\n");
		int rowCount = rows.length;
		int columnCount = 1;

		BigInteger[][] arr = new BigInteger[rowCount][];

		for (int r = 0; r < rowCount; r++) {
			String row = rows[r].trim();

			String[] columns;
			if (row.length() == 0) {
				columns = new String[0];
			} else {
				columns = row.split("\\s+");
			}

			if (columns.length != columnCount) {
				throw new IllegalArgumentException("Row " + r + " has " + columns.length + " column(s); expected " + columnCount);
			}

			arr[r] = new BigInteger[columnCount];

			for (int c = 0; c < columnCount; c++) {
				BigInteger value;

				try {
					value = new BigInteger(columns[c]);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException("Value at " + r + ", " + c + " not a decimal integer: " + columns[c]);
				}

				arr[r][c] = value;
			}

			columnCount++;
		}

		return arr;
	}

	public static long distinctFactorCount(long n) {
		if (n <= 1L) {
			return 1L;
		}

		return PRIME_ENGINE.primeFactorsCountsOf(n).values().stream()
			.mapToLong(i -> i + 1L)
			.reduce(1L, (a, b) -> a * b);
	}

	public static BigInteger factorial(BigInteger n) {
		if (n.signum() == -1) {
			throw new IllegalArgumentException("Factorial not possible for negative number; got " + n);
		}

		if (n.compareTo(ONE) <= 0) {
			return ONE;
		}

		BigInteger product = n;
		n = n.subtract(ONE);

		while (n.compareTo(ONE) > 0) {
			product = product.multiply(n);
			n = n.subtract(ONE);
		}

		return product;
	}

	public static SortedSet<Long> properDivisors(long n) {
		if (n < 1L) throw new IllegalArgumentException("Argument must be greater than 0; got " + n);
		if (n == 1L) return Collections.emptySortedSet();

		SortedSet<Long> divisors = new TreeSet<>();

		for (long trialDivisor = 2L; trialDivisor < n; trialDivisor++) {
			if (n % trialDivisor == 0) {
				divisors.add(trialDivisor);
			}
		}

		divisors.add(1L);

		return divisors;
	}

	public static long digitSum(long n) {
		if (n < 0L) throw new IllegalArgumentException("Only non-negative numbers supported; got " + n);

		long sum = 0L;

		for (char digit : String.valueOf(n).toCharArray()) {
			sum += digit - '0';
		}

		return sum;
	}

	public static long digitSum(BigInteger n) {
		if (n.signum() < 0) throw new IllegalArgumentException("Only non-negative numbers supported; got " + n);

		long sum = 0L;

		for (char digit : n.toString().toCharArray()) {
			sum += digit - '0';
		}

		return sum;
	}

	// Prevent instantiation of this class
	private NumberUtil() {}
}
