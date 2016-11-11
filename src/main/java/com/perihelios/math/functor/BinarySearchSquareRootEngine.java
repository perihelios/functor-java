package com.perihelios.math.functor;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;

public class BinarySearchSquareRootEngine implements SquareRootEngine {
	@Override
	public BigInteger sqrtFloor(BigInteger n) {
		BigInteger[] range = range(n);
		BigInteger min = range[0];
		BigInteger max = range[1];

		while (min.compareTo(max) < 0) {
			BigInteger partition = min.add(max).shiftRight(1);

			BigInteger square = partition.multiply(partition);
			int comp = square.compareTo(n);

			if (comp == 0) {
				return partition;
			}

			if (comp < 0) {
				min = partition.add(ONE);
			} else {
				max = partition.subtract(ONE);
			}
		}

		return min.multiply(min).compareTo(n) > 0 ? min.subtract(ONE) : min;
	}

	private static BigInteger[] range(BigInteger n) {
		int length = n.bitLength();
		int shiftForMaximum = ((length + 1) >>> 1) - 1;

		BigInteger maximum = n.shiftRight(shiftForMaximum);
		BigInteger minimum = maximum.shiftRight(1);

		return new BigInteger[] {
			minimum,
			maximum
		};
	}
}
