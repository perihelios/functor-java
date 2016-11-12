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

	// Prevent instantiation of this class
	private NumberUtil() {}
}
