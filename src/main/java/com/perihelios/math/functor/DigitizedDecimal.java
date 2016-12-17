package com.perihelios.math.functor;

import java.util.Arrays;

import static java.lang.Math.max;

public class DigitizedDecimal extends Number implements Comparable<DigitizedDecimal> {
	private final boolean negative;
	private final long[] digits;
	private final int offset;

	public DigitizedDecimal(long n) {
		if (n < 0) {
			if (n == Long.MIN_VALUE) {
				throw new IllegalArgumentException("Cannot represent Long.MIN_VALUE; minimum representable number is Long.MIN_VALUE + 1");
			}

			this.negative = true;
			n = -n;
		} else {
			this.negative = false;
		}

		char[] digitsChars = String.valueOf(n).toCharArray();
		long[] digits = new long[digitsChars.length];

		for (int i = 0; i < digitsChars.length; i++) {
			digits[i] = digitsChars[i] - '0';
		}

		this.digits = digits;
		this.offset = 0;
	}

	private DigitizedDecimal(boolean negative, long[] digits, int offset) {
		this.negative = negative;
		this.digits = digits;
		this.offset = offset;
	}

	public DigitizedDecimal add(DigitizedDecimal n) {
		if (negative && n.negative) {
			long[] result = add(digits, offset, n.digits, n.offset);

			if (result.length > 1 && result[0] == 0) {
				return new DigitizedDecimal(true, result, 1);
			} else {
				return new DigitizedDecimal(true, result, 0);
			}
		}

		if (!negative && !n.negative) {
			long[] result = add(digits, offset, n.digits, n.offset);

			if (result.length > 1 && result[0] == 0) {
				return new DigitizedDecimal(false, result, 1);
			} else {
				return new DigitizedDecimal(false, result, 0);
			}
		}

		if (negative) {
			long[] result = subtract(n.digits, n.offset, digits, offset);
			boolean negative = result[0] < 0;
			int nonZeroOffset = 1;

			while (nonZeroOffset < result.length - 1) {
				if (result[nonZeroOffset] == 0) {
					nonZeroOffset++;
				} else {
					break;
				}
			}

			return new DigitizedDecimal(negative, result, nonZeroOffset);
		}

		long[] result = subtract(digits, offset, n.digits, n.offset);

		boolean negative = result[0] < 0;
		int nonZeroOffset = 1;

		while (nonZeroOffset < result.length - 1) {
			if (result[nonZeroOffset] == 0) {
				nonZeroOffset++;
			} else {
				break;
			}
		}

		return new DigitizedDecimal(negative, result, nonZeroOffset);
	}

	public DigitizedDecimal increment() {
		int length = length();
		long[] newDigits = new long[length + 1];

		System.arraycopy(digits, offset, newDigits, 1, length);

		if (negative) {
			throw new UnsupportedOperationException("Incrementing negative numbers is currently not supported (attempted on value: " + this + ")");
		} else {
			increment(newDigits);

			if (newDigits[0] != 0) {
				return new DigitizedDecimal(false, newDigits, 0);
			} else {
				return new DigitizedDecimal(false, newDigits, 1);
			}
		}
	}

	public boolean digitsAscending() {
		int index = offset;
		long value = digits[index++];

		while (index < digits.length) {
			long next = digits[index++];

			if (value > next) {
				return false;
			}

			value = next;
		}

		return true;
	}

	public boolean digitsStrictlyAscending() {
		int index = offset;
		long value = digits[index++];

		while (index < digits.length) {
			long next = digits[index++];

			if (value >= next) {
				return false;
			}

			value = next;
		}

		return true;
	}

	public boolean digitsDescending() {
		int index = offset;
		long value = digits[index++];

		while (index < digits.length) {
			long next = digits[index++];

			if (value < next) {
				return false;
			}

			value = next;
		}

		return true;
	}

	public boolean digitsStrictlyDescending() {
		int index = offset;
		long value = digits[index++];

		while (index < digits.length) {
			long next = digits[index++];

			if (value <= next) {
				return false;
			}

			value = next;
		}

		return true;
	}

	public DigitizedDecimal reverseDigits() {
		int length = length();

		for (int i = digits.length - 1; i > offset; i--) {
			if (digits[i] != 0) break;

			length--;
		}

		long[] newDigits = new long[length];

		for (int index = offset + length - 1, newDigitsIndex = 0; newDigitsIndex < length; index--, newDigitsIndex++) {
			newDigits[newDigitsIndex] = digits[index];
		}

		return new DigitizedDecimal(negative, newDigits, 0);
	}

	public boolean digitsAllOdd() {
		for (long digit : digits) {
			if ((digit & 1) == 0) return false;
		}

		return true;
	}

	public boolean digitsAllEven() {
		for (long digit : digits) {
			if ((digit & 1) != 0) return false;
		}

		return true;
	}

	@Override
	public int intValue() {
		return (int) longValue();
	}

	@Override
	public long longValue() {
		long positionalMultiplier = 10;
		int index = digits.length - 1;
		long value = digits[index--];

		for (; index >= offset; index--) {
			value += digits[index] * positionalMultiplier;
			positionalMultiplier *= 10;
		}

		return negative ? -value : value;
	}

