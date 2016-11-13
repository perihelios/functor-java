package com.perihelios.math.functor;

import java.math.BigInteger;

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

	public static BigInteger distinctFactorCount(BigInteger n) {
		if (n.compareTo(ONE) <= 0) {
			return ONE;
		}

		return BigInteger.valueOf(PRIME_ENGINE.primeFactorsOf(n).values().stream()
			.mapToLong(i -> i + 1L)
			.reduce(1L, (a, b) -> a * b));
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

	// Prevent instantiation of this class
	private NumberUtil() {}
}
