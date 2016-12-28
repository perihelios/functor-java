package com.perihelios.math.functor;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.perihelios.math.functor.TestUtil.treeMap;
import static com.perihelios.math.functor.TestUtil.treeSet;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class SimplePrimeEngineTest {
	private SimplePrimeEngine engine = new SimplePrimeEngine(new BinarySearchSquareRootEngine());

	@Test
	public void primeFactorCountsOf_works() {
		assertThat(engine.primeFactorsCountsOf(0), is(treeMap(0L, 1L)));
		assertThat(engine.primeFactorsCountsOf(1), is(treeMap(1L, 1L)));
		assertThat(engine.primeFactorsCountsOf(2), is(treeMap(2L, 1L)));
		assertThat(engine.primeFactorsCountsOf(3), is(treeMap(3L, 1L)));
		assertThat(engine.primeFactorsCountsOf(4), is(treeMap(2L, 2L)));
		assertThat(engine.primeFactorsCountsOf(5), is(treeMap(5L, 1L)));
		assertThat(engine.primeFactorsCountsOf(6), is(treeMap(2L, 1L, 3L, 1L)));
		assertThat(engine.primeFactorsCountsOf(61), is(treeMap(61L, 1L)));
		assertThat(engine.primeFactorsCountsOf(106L), is(treeMap(2L, 1L, 53L, 1L)));
		assertThat(engine.primeFactorsCountsOf(120), is(treeMap(2L, 3L, 3L, 1L, 5L, 1L)));
		assertThat(engine.primeFactorsCountsOf(11562909), is(treeMap(3L, 1L, 29L, 2L, 4583L, 1L)));
	}

	@Test
	public void primeFactorsCountsOf_minimum() {
		try {
			engine.primeFactorsCountsOf(-1L);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Minimum allowed argument is 0; got -1"));
		}
	}

	@Test
	public void primeFactorsOf_works() {
		assertThat(engine.primeFactorsOf(0L), is(asList(0L)));
		assertThat(engine.primeFactorsOf(1L), is(asList(1L)));
		assertThat(engine.primeFactorsOf(2L), is(asList(2L)));
		assertThat(engine.primeFactorsOf(3L), is(asList(3L)));
		assertThat(engine.primeFactorsOf(4L), is(asList(2L, 2L)));
		assertThat(engine.primeFactorsOf(5L), is(asList(5L)));
		assertThat(engine.primeFactorsOf(6L), is(asList(2L, 3L)));
		assertThat(engine.primeFactorsOf(106L), is(asList(2L, 53L)));
		assertThat(engine.primeFactorsOf(120L), is(asList(2L, 2L, 2L, 3L, 5L)));
		assertThat(engine.primeFactorsOf(11562909L), is(asList(3L, 29L, 29L, 4583L)));
	}

	@Test
	public void primeFactorsOf_minimum() {
		try {
			engine.primeFactorsOf(-1L);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Minimum allowed argument is 0; got -1"));
		}
	}

	@Test
	public void distinctPrimeFactorsOf_works() {
		assertThat(engine.distinctPrimeFactorsOf(0L), is(treeSet(0L)));
		assertThat(engine.distinctPrimeFactorsOf(1L), is(treeSet(1L)));
		assertThat(engine.distinctPrimeFactorsOf(2L), is(treeSet(2L)));
		assertThat(engine.distinctPrimeFactorsOf(3L), is(treeSet(3L)));
		assertThat(engine.distinctPrimeFactorsOf(4L), is(treeSet(2L)));
		assertThat(engine.distinctPrimeFactorsOf(5L), is(treeSet(5L)));
		assertThat(engine.distinctPrimeFactorsOf(6L), is(treeSet(2L, 3L)));
		assertThat(engine.distinctPrimeFactorsOf(106L), is(treeSet(2L, 53L)));
		assertThat(engine.distinctPrimeFactorsOf(120L), is(treeSet(2L, 3L, 5L)));
		assertThat(engine.distinctPrimeFactorsOf(11562909L), is(treeSet(3L, 29L, 4583L)));
	}

	@Test
	public void distinctPrimeFactorsOf_minimum() {
		try {
			engine.distinctPrimeFactorsOf(-1L);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Minimum allowed argument is 0; got -1"));
		}
	}

	@Test
	public void primes_works() {
		assertThat(
			engine.primes().limit(20).boxed().collect(Collectors.toList()),
			is(asList(2L, 3L, 5L, 7L, 11L, 13L, 17L, 19L, 23L, 29L, 31L, 37L, 41L, 43L, 47L, 53L, 59L, 61L, 67L, 71L))
		);
	}

	@Test
	public void primes_works_when_calling_isPrime_in_stream() {
		AtomicLong saved = new AtomicLong();

		engine.primes()
			.peek(n -> {
				assertThat(n, not(saved.get()));
				saved.set(n);
				engine.isPrime(1);
			})
			.limit(100)
			.sum();
	}

	@Test
	public void isPrime_works() {
		assertThat(engine.isPrime(1), is(false));
		assertThat(engine.isPrime(2), is(true));
		assertThat(engine.isPrime(3), is(true));
		assertThat(engine.isPrime(4), is(false));
		assertThat(engine.isPrime(4583), is(true));
		assertThat(engine.isPrime(4584), is(false));
		assertThat(engine.isPrime(4583 * 4583), is(false));
		assertThat(engine.isPrime(999983), is(true));
	}
}