	@Override
	public float floatValue() {
		return longValue();
	}

	@Override
	public double doubleValue() {
		return longValue();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || o.getClass() != DigitizedDecimal.class) return false;

		DigitizedDecimal that = (DigitizedDecimal) o;

		if (negative != that.negative) return false;
		if (length() != that.length()) return false;

		for (int index = digits.length - 1, indexThat = that.digits.length - 1; index >= offset; index--, indexThat--) {
			if (digits[index] != that.digits[indexThat]) return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(digits) ^ (negative ? 1 : 0);
	}

	@Override
	public int compareTo(DigitizedDecimal o) {
		if (negative) {
			if (o.negative) {
				if (length() > o.length()) {
					return -1;
				} else if (length() < o.length()) {
					return 1;
				} else {
					return digitCompare(o.digits, o.offset, digits, offset);
				}
			} else {
				return -1;
			}
		} else {
			if (o.negative) {
				return 1;
			} else {
				if (length() > o.length()) {
					return 1;
				} else if (length() < o.length()) {
					return -1;
				} else {
					return digitCompare(digits, offset, o.digits, o.offset);
				}
			}
		}
	}

	private int length() {
		return digits.length - offset;
	}

	/**
	 * Compare digits in two arrays of <strong>equal length</strong>.
	 *
	 * @param a array of digits from left number
	 * @param b array of digits from right number
	 * @return -1, 0, or 1 to indicate left < right, left == right, and left > right, respectively
	 */
	private static int digitCompare(long[] a, int offsetA, long[] b, int offsetB) {
		for (int indexA = offsetA, indexB = offsetB; indexA < a.length; indexA++, indexB++) {
			long digitA = a[indexA];
			long digitB = b[indexB];

			if (digitA > digitB) {
				return 1;
			}

			if (digitB > digitA) {
				return -1;
			}
		}

		return 0;
	}

	@Override
	public String toString() {
		char[] n;
		int nIndex;

		if (negative) {
			n = new char[length() + 1];
			n[0] = '-';
			nIndex = 1;
		} else {
			n = new char[length()];
			nIndex = 0;
		}

		for (int i = offset; i < digits.length; i++) {
			n[nIndex++] = (char) (digits[i] + '0');
		}

		return new String(n);
	}

	private static long[] add(long[] a, int offsetA, long[] b, int offsetB) {
		long[] newDigits = new long[max(a.length - offsetA, b.length - offsetB) + 1];
		int newDigitsIndex = newDigits.length - 1;

		int indexA = a.length - 1;
		int indexB = b.length - 1;
		long carry = 0;

		while (indexA >= offsetA && indexB >= offsetB) {
			long value = a[indexA--] + b[indexB--] + carry;

			if (value >= 10) {
				value -= 10;
				carry = 1;
			} else {
				carry = 0;
			}

			newDigits[newDigitsIndex--] = value;
		}

		while (indexA >= offsetA) {
			long value = a[indexA--] + carry;

			if (value >= 10) {
				value -= 10;
				carry = 1;
			} else {
				carry = 0;
			}

			newDigits[newDigitsIndex--] = value;
		}

		while (indexB >= offsetB) {
			long value = b[indexB--] + carry;

			if (value >= 10) {
				value -= 10;
				carry = 1;
			} else {
				carry = 0;
			}

			newDigits[newDigitsIndex--] = value;
		}

		if (carry != 0) {
			newDigits[newDigitsIndex] = carry;
		}

		return newDigits;
	}

	private static long[] subtract(long[] a, int offsetA, long[] b, int offsetB) {
		long[] newDigits = new long[max(a.length - offsetA, b.length - offsetB) + 1];
		int newDigitsIndex = newDigits.length - 1;

		int indexA = a.length - 1;
		int indexB = b.length - 1;
		long borrow = 0;

		while (indexA >= offsetA && indexB >= offsetB) {
			long value = a[indexA--] - b[indexB--] - borrow;

			if (value < 0) {
				value += 10;
				borrow = 1;
			} else {
				borrow = 0;
			}

			newDigits[newDigitsIndex--] = value;
		}

		while (indexA >= offsetA) {
			long value = a[indexA--] - borrow;

			if (value < 0) {
				value += 10;
				borrow = 1;
			} else {
				borrow = 0;
			}

			newDigits[newDigitsIndex--] = value;
		}

		while (indexB >= offsetB) {
			long value = -b[indexB--] - borrow;

			if (value < 0) {
				value += 10;
				borrow = 1;
			} else {
				borrow = 0;
			}

			newDigits[newDigitsIndex--] = value;
		}

		if (borrow != 0) {
			// Performs 10s-complement adjustment
			for (int i = newDigitsIndex + 1; i < newDigits.length; i++) {
				newDigits[i] = 9 - newDigits[i];
			}

			newDigits[newDigitsIndex] = -borrow;

			increment(newDigits);
		}

		return newDigits;
	}

	private static void increment(long[] a) {
		for (int i = a.length - 1; i >= 0; i--) {
			long value = a[i] + 1;

			if (value == 10) {
				a[i] = 0;
			} else {
				a[i] = value;
				break;
			}
		}
	}
}
