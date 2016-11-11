package com.perihelios.math.functor;

import org.junit.Test;

import static com.perihelios.math.functor.NumberUtil.bigInt;
import static com.perihelios.math.functor.TestUtil.treeMap;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimplePrimeEngineTest {
	private SimplePrimeEngine engine = new SimplePrimeEngine(new BinarySearchSquareRootEngine());

	@Test
	public void primeFactorsOf_works() {
		assertThat(engine.primeFactorsOf(bigInt(0)), is(treeMap(bigInt(0), 1L)));
		assertThat(engine.primeFactorsOf(bigInt(1)), is(treeMap(bigInt(1), 1L)));
		assertThat(engine.primeFactorsOf(bigInt(2)), is(treeMap(bigInt(2), 1L)));
		assertThat(engine.primeFactorsOf(bigInt(3)), is(treeMap(bigInt(3), 1L)));
		assertThat(engine.primeFactorsOf(bigInt(4)), is(treeMap(bigInt(2), 2L)));
		assertThat(engine.primeFactorsOf(bigInt(5)), is(treeMap(bigInt(5), 1L)));
		assertThat(engine.primeFactorsOf(bigInt(6)), is(treeMap(bigInt(2), 1L, bigInt(3), 1L)));
		assertThat(engine.primeFactorsOf(bigInt(120)), is(treeMap(bigInt(2), 3L, bigInt(3), 1L, bigInt(5), 1L)));
		assertThat(engine.primeFactorsOf(bigInt(11562909)), is(treeMap(bigInt(3), 1L, bigInt(29), 2L, bigInt(4583), 1L)));
	}

	@Test
	public void primeFactorsOf_minimum() {
		try {
			engine.primeFactorsOf(bigInt(-1L));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Minimum allowed argument is 0; got -1"));
		}
	}
}
