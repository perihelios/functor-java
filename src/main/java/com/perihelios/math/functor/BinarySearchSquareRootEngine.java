package com.perihelios.math.functor;

import javax.annotation.Generated;
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

	@Override
	public long sqrtFloor(long n) {
		long[] range = range(n);
		long min = range[0];
		long max = range[1];

		while (min < max) {
			long partition = (min + max) >>> 1;

			long square = partition * partition;
			long comp = square - n;

			if (comp == 0) {
				return partition;
			}

			if (comp < 0) {
				min = partition + 1;
			} else {
				max = partition - 1;
			}
		}

		return min * min - n > 0 ? min - 1 : min;
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

	private static long[] range(long n) {
		long length = bitLength(n);
		long shiftForMaximum = ((length + 1) >>> 1) - 1;

		long maximum = n >>> shiftForMaximum;
		long minimum = maximum >>> 1;

		return new long[] {
			minimum,
			maximum
		};
	}

	// TODO: copied from square-root project
	@Generated("Generated in other project and lazily copied here. :-/")
	private static long bitLength(long value) {
		if ((value & 0b1111111111111111111111111111111100000000000000000000000000000000L) == 0L) {
			if ((value & 0b1111111111111111111111111111111111111111111111110000000000000000L) == 0L) {
				if ((value & 0b1111111111111111111111111111111111111111111111111111111100000000L) == 0L) {
					if ((value & 0b1111111111111111111111111111111111111111111111111111111111110000L) == 0L) {
						if ((value & 0b1111111111111111111111111111111111111111111111111111111111111100L) == 0L) {
							if ((value & 0b1111111111111111111111111111111111111111111111111111111111111110L) == 0L) return 1;
							else return 2;
						} else {
							if ((value & 0b1111111111111111111111111111111111111111111111111111111111111000L) == 0L) return 3;
							else return 4;
						}
					} else {
						if ((value & 0b1111111111111111111111111111111111111111111111111111111111000000L) == 0L) {
							if ((value & 0b1111111111111111111111111111111111111111111111111111111111100000L) == 0L) return 5;
							else return 6;
						} else {
							if ((value & 0b1111111111111111111111111111111111111111111111111111111110000000L) == 0L) return 7;
							else return 8;
						}
					}
				} else {
					if ((value & 0b1111111111111111111111111111111111111111111111111111000000000000L) == 0L) {
						if ((value & 0b1111111111111111111111111111111111111111111111111111110000000000L) == 0L) {
							if ((value & 0b1111111111111111111111111111111111111111111111111111111000000000L) == 0L) return 9;
							else return 10;
						} else {
							if ((value & 0b1111111111111111111111111111111111111111111111111111100000000000L) == 0L) return 11;
							else return 12;
						}
					} else {
						if ((value & 0b1111111111111111111111111111111111111111111111111100000000000000L) == 0L) {
							if ((value & 0b1111111111111111111111111111111111111111111111111110000000000000L) == 0L) return 13;
							else return 14;
						} else {
							if ((value & 0b1111111111111111111111111111111111111111111111111000000000000000L) == 0L) return 15;
							else return 16;
						}
					}
				}
			} else {
				if ((value & 0b1111111111111111111111111111111111111111000000000000000000000000L) == 0L) {
					if ((value & 0b1111111111111111111111111111111111111111111100000000000000000000L) == 0L) {
						if ((value & 0b1111111111111111111111111111111111111111111111000000000000000000L) == 0L) {
							if ((value & 0b1111111111111111111111111111111111111111111111100000000000000000L) == 0L) return 17;
							else return 18;
						} else {
							if ((value & 0b1111111111111111111111111111111111111111111110000000000000000000L) == 0L) return 19;
							else return 20;
						}
					} else {
						if ((value & 0b1111111111111111111111111111111111111111110000000000000000000000L) == 0L) {
							if ((value & 0b1111111111111111111111111111111111111111111000000000000000000000L) == 0L) return 21;
							else return 22;
						} else {
							if ((value & 0b1111111111111111111111111111111111111111100000000000000000000000L) == 0L) return 23;
							else return 24;
						}
					}
				} else {
					if ((value & 0b1111111111111111111111111111111111110000000000000000000000000000L) == 0L) {
						if ((value & 0b1111111111111111111111111111111111111100000000000000000000000000L) == 0L) {
							if ((value & 0b1111111111111111111111111111111111111110000000000000000000000000L) == 0L) return 25;
							else return 26;
						} else {
							if ((value & 0b1111111111111111111111111111111111111000000000000000000000000000L) == 0L) return 27;
							else return 28;
						}
					} else {
						if ((value & 0b1111111111111111111111111111111111000000000000000000000000000000L) == 0L) {
							if ((value & 0b1111111111111111111111111111111111100000000000000000000000000000L) == 0L) return 29;
							else return 30;
						} else {
							if ((value & 0b1111111111111111111111111111111110000000000000000000000000000000L) == 0L) return 31;
							else return 32;
						}
					}
				}
			}
		} else {
			if ((value & 0b1111111111111111000000000000000000000000000000000000000000000000L) == 0L) {
				if ((value & 0b1111111111111111111111110000000000000000000000000000000000000000L) == 0L) {
					if ((value & 0b1111111111111111111111111111000000000000000000000000000000000000L) == 0L) {
						if ((value & 0b1111111111111111111111111111110000000000000000000000000000000000L) == 0L) {
							if ((value & 0b1111111111111111111111111111111000000000000000000000000000000000L) == 0L) return 33;
							else return 34;
						} else {
							if ((value & 0b1111111111111111111111111111100000000000000000000000000000000000L) == 0L) return 35;
							else return 36;
						}
					} else {
						if ((value & 0b1111111111111111111111111100000000000000000000000000000000000000L) == 0L) {
							if ((value & 0b1111111111111111111111111110000000000000000000000000000000000000L) == 0L) return 37;
							else return 38;
						} else {
							if ((value & 0b1111111111111111111111111000000000000000000000000000000000000000L) == 0L) return 39;
							else return 40;
						}
					}
				} else {
					if ((value & 0b1111111111111111111100000000000000000000000000000000000000000000L) == 0L) {
						if ((value & 0b1111111111111111111111000000000000000000000000000000000000000000L) == 0L) {
							if ((value & 0b1111111111111111111111100000000000000000000000000000000000000000L) == 0L) return 41;
							else return 42;
						} else {
							if ((value & 0b1111111111111111111110000000000000000000000000000000000000000000L) == 0L) return 43;
							else return 44;
						}
					} else {
						if ((value & 0b1111111111111111110000000000000000000000000000000000000000000000L) == 0L) {
							if ((value & 0b1111111111111111111000000000000000000000000000000000000000000000L) == 0L) return 45;
							else return 46;
						} else {
							if ((value & 0b1111111111111111100000000000000000000000000000000000000000000000L) == 0L) return 47;
							else return 48;
						}
					}
				}
			} else {
				if ((value & 0b1111111100000000000000000000000000000000000000000000000000000000L) == 0L) {
					if ((value & 0b1111111111110000000000000000000000000000000000000000000000000000L) == 0L) {
						if ((value & 0b1111111111111100000000000000000000000000000000000000000000000000L) == 0L) {
							if ((value & 0b1111111111111110000000000000000000000000000000000000000000000000L) == 0L) return 49;
							else return 50;
						} else {
							if ((value & 0b1111111111111000000000000000000000000000000000000000000000000000L) == 0L) return 51;
							else return 52;
						}
					} else {
						if ((value & 0b1111111111000000000000000000000000000000000000000000000000000000L) == 0L) {
							if ((value & 0b1111111111100000000000000000000000000000000000000000000000000000L) == 0L) return 53;
							else return 54;
						} else {
							if ((value & 0b1111111110000000000000000000000000000000000000000000000000000000L) == 0L) return 55;
							else return 56;
						}
					}
				} else {
					if ((value & 0b1111000000000000000000000000000000000000000000000000000000000000L) == 0L) {
						if ((value & 0b1111110000000000000000000000000000000000000000000000000000000000L) == 0L) {
							if ((value & 0b1111111000000000000000000000000000000000000000000000000000000000L) == 0L) return 57;
							else return 58;
						} else {
							if ((value & 0b1111100000000000000000000000000000000000000000000000000000000000L) == 0L) return 59;
							else return 60;
						}
					} else {
						if ((value & 0b1100000000000000000000000000000000000000000000000000000000000000L) == 0L) {
							if ((value & 0b1110000000000000000000000000000000000000000000000000000000000000L) == 0L) return 61;
							else return 62;
						} else {
							if ((value & 0b1000000000000000000000000000000000000000000000000000000000000000L) == 0L) return 63;
							else return 64;
						}
					}
				}
			}
		}
	}
}
