package com.perihelios.math.functor;

import org.junit.Test;

import static com.perihelios.math.functor.NumberUtil.bigInt;
import static com.perihelios.math.functor.NumberUtil.bigInts;
import static com.perihelios.math.functor.PolygonalSequences.polygonalSequence;
import static com.perihelios.math.functor.PolygonalSequences.polygonalSequenceFromIndex;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PolygonalSequencesTest {
	@Test
	public void polygonalSequence_BigInteger_works() {
		// Triangular numbers
		assertThat(
			polygonalSequence(bigInt(3)).limit(20).collect(toList()),
			is(asList(bigInts(1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78, 91, 105, 120, 136, 153, 171, 190, 210)))
		);

		// Pentagonal numbers
		assertThat(
			polygonalSequence(bigInt(5)).limit(20).collect(toList()),
			is(asList(bigInts(1, 5, 12, 22, 35, 51, 70, 92, 117, 145, 176, 210, 247, 287, 330, 376, 425, 477, 532, 590)))
		);

		// Hexagonal numbers
		assertThat(
			polygonalSequence(bigInt(6)).limit(20).collect(toList()),
			is(asList(bigInts(1, 6, 15, 28, 45, 66, 91, 120, 153, 190, 231, 276, 325, 378, 435, 496, 561, 630, 703, 780)))
		);
	}
	
	@Test
	public void polygonalSequenceFromIndex_BigInteger_works() {
		// Triangular numbers
		assertThat(
			polygonalSequenceFromIndex(bigInt(3), bigInt(11)).limit(10).collect(toList()),
			is(asList(bigInts(66, 78, 91, 105, 120, 136, 153, 171, 190, 210)))
		);

		// Pentagonal numbers
		assertThat(
			polygonalSequenceFromIndex(bigInt(5), bigInt(11)).limit(10).collect(toList()),
			is(asList(bigInts(176, 210, 247, 287, 330, 376, 425, 477, 532, 590)))
		);

		// Hexagonal numbers
		assertThat(
			polygonalSequenceFromIndex(bigInt(6), bigInt(11)).limit(10).collect(toList()),
			is(asList(bigInts(231, 276, 325, 378, 435, 496, 561, 630, 703, 780)))
		);
	}

	@Test
	public void polygonalSequence_long_works() {
		// Triangular numbers
		assertThat(
			polygonalSequence(3).limit(20).boxed().collect(toList()),
			is(asList(1L, 3L, 6L, 10L, 15L, 21L, 28L, 36L, 45L, 55L, 66L, 78L, 91L, 105L, 120L, 136L, 153L, 171L, 190L, 210L))
		);

		// Pentagonal numbers
		assertThat(
			polygonalSequence(5).limit(20).boxed().collect(toList()),
			is(asList(1L, 5L, 12L, 22L, 35L, 51L, 70L, 92L, 117L, 145L, 176L, 210L, 247L, 287L, 330L, 376L, 425L, 477L, 532L, 590L))
		);

		// Hexagonal numbers
		assertThat(
			polygonalSequence(6).limit(20).boxed().collect(toList()),
			is(asList(1L, 6L, 15L, 28L, 45L, 66L, 91L, 120L, 153L, 190L, 231L, 276L, 325L, 378L, 435L, 496L, 561L, 630L, 703L, 780L))
		);
	}

	@Test
	public void polygonalSequenceFromIndex_long_works() {
		// Triangular numbers
		assertThat(
			polygonalSequenceFromIndex(3, 11).limit(10).boxed().collect(toList()),
			is(asList(66L, 78L, 91L, 105L, 120L, 136L, 153L, 171L, 190L, 210L))
		);

		// Pentagonal numbers
		assertThat(
			polygonalSequenceFromIndex(5, 11).limit(10).boxed().collect(toList()),
			is(asList(176L, 210L, 247L, 287L, 330L, 376L, 425L, 477L, 532L, 590L))
		);

		// Hexagonal numbers
		assertThat(
			polygonalSequenceFromIndex(6, 11).limit(10).boxed().collect(toList()),
			is(asList(231L, 276L, 325L, 378L, 435L, 496L, 561L, 630L, 703L, 780L))
		);
	}
}
