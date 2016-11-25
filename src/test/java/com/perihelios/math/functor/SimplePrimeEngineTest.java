package com.perihelios.math.functor;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.perihelios.math.functor.TestUtil.treeMap;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class SimplePrimeEngineTest {
	private SimplePrimeEngine engine = new SimplePrimeEngine(new BinarySearchSquareRootEngine());

	@Test
	public void primeFactorsOf_works() {
		assertThat(engine.primeFactorsOf(0), is(treeMap(0L, 1L)));
		assertThat(engine.primeFactorsOf(1), is(treeMap(1L, 1L)));
		assertThat(engine.primeFactorsOf(2), is(treeMap(2L, 1L)));
		assertThat(engine.primeFactorsOf(3), is(treeMap(3L, 1L)));
		assertThat(engine.primeFactorsOf(4), is(treeMap(2L, 2L)));
		assertThat(engine.primeFactorsOf(5), is(treeMap(5L, 1L)));
		assertThat(engine.primeFactorsOf(6), is(treeMap(2L, 1L, 3L, 1L)));
		assertThat(engine.primeFactorsOf(61), is(treeMap(61L, 1L)));
		assertThat(engine.primeFactorsOf(120), is(treeMap(2L, 3L, 3L, 1L, 5L, 1L)));
		assertThat(engine.primeFactorsOf(11562909), is(treeMap(3L, 1L, 29L, 2L, 4583L, 1L)));
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
	public void primeFactorsOf_minimum() {
		try {
			engine.primeFactorsOf(-1L);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Minimum allowed argument is 0; got -1"));
		}
	}

	@Test
	public void isPrime_works() {
		assertThat(engine.isPrime(1), is(false));
		assertThat(engine.isPrime(2), is(true));
		assertThat(engine.isPrime(3), is(true));
		assertThat(engine.isPrime(4), is(false));
		assertThat(engine.isPrime(4583), is(true));
	}
}
