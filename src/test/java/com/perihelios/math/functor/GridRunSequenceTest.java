package com.perihelios.math.functor;

import org.junit.Test;

import java.math.BigInteger;
import java.util.stream.Collectors;

import static com.perihelios.math.functor.GridRunSequence.Direction.DIAGONAL_NEGATIVE;
import static com.perihelios.math.functor.GridRunSequence.Direction.DIAGONAL_POSITIVE;
import static com.perihelios.math.functor.GridRunSequence.Direction.HORIZONTAL;
import static com.perihelios.math.functor.GridRunSequence.Direction.VERTICAL;
import static com.perihelios.math.functor.GridRunSequence.gridRuns;
import static com.perihelios.math.functor.NumberUtil.bigInt;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class GridRunSequenceTest {
	@Test
	public void gridRuns_vertical() {
		assertThat(gridRuns(grid(1, 1), 1, VERTICAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101))
		)));

		assertThat(gridRuns(grid(2, 2), 1, VERTICAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101)),
			asList(bigInt(102)),
			asList(bigInt(201)),
			asList(bigInt(202))
		)));

		assertThat(gridRuns(grid(2, 2), 2, VERTICAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(201)),
			asList(bigInt(102), bigInt(202))
		)));

		assertThat(gridRuns(grid(2, 3), 2, VERTICAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(201)),
			asList(bigInt(102), bigInt(202)),
			asList(bigInt(103), bigInt(203))
		)));

		assertThat(gridRuns(grid(3, 2), 2, VERTICAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(201)),
			asList(bigInt(102), bigInt(202)),
			asList(bigInt(201), bigInt(301)),
			asList(bigInt(202), bigInt(302))
		)));

		assertThat(gridRuns(grid(3, 3), 2, VERTICAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(201)),
			asList(bigInt(102), bigInt(202)),
			asList(bigInt(103), bigInt(203)),
			asList(bigInt(201), bigInt(301)),
			asList(bigInt(202), bigInt(302)),
			asList(bigInt(203), bigInt(303))
		)));
	}

	@Test
	public void gridRuns_horizontal() {
		assertThat(gridRuns(grid(1, 1), 1, HORIZONTAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101))
		)));

		assertThat(gridRuns(grid(2, 2), 1, HORIZONTAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101)),
			asList(bigInt(102)),
			asList(bigInt(201)),
			asList(bigInt(202))
		)));

		assertThat(gridRuns(grid(2, 2), 2, HORIZONTAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(102)),
			asList(bigInt(201), bigInt(202))
		)));

		assertThat(gridRuns(grid(2, 3), 2, HORIZONTAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(102)),
			asList(bigInt(102), bigInt(103)),
			asList(bigInt(201), bigInt(202)),
			asList(bigInt(202), bigInt(203))
		)));

		assertThat(gridRuns(grid(3, 2), 2, HORIZONTAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(102)),
			asList(bigInt(201), bigInt(202)),
			asList(bigInt(301), bigInt(302))
		)));

		assertThat(gridRuns(grid(3, 3), 2, HORIZONTAL).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(102)),
			asList(bigInt(102), bigInt(103)),
			asList(bigInt(201), bigInt(202)),
			asList(bigInt(202), bigInt(203)),
			asList(bigInt(301), bigInt(302)),
			asList(bigInt(302), bigInt(303))
		)));
	}

	@Test
	public void gridRuns_diagonal_positive() {
		assertThat(gridRuns(grid(1, 1), 1, DIAGONAL_POSITIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(101))
		)));

		assertThat(gridRuns(grid(2, 2), 1, DIAGONAL_POSITIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(101)),
			asList(bigInt(102)),
			asList(bigInt(201)),
			asList(bigInt(202))
		)));

		assertThat(gridRuns(grid(2, 2), 2, DIAGONAL_POSITIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(201), bigInt(102))
		)));

		assertThat(gridRuns(grid(2, 3), 2, DIAGONAL_POSITIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(201), bigInt(102)),
			asList(bigInt(202), bigInt(103))
		)));

		assertThat(gridRuns(grid(3, 2), 2, DIAGONAL_POSITIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(201), bigInt(102)),
			asList(bigInt(301), bigInt(202))
		)));

		assertThat(gridRuns(grid(3, 3), 2, DIAGONAL_POSITIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(201), bigInt(102)),
			asList(bigInt(202), bigInt(103)),
			asList(bigInt(301), bigInt(202)),
			asList(bigInt(302), bigInt(203))
		)));
	}

	@Test
	public void gridRuns_diagonal_negative() {
		assertThat(gridRuns(grid(1, 1), 1, DIAGONAL_NEGATIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(101))
		)));

		assertThat(gridRuns(grid(2, 2), 1, DIAGONAL_NEGATIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(101)),
			asList(bigInt(102)),
			asList(bigInt(201)),
			asList(bigInt(202))
		)));

		assertThat(gridRuns(grid(2, 2), 2, DIAGONAL_NEGATIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(202))
		)));

		assertThat(gridRuns(grid(2, 3), 2, DIAGONAL_NEGATIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(202)),
			asList(bigInt(102), bigInt(203))
		)));

		assertThat(gridRuns(grid(3, 2), 2, DIAGONAL_NEGATIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(202)),
			asList(bigInt(201), bigInt(302))
		)));

		assertThat(gridRuns(grid(3, 3), 2, DIAGONAL_NEGATIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(202)),
			asList(bigInt(102), bigInt(203)),
			asList(bigInt(201), bigInt(302)),
			asList(bigInt(202), bigInt(303))
		)));
	}

	@Test
	public void gridRuns_mixture() {
		assertThat(gridRuns(grid(1, 1), 1, HORIZONTAL, VERTICAL, DIAGONAL_POSITIVE, DIAGONAL_NEGATIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(101)), // origin 0,0: horizontal
			asList(bigInt(101)), // origin 0,0: vertical
			asList(bigInt(101)), // origin 0,0: diagonal positive
			asList(bigInt(101))  // origin 0,0: diagonal negative
		)));

		assertThat(gridRuns(grid(2, 2), 2, HORIZONTAL, VERTICAL, DIAGONAL_POSITIVE, DIAGONAL_NEGATIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(102)), // origin 0,0: horizontal
			asList(bigInt(101), bigInt(201)), // origin 0,0: vertical
			asList(bigInt(201), bigInt(102)), // origin 0,0: diagonal positive
			asList(bigInt(101), bigInt(202)), // origin 0,0: diagonal negative
			asList(bigInt(102), bigInt(202)), // origin 0,1: vertical
			asList(bigInt(201), bigInt(202))  // origin 1,0: horizontal
		)));

		assertThat(gridRuns(grid(3, 3), 2, HORIZONTAL, VERTICAL, DIAGONAL_POSITIVE, DIAGONAL_NEGATIVE).collect(Collectors.toList()), is(asList(
			asList(bigInt(101), bigInt(102)), // origin 0,0: horizontal
			asList(bigInt(101), bigInt(201)), // origin 0,0: vertical
			asList(bigInt(201), bigInt(102)), // origin 0,0: diagonal positive
			asList(bigInt(101), bigInt(202)), // origin 0,0: diagonal negative
			asList(bigInt(102), bigInt(103)), // origin 0,1: horizontal
			asList(bigInt(102), bigInt(202)), // origin 0,1: vertical
			asList(bigInt(202), bigInt(103)), // origin 0,1: diagonal positive
			asList(bigInt(102), bigInt(203)), // origin 0,1: diagonal negative
			asList(bigInt(103), bigInt(203)), // origin 0,2: vertical
			asList(bigInt(201), bigInt(202)), // origin 1,0: horizontal
			asList(bigInt(201), bigInt(301)), // origin 1,0: vertical
			asList(bigInt(301), bigInt(202)), // origin 1,0: diagonal positive
			asList(bigInt(201), bigInt(302)), // origin 1,0: diagonal negative
			asList(bigInt(202), bigInt(203)), // origin 1,1: horizontal
			asList(bigInt(202), bigInt(302)), // origin 1,1: vertical
			asList(bigInt(302), bigInt(203)), // origin 1,1: diagonal positive
			asList(bigInt(202), bigInt(303)), // origin 1,1: diagonal negative
			asList(bigInt(203), bigInt(303)), // origin 1,2: vertical
			asList(bigInt(301), bigInt(302)), // origin 2,0: horizontal
			asList(bigInt(302), bigInt(303))  // origin 2,1: horizontal
		)));
	}

	@Test
	public void gridRuns_minimum_runLength() {
		try {
			gridRuns(new BigInteger[3][2], 0, HORIZONTAL);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Run length argument must be greater than 0; got 0"));
		}
	}

	@Test
	public void gridRuns_requires_runLength_to_fit_for_given_Directions() {
		try {
			gridRuns(new BigInteger[3][2], 3, HORIZONTAL);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Cannot create runs of length 3 for 2x3 grid for direction(s) HORIZONTAL"));
		}

		try {
			gridRuns(new BigInteger[2][3], 3, VERTICAL);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Cannot create runs of length 3 for 3x2 grid for direction(s) VERTICAL"));
		}

		try {
			gridRuns(new BigInteger[2][3], 3, DIAGONAL_NEGATIVE);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Cannot create runs of length 3 for 3x2 grid for direction(s) DIAGONAL_NEGATIVE"));
		}

		try {
			gridRuns(new BigInteger[3][2], 3, DIAGONAL_NEGATIVE);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Cannot create runs of length 3 for 2x3 grid for direction(s) DIAGONAL_NEGATIVE"));
		}

		try {
			gridRuns(new BigInteger[2][3], 3, DIAGONAL_POSITIVE);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Cannot create runs of length 3 for 3x2 grid for direction(s) DIAGONAL_POSITIVE"));
		}

		try {
			gridRuns(new BigInteger[3][2], 3, DIAGONAL_POSITIVE);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(expected.getMessage(), is("Cannot create runs of length 3 for 2x3 grid for direction(s) DIAGONAL_POSITIVE"));
		}

		try {
			gridRuns(new BigInteger[2][2], 3, HORIZONTAL, VERTICAL, DIAGONAL_NEGATIVE, DIAGONAL_POSITIVE);
			fail("Expected exception " + IllegalArgumentException.class.getName());
		} catch (IllegalArgumentException expected) {
			assertThat(
				expected.getMessage(),
				is("Cannot create runs of length 3 for 2x2 grid for direction(s) HORIZONTAL, VERTICAL, DIAGONAL_POSITIVE, DIAGONAL_NEGATIVE")
			);
		}
	}

	private static BigInteger[][] grid(int rows, int columns) {
		BigInteger[][] grid = new BigInteger[rows][columns];

		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				grid[row][column] = BigInteger.valueOf((row + 1) * 100 + column + 1);
			}
		}

		return grid;
	}
}
