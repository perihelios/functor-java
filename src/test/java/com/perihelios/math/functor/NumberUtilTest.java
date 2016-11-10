package com.perihelios.math.functor;

import org.junit.Test;

import java.math.BigInteger;

import static com.perihelios.math.functor.NumberUtil.bigInt;
import static com.perihelios.math.functor.NumberUtil.bigInts;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NumberUtilTest {
	@Test
	public void bigInt_works() {
		assertThat(bigInt(2), is(BigInteger.valueOf(2L)));
	}

	@Test
	public void bigInts_works() {
		assertThat(bigInts().length, is(0));
		assertThat(bigInts(1), is(arrayContaining(BigInteger.valueOf(1L))));
		assertThat(bigInts(1, 2), is(arrayContaining(BigInteger.valueOf(1L), BigInteger.valueOf(2L))));
	}
}
