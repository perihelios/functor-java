package com.perihelios.math.functor;

public class RationalNumber extends Number implements Comparable<RationalNumber> {
	private static final PrimeEngine ENGINE = new SimplePrimeEngine(new BinarySearchSquareRootEngine());

	private final long numerator;
	private final long denominator;
	private final boolean negative;

	public RationalNumber(long numerator, long denominator) {
		if (numerator == 0L) {
			this.numerator = 0L;
			this.denominator = 1L;
			this.negative = false;

			return;
		}

		if (numerator < 0L) {
			numerator = -numerator;

			if (denominator < 0L) {
				denominator = -denominator;
				negative = false;
			} else {
				negative = true;
			}
		} else {
			if (denominator < 0L) {
				denominator = -denominator;
				negative = true;
			} else {
				negative = false;
			}
		}

		if (numerator == 1L) {
			this.numerator = 1L;
			this.denominator = denominator;

			return;
		}

		if (denominator == 1L) {
			this.numerator = numerator;
			this.denominator = 1L;

			return;
		}

		LowestCommonMultipleBucket bucket = new LowestCommonMultipleBucket(ENGINE);
		bucket.add(numerator);
		bucket.add(denominator);

		long lcm = bucket.lcm();

		this.numerator = lcm / denominator;
		this.denominator = lcm / numerator;
	}

	public long numerator() {
		return numerator;
	}

	public long denominator() {
		return denominator;
	}

	public RationalNumber multiply(long n) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	public RationalNumber multiply(RationalNumber n) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	public RationalNumber add(long n) {
		long signedNumerator = negative ? -numerator : numerator;

		return new RationalNumber(signedNumerator + n * denominator, denominator);
	}

	public RationalNumber add(RationalNumber n) {
		long signedNumerator = negative ? -numerator : numerator;
		long signedNumeratorN = n.negative ? -n.numerator : n.numerator;
		long denominator = this.denominator;
		long denominatorN = n.denominator;

		if (denominator == 1L) {
			return n.add(signedNumerator);
		}

		if (denominatorN == 1L) {
			return add(signedNumeratorN);
		}

		LowestCommonMultipleBucket bucket = new LowestCommonMultipleBucket(ENGINE);
		bucket.add(denominator);
		bucket.add(denominatorN);

		long lcm = bucket.lcm();

		return new RationalNumber(signedNumerator * (lcm / denominator) + signedNumeratorN * (lcm / denominatorN), lcm);
	}

	public RationalNumber subtract(long n) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	public RationalNumber subtract(RationalNumber n) {
		throw new UnsupportedOperationException("Method not implemented");
	}

	public boolean isInteger() {
		return numerator == 0L || denominator == 1L;
	}

	public boolean isNegative() {
		return negative;
	}

	public RationalNumber reciprocal() {
		if (negative) {
			return new RationalNumber(-denominator, numerator);
		} else {
			return new RationalNumber(denominator, numerator);
		}
	}

	public long signum() {
		if (negative) {
			return -1L;
		} else {
			return 1L;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		RationalNumber that = (RationalNumber) o;

		return negative == that.negative && numerator == that.numerator && denominator == that.denominator;
	}

	@Override
	public int hashCode() {
		return (negative ? 1 : 0) + 7 * Long.hashCode(numerator) + 31 * Long.hashCode(denominator);
	}

	@Override
	public int compareTo(RationalNumber o) {
		if (negative) {
			if (!o.negative) {
				return -1;
			}
		} else {
			if (o.negative) {
				return 1;
			}
		}

		if (denominator == 1L) {
			return Long.compare(numerator * o.denominator, o.numerator);
		}

		if (o.denominator == 1L) {
			return Long.compare(numerator, o.numerator * denominator);
		}

		LowestCommonMultipleBucket bucket = new LowestCommonMultipleBucket(ENGINE);
		bucket.add(denominator);
		bucket.add(o.denominator);

		long lcm = bucket.lcm();

		long numerator = this.numerator * lcm / denominator;
		long numeratorO = o.numerator * lcm / o.denominator;

		return Long.compare(numerator, numeratorO);
	}

	@Override
	public int intValue() {
		if (negative) {
			return (int) (-numerator / denominator);
		} else {
			return (int) (numerator / denominator);
		}
	}

	@Override
	public long longValue() {
		if (negative) {
			return -numerator / denominator;
		} else {
			return numerator / denominator;
		}
	}

	@Override
	public float floatValue() {
		if (negative) {
			return -numerator / (float) denominator;
		} else {
			return numerator / (float) denominator;
		}
	}

	@Override
	public double doubleValue() {
		if (negative) {
			return -numerator / (double) denominator;
		} else {
			return numerator / (double) denominator;
		}
	}

	@Override
	public String toString() {
		long signedNumerator = negative ? -numerator : numerator;

		if (denominator == 1L) {
			return String.valueOf(signedNumerator);
		}

		return signedNumerator + "/" + denominator;
	}
}
