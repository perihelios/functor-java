package com.perihelios.math.functor;

import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BinarySearchSquareRootEngineTest {
	private final BinarySearchSquareRootEngine engine = new BinarySearchSquareRootEngine();

	@Test
	public void sqrtFloor_works() {
		for (long i = 0; i < 100_000; i++) {
			BigInteger expected = BigInteger.valueOf((long) Math.sqrt(i));
			BigInteger actual = engine.sqrtFloor(BigInteger.valueOf(i));

			assertThat("Failed on square root of " + i, expected, is(actual));
		}

		// Arbitrary large value
		assertThat(engine.sqrtFloor(new BigInteger("51098462398734928746439878")), is(new BigInteger("7148318851221")));
	}
}
