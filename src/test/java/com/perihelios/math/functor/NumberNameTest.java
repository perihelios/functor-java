package com.perihelios.math.functor;

import org.junit.Test;

import java.util.stream.Stream;

import static com.perihelios.math.functor.NumberName.numberName;
import static com.perihelios.math.functor.NumberName.numberNames;
import static java.math.BigInteger.valueOf;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class NumberNameTest {
	@Test
	public void numberName_works() {
		assertThat(numberName(valueOf(   0L)), is("zero"));
		assertThat(numberName(valueOf(   1L)), is("one"));
		assertThat(numberName(valueOf(   2L)), is("two"));
		assertThat(numberName(valueOf(   3L)), is("three"));
		assertThat(numberName(valueOf(   4L)), is("four"));
		assertThat(numberName(valueOf(   5L)), is("five"));
		assertThat(numberName(valueOf(   6L)), is("six"));
		assertThat(numberName(valueOf(   7L)), is("seven"));
		assertThat(numberName(valueOf(   8L)), is("eight"));
		assertThat(numberName(valueOf(   9L)), is("nine"));
		assertThat(numberName(valueOf(  10L)), is("ten"));
		assertThat(numberName(valueOf(  11L)), is("eleven"));
		assertThat(numberName(valueOf(  12L)), is("twelve"));
		assertThat(numberName(valueOf(  13L)), is("thirteen"));
		assertThat(numberName(valueOf(  14L)), is("fourteen"));
		assertThat(numberName(valueOf(  15L)), is("fifteen"));
		assertThat(numberName(valueOf(  16L)), is("sixteen"));
		assertThat(numberName(valueOf(  17L)), is("seventeen"));
		assertThat(numberName(valueOf(  18L)), is("eighteen"));
		assertThat(numberName(valueOf(  19L)), is("nineteen"));
		assertThat(numberName(valueOf(  20L)), is("twenty"));
		assertThat(numberName(valueOf(  21L)), is("twenty-one"));
		assertThat(numberName(valueOf(  29L)), is("twenty-nine"));
		assertThat(numberName(valueOf(  30L)), is("thirty"));
		assertThat(numberName(valueOf(  31L)), is("thirty-one"));
		assertThat(numberName(valueOf(  39L)), is("thirty-nine"));
		assertThat(numberName(valueOf(  40L)), is("forty"));
		assertThat(numberName(valueOf(  41L)), is("forty-one"));
		assertThat(numberName(valueOf(  49L)), is("forty-nine"));
		assertThat(numberName(valueOf(  50L)), is("fifty"));
		assertThat(numberName(valueOf(  51L)), is("fifty-one"));
		assertThat(numberName(valueOf(  59L)), is("fifty-nine"));
		assertThat(numberName(valueOf(  60L)), is("sixty"));
		assertThat(numberName(valueOf(  61L)), is("sixty-one"));
		assertThat(numberName(valueOf(  69L)), is("sixty-nine"));
		assertThat(numberName(valueOf(  70L)), is("seventy"));
		assertThat(numberName(valueOf(  71L)), is("seventy-one"));
		assertThat(numberName(valueOf(  79L)), is("seventy-nine"));
		assertThat(numberName(valueOf(  80L)), is("eighty"));
		assertThat(numberName(valueOf(  81L)), is("eighty-one"));
		assertThat(numberName(valueOf(  89L)), is("eighty-nine"));
		assertThat(numberName(valueOf(  90L)), is("ninety"));
		assertThat(numberName(valueOf(  91L)), is("ninety-one"));
		assertThat(numberName(valueOf(  99L)), is("ninety-nine"));
		assertThat(numberName(valueOf( 100L)), is("one-hundred"));
		assertThat(numberName(valueOf( 101L)), is("one-hundred and one"));
		assertThat(numberName(valueOf( 199L)), is("one-hundred and ninety-nine"));
		assertThat(numberName(valueOf( 200L)), is("two-hundred"));
		assertThat(numberName(valueOf( 201L)), is("two-hundred and one"));
		assertThat(numberName(valueOf( 299L)), is("two-hundred and ninety-nine"));
		assertThat(numberName(valueOf( 300L)), is("three-hundred"));
		assertThat(numberName(valueOf( 301L)), is("three-hundred and one"));
		assertThat(numberName(valueOf( 399L)), is("three-hundred and ninety-nine"));
		assertThat(numberName(valueOf( 400L)), is("four-hundred"));
		assertThat(numberName(valueOf( 401L)), is("four-hundred and one"));
		assertThat(numberName(valueOf( 499L)), is("four-hundred and ninety-nine"));
		assertThat(numberName(valueOf( 500L)), is("five-hundred"));
		assertThat(numberName(valueOf( 501L)), is("five-hundred and one"));
		assertThat(numberName(valueOf( 599L)), is("five-hundred and ninety-nine"));
		assertThat(numberName(valueOf( 600L)), is("six-hundred"));
		assertThat(numberName(valueOf( 601L)), is("six-hundred and one"));
		assertThat(numberName(valueOf( 699L)), is("six-hundred and ninety-nine"));
		assertThat(numberName(valueOf( 700L)), is("seven-hundred"));
		assertThat(numberName(valueOf( 701L)), is("seven-hundred and one"));
		assertThat(numberName(valueOf( 799L)), is("seven-hundred and ninety-nine"));
		assertThat(numberName(valueOf( 800L)), is("eight-hundred"));
		assertThat(numberName(valueOf( 801L)), is("eight-hundred and one"));
		assertThat(numberName(valueOf( 899L)), is("eight-hundred and ninety-nine"));
		assertThat(numberName(valueOf( 900L)), is("nine-hundred"));
		assertThat(numberName(valueOf( 901L)), is("nine-hundred and one"));
		assertThat(numberName(valueOf( 999L)), is("nine-hundred and ninety-nine"));
		assertThat(numberName(valueOf(1000L)), is("one-thousand"));
	}

	@Test
	public void numberName_minimum() {
		try {
			numberName(valueOf(-1L));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Argument must be in range [0, 1000], but got -1"));
		}
	}

	@Test
	public void numberName_maximum() {
		try {
			numberName(valueOf(1001L));
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Argument must be in range [0, 1000], but got 1001"));
		}
	}

	@Test
	public void numberNames_works() {
		assertThat(
			numberNames(Stream.of(valueOf(0L), valueOf(1L), valueOf(10L), valueOf(153L)))
				.collect(toList()),
			is(asList(
				"zero", "one", "ten", "one-hundred and fifty-three"
			))
		);

	}
}
