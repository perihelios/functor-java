package com.perihelios.math.functor;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class DigitizedDecimalTest {
	@Test
	public void constructor_throws_for_Long_MIN_VALUE() {
		try {
			new DigitizedDecimal(Long.MIN_VALUE);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Cannot represent Long.MIN_VALUE; minimum representable number is Long.MIN_VALUE + 1"));
		}
	}

	@Test
	public void add_works() {
		assertThat(new DigitizedDecimal(0).add(new DigitizedDecimal(0)), is(new DigitizedDecimal(0)));
		assertThat(new DigitizedDecimal(0).add(new DigitizedDecimal(1)), is(new DigitizedDecimal(1)));
		assertThat(new DigitizedDecimal(1).add(new DigitizedDecimal(0)), is(new DigitizedDecimal(1)));

		assertThat(new DigitizedDecimal(1).add(new DigitizedDecimal(1)), is(new DigitizedDecimal(2)));
		assertThat(new DigitizedDecimal(4).add(new DigitizedDecimal(5)), is(new DigitizedDecimal(9)));
		assertThat(new DigitizedDecimal(5).add(new DigitizedDecimal(5)), is(new DigitizedDecimal(10)));
		assertThat(new DigitizedDecimal(9999).add(new DigitizedDecimal(999)), is(new DigitizedDecimal(10998)));
		assertThat(new DigitizedDecimal(999).add(new DigitizedDecimal(9999)), is(new DigitizedDecimal(10998)));

		assertThat(new DigitizedDecimal(0).add(new DigitizedDecimal(-1)), is(new DigitizedDecimal(-1)));
		assertThat(new DigitizedDecimal(-1).add(new DigitizedDecimal(0)), is(new DigitizedDecimal(-1)));
		assertThat(new DigitizedDecimal(1).add(new DigitizedDecimal(-1)), is(new DigitizedDecimal(0)));
		assertThat(new DigitizedDecimal(0).add(new DigitizedDecimal(-9)), is(new DigitizedDecimal(-9)));
		assertThat(new DigitizedDecimal(-9).add(new DigitizedDecimal(0)), is(new DigitizedDecimal(-9)));
		assertThat(new DigitizedDecimal(-10).add(new DigitizedDecimal(1)), is(new DigitizedDecimal(-9)));
		assertThat(new DigitizedDecimal(1).add(new DigitizedDecimal(-10)), is(new DigitizedDecimal(-9)));
		assertThat(new DigitizedDecimal(10).add(new DigitizedDecimal(-1)), is(new DigitizedDecimal(9)));
		assertThat(new DigitizedDecimal(-1).add(new DigitizedDecimal(10)), is(new DigitizedDecimal(9)));
		assertThat(new DigitizedDecimal(-10).add(new DigitizedDecimal(10)), is(new DigitizedDecimal(0)));
		assertThat(new DigitizedDecimal(10).add(new DigitizedDecimal(-10)), is(new DigitizedDecimal(0)));
		assertThat(new DigitizedDecimal(20).add(new DigitizedDecimal(-11)), is(new DigitizedDecimal(9)));
		assertThat(new DigitizedDecimal(-11).add(new DigitizedDecimal(20)), is(new DigitizedDecimal(9)));
		assertThat(new DigitizedDecimal(-9999).add(new DigitizedDecimal(999)), is(new DigitizedDecimal(-9000)));
		assertThat(new DigitizedDecimal(999).add(new DigitizedDecimal(-9999)), is(new DigitizedDecimal(-9000)));

		assertThat(new DigitizedDecimal(-1).add(new DigitizedDecimal(-1)), is(new DigitizedDecimal(-2)));
		assertThat(new DigitizedDecimal(-1).add(new DigitizedDecimal(-8)), is(new DigitizedDecimal(-9)));
		assertThat(new DigitizedDecimal(-8).add(new DigitizedDecimal(-1)), is(new DigitizedDecimal(-9)));
		assertThat(new DigitizedDecimal(-1).add(new DigitizedDecimal(-9)), is(new DigitizedDecimal(-10)));
		assertThat(new DigitizedDecimal(-9).add(new DigitizedDecimal(-1)), is(new DigitizedDecimal(-10)));
		assertThat(new DigitizedDecimal(-9999).add(new DigitizedDecimal(-999)), is(new DigitizedDecimal(-10998)));
		assertThat(new DigitizedDecimal(-999).add(new DigitizedDecimal(-9999)), is(new DigitizedDecimal(-10998)));
	}

	@Test
	public void increment_works() {
		assertThat(new DigitizedDecimal(0).increment(), is(new DigitizedDecimal(1)));
		assertThat(new DigitizedDecimal(1).increment(), is(new DigitizedDecimal(2)));
		assertThat(new DigitizedDecimal(9).increment(), is(new DigitizedDecimal(10)));
		assertThat(new DigitizedDecimal(19).increment(), is(new DigitizedDecimal(20)));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void increment_of_negative_currently_unsupported() {
		new DigitizedDecimal(-1).increment();
	}

	@Test
	public void digitsAscending_works() {
		assertThat(new DigitizedDecimal(0).digitsAscending(), is(true));
		assertThat(new DigitizedDecimal(9).digitsAscending(), is(true));
		assertThat(new DigitizedDecimal(11).digitsAscending(), is(true));
		assertThat(new DigitizedDecimal(12).digitsAscending(), is(true));
		assertThat(new DigitizedDecimal(19).digitsAscending(), is(true));
		assertThat(new DigitizedDecimal(122).digitsAscending(), is(true));
		assertThat(new DigitizedDecimal(123).digitsAscending(), is(true));
		assertThat(new DigitizedDecimal(189).digitsAscending(), is(true));

		assertThat(new DigitizedDecimal(10).digitsAscending(), is(false));
		assertThat(new DigitizedDecimal(21).digitsAscending(), is(false));
		assertThat(new DigitizedDecimal(98).digitsAscending(), is(false));
		assertThat(new DigitizedDecimal(132).digitsAscending(), is(false));
		assertThat(new DigitizedDecimal(198).digitsAscending(), is(false));
		assertThat(new DigitizedDecimal(1232).digitsAscending(), is(false));
	}

	@Test
	public void digitsStrictlyAscending_works() {
		assertThat(new DigitizedDecimal(0).digitsStrictlyAscending(), is(true));
		assertThat(new DigitizedDecimal(9).digitsStrictlyAscending(), is(true));
		assertThat(new DigitizedDecimal(11).digitsStrictlyAscending(), is(false));
		assertThat(new DigitizedDecimal(12).digitsStrictlyAscending(), is(true));
		assertThat(new DigitizedDecimal(19).digitsStrictlyAscending(), is(true));
		assertThat(new DigitizedDecimal(122).digitsStrictlyAscending(), is(false));
		assertThat(new DigitizedDecimal(123).digitsStrictlyAscending(), is(true));
		assertThat(new DigitizedDecimal(189).digitsStrictlyAscending(), is(true));

		assertThat(new DigitizedDecimal(10).digitsStrictlyAscending(), is(false));
		assertThat(new DigitizedDecimal(21).digitsStrictlyAscending(), is(false));
		assertThat(new DigitizedDecimal(98).digitsStrictlyAscending(), is(false));
		assertThat(new DigitizedDecimal(132).digitsStrictlyAscending(), is(false));
		assertThat(new DigitizedDecimal(198).digitsStrictlyAscending(), is(false));
		assertThat(new DigitizedDecimal(1232).digitsStrictlyAscending(), is(false));
	}

	@Test
	public void digitsDescending_works() {
		assertThat(new DigitizedDecimal(0).digitsDescending(), is(true));
		assertThat(new DigitizedDecimal(9).digitsDescending(), is(true));
		assertThat(new DigitizedDecimal(10).digitsDescending(), is(true));
		assertThat(new DigitizedDecimal(11).digitsDescending(), is(true));
		assertThat(new DigitizedDecimal(21).digitsDescending(), is(true));
		assertThat(new DigitizedDecimal(91).digitsDescending(), is(true));
		assertThat(new DigitizedDecimal(211).digitsDescending(), is(true));
		assertThat(new DigitizedDecimal(221).digitsDescending(), is(true));
		assertThat(new DigitizedDecimal(981).digitsDescending(), is(true));

		assertThat(new DigitizedDecimal(12).digitsDescending(), is(false));
		assertThat(new DigitizedDecimal(19).digitsDescending(), is(false));
		assertThat(new DigitizedDecimal(89).digitsDescending(), is(false));
		assertThat(new DigitizedDecimal(212).digitsDescending(), is(false));
		assertThat(new DigitizedDecimal(989).digitsDescending(), is(false));
	}

	@Test
	public void digitsStrictlyDescending_works() {
		assertThat(new DigitizedDecimal(0).digitsStrictlyDescending(), is(true));
		assertThat(new DigitizedDecimal(9).digitsStrictlyDescending(), is(true));
		assertThat(new DigitizedDecimal(10).digitsStrictlyDescending(), is(true));
		assertThat(new DigitizedDecimal(11).digitsStrictlyDescending(), is(false));
		assertThat(new DigitizedDecimal(21).digitsStrictlyDescending(), is(true));
		assertThat(new DigitizedDecimal(91).digitsStrictlyDescending(), is(true));
		assertThat(new DigitizedDecimal(211).digitsStrictlyDescending(), is(false));
		assertThat(new DigitizedDecimal(221).digitsStrictlyDescending(), is(false));
		assertThat(new DigitizedDecimal(981).digitsStrictlyDescending(), is(true));

		assertThat(new DigitizedDecimal(12).digitsStrictlyDescending(), is(false));
		assertThat(new DigitizedDecimal(19).digitsStrictlyDescending(), is(false));
		assertThat(new DigitizedDecimal(89).digitsStrictlyDescending(), is(false));
		assertThat(new DigitizedDecimal(212).digitsStrictlyDescending(), is(false));
		assertThat(new DigitizedDecimal(989).digitsStrictlyDescending(), is(false));
	}

	@Test
	public void reverseDigits_works() {
		assertThat(new DigitizedDecimal(0).reverseDigits(), is(new DigitizedDecimal(0)));
		assertThat(new DigitizedDecimal(1).reverseDigits(), is(new DigitizedDecimal(1)));
		assertThat(new DigitizedDecimal(-1).reverseDigits(), is(new DigitizedDecimal(-1)));

		assertThat(new DigitizedDecimal(10).reverseDigits(), is(new DigitizedDecimal(1)));
		assertThat(new DigitizedDecimal(20).reverseDigits(), is(new DigitizedDecimal(2)));
		assertThat(new DigitizedDecimal(100).reverseDigits(), is(new DigitizedDecimal(1)));

		assertThat(new DigitizedDecimal(63).reverseDigits(), is(new DigitizedDecimal(36)));
		assertThat(new DigitizedDecimal(36).reverseDigits(), is(new DigitizedDecimal(63)));

		assertThat(new DigitizedDecimal(123).reverseDigits(), is(new DigitizedDecimal(321)));
		assertThat(new DigitizedDecimal(1230).reverseDigits(), is(new DigitizedDecimal(321)));
		assertThat(new DigitizedDecimal(1230000).reverseDigits(), is(new DigitizedDecimal(321)));
		assertThat(new DigitizedDecimal(-1230000).reverseDigits(), is(new DigitizedDecimal(-321)));
	}

	@Test
	public void compareTo_works() {
		assertThat(new DigitizedDecimal(0).compareTo(new DigitizedDecimal(0)), is(0));
		assertThat(new DigitizedDecimal(1).compareTo(new DigitizedDecimal(1)), is(0));
		assertThat(new DigitizedDecimal(-1).compareTo(new DigitizedDecimal(-1)), is(0));

		assertThat(new DigitizedDecimal(0).compareTo(new DigitizedDecimal(-1)), is(1));
		assertThat(new DigitizedDecimal(-1).compareTo(new DigitizedDecimal(0)), is(-1));
		assertThat(new DigitizedDecimal(-1).compareTo(new DigitizedDecimal(1)), is(-1));
		assertThat(new DigitizedDecimal(1).compareTo(new DigitizedDecimal(-1)), is(1));

		assertThat(new DigitizedDecimal(10).compareTo(new DigitizedDecimal(1)), is(1));
		assertThat(new DigitizedDecimal(1).compareTo(new DigitizedDecimal(10)), is(-1));
		assertThat(new DigitizedDecimal(-10).compareTo(new DigitizedDecimal(-1)), is(-1));
		assertThat(new DigitizedDecimal(-1).compareTo(new DigitizedDecimal(-10)), is(1));

		assertThat(new DigitizedDecimal(1).compareTo(new DigitizedDecimal(0)), is(1));
		assertThat(new DigitizedDecimal(0).compareTo(new DigitizedDecimal(1)), is(-1));
		assertThat(new DigitizedDecimal(-1).compareTo(new DigitizedDecimal(-2)), is(1));
		assertThat(new DigitizedDecimal(-2).compareTo(new DigitizedDecimal(-1)), is(-1));

		assertThat(new DigitizedDecimal(1000).compareTo(new DigitizedDecimal(1000)), is(0));
		assertThat(new DigitizedDecimal(1000).compareTo(new DigitizedDecimal(1001)), is(-1));
		assertThat(new DigitizedDecimal(1001).compareTo(new DigitizedDecimal(1000)), is(1));

		assertThat(new DigitizedDecimal(-1000).compareTo(new DigitizedDecimal(-1000)), is(0));
		assertThat(new DigitizedDecimal(-1000).compareTo(new DigitizedDecimal(-1001)), is(1));
		assertThat(new DigitizedDecimal(-1001).compareTo(new DigitizedDecimal(-1000)), is(-1));

		// Force offset into play
		assertThat(new DigitizedDecimal(1).add(new DigitizedDecimal(0)).compareTo(new DigitizedDecimal(1)), is(0));
	}

	@Test
	public void longValue_works() {
		assertThat(new DigitizedDecimal(0).longValue(), is(0L));
		assertThat(new DigitizedDecimal(1).longValue(), is(1L));
		assertThat(new DigitizedDecimal(-1).longValue(), is(-1L));

		assertThat(new DigitizedDecimal(Long.MAX_VALUE).longValue(), is(Long.MAX_VALUE));
		assertThat(new DigitizedDecimal(Long.MIN_VALUE + 1).longValue(), is(Long.MIN_VALUE + 1));
	}

	@Test
	public void intValue_works() {
		assertThat(new DigitizedDecimal(0).intValue(), is(0));
		assertThat(new DigitizedDecimal(1).intValue(), is(1));
		assertThat(new DigitizedDecimal(-1).intValue(), is(-1));

		assertThat(new DigitizedDecimal(Integer.MAX_VALUE).intValue(), is(Integer.MAX_VALUE));
		assertThat(new DigitizedDecimal(Integer.MIN_VALUE).intValue(), is(Integer.MIN_VALUE));
		assertThat(new DigitizedDecimal(Long.MAX_VALUE).intValue(), is((int) Long.MAX_VALUE));
	}

	@Test
	public void floatValue_works() {
		assertThat(new DigitizedDecimal(0).floatValue(), is(0f));
		assertThat(new DigitizedDecimal(1).floatValue(), is(1f));
		assertThat(new DigitizedDecimal(-1).floatValue(), is(-1f));

		assertThat(new DigitizedDecimal(Long.MAX_VALUE).floatValue(), is((float) Long.MAX_VALUE));
	}

	@Test
	public void doubleValue_works() {
		assertThat(new DigitizedDecimal(0).doubleValue(), is(0d));
		assertThat(new DigitizedDecimal(1).doubleValue(), is(1d));
		assertThat(new DigitizedDecimal(-1).doubleValue(), is(-1d));

		assertThat(new DigitizedDecimal(Long.MAX_VALUE).doubleValue(), is((double) Long.MAX_VALUE));
	}
}
