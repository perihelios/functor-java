package com.perihelios.math.functor;

import org.junit.Test;

import java.math.BigInteger;

import static com.perihelios.math.functor.Predicates.even;
import static com.perihelios.math.functor.Predicates.odd;
import static com.perihelios.math.functor.Predicates.palindrome;
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

	@Test
	public void palindrome_BigInteger() {
		assertThat(palindrome(new BigInteger("11111122222322222111111")), is(true));
		assertThat(palindrome(new BigInteger("11111122222332222111111")), is(false));
	}

	@Test
	public void palindrome_long() {
		assertThat(palindrome(1111122223222211111L), is(true));
		assertThat(palindrome(1111122223322211111L), is(false));
	}

	@Test
	public void palindrome_String() {
		assertThat(palindrome("aba"), is(true));
		assertThat(palindrome("abb"), is(false));
	}

	@Test
	public void palindrome_char_array() {
		assertThat(palindrome("".toCharArray()), is(true));
		assertThat(palindrome("a".toCharArray()), is(true));
		assertThat(palindrome("aa".toCharArray()), is(true));
		assertThat(palindrome("ab".toCharArray()), is(false));
		assertThat(palindrome("aba".toCharArray()), is(true));
		assertThat(palindrome("abb".toCharArray()), is(false));
		assertThat(palindrome("abba".toCharArray()), is(true));
	}
}
