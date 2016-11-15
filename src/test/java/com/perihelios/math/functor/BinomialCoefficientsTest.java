package com.perihelios.math.functor;

import org.junit.Test;

import static com.perihelios.math.functor.BinomialCoefficients.centralBinomialCoefficient;
import static com.perihelios.math.functor.NumberUtil.bigInt;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BinomialCoefficientsTest {
	@Test
	public void centralBinomialCoefficient_works() {
		assertThat(centralBinomialCoefficient(0), is(bigInt(1)));
		assertThat(centralBinomialCoefficient(1), is(bigInt(2)));
		assertThat(centralBinomialCoefficient(2), is(bigInt(6)));
		assertThat(centralBinomialCoefficient(3), is(bigInt(20)));
		assertThat(centralBinomialCoefficient(4), is(bigInt(70)));
		assertThat(centralBinomialCoefficient(5), is(bigInt(252)));
		assertThat(centralBinomialCoefficient(6), is(bigInt(924)));
	}
}
