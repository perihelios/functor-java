package com.perihelios.math.functor;

import org.junit.Test;

import java.util.stream.Collectors;

import static com.perihelios.math.functor.Fibonacci.fibonacci;
import static java.math.BigInteger.valueOf;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FibonacciTest {
	@Test
	public void fibonacci_BigDecimal() {
		assertThat(
			fibonacci(valueOf(2L), valueOf(3L)).limit(5).collect(Collectors.toList()),
			is(asList(valueOf(2L), valueOf(3L), valueOf(5L), valueOf(8L), valueOf(13L)))
		);
	}

	@Test
	public void fibonacci_long() {
		assertThat(
			fibonacci(2L, 3L).limit(5).collect(Collectors.toList()),
			is(asList(valueOf(2L), valueOf(3L), valueOf(5L), valueOf(8L), valueOf(13L)))
		);
	}

	@Test
	public void fibonacci_nullary() {
		assertThat(
			fibonacci().limit(7).collect(Collectors.toList()),
			is(asList(valueOf(1L), valueOf(1L), valueOf(2L), valueOf(3L), valueOf(5L), valueOf(8L), valueOf(13L)))
		);
	}
}
