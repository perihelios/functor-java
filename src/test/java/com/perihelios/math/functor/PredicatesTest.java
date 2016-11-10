package com.perihelios.math.functor;

import org.junit.Test;

import static com.perihelios.math.functor.Predicates.even;
import static com.perihelios.math.functor.Predicates.odd;
import static java.math.BigInteger.valueOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PredicatesTest {
	@Test
	public void even_BigInteger() {
		assertThat(even(valueOf(0L)), is(true));
		assertThat(even(valueOf(1L)), is(false));
		assertThat(even(valueOf(-1L)), is(false));
		assertThat(even(valueOf(2L)), is(true));
		assertThat(even(valueOf(-2L)), is(true));
	}

	@Test
	public void odd_BigInteger() {
		assertThat(odd(valueOf(0L)), is(false));
		assertThat(odd(valueOf(1L)), is(true));
		assertThat(odd(valueOf(-1L)), is(true));
		assertThat(odd(valueOf(2L)), is(false));
		assertThat(odd(valueOf(-2L)), is(false));
	}

	@Test
	public void even_long() {
		assertThat(even(0L), is(true));
		assertThat(even(1L), is(false));
		assertThat(even(-1L), is(false));
		assertThat(even(2L), is(true));
		assertThat(even(-2L), is(true));
	}

	@Test
	public void odd_long() {
		assertThat(odd(0L), is(false));
		assertThat(odd(1L), is(true));
		assertThat(odd(-1L), is(true));
		assertThat(odd(2L), is(false));
		assertThat(odd(-2L), is(false));
	}
}
