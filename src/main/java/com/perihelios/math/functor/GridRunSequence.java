package com.perihelios.math.functor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.perihelios.math.functor.GridRunSequence.Direction.DIAGONAL_NEGATIVE;
import static com.perihelios.math.functor.GridRunSequence.Direction.DIAGONAL_POSITIVE;
import static com.perihelios.math.functor.GridRunSequence.Direction.HORIZONTAL;
import static com.perihelios.math.functor.GridRunSequence.Direction.VERTICAL;
import static java.util.Arrays.asList;

public class GridRunSequence {
	public static Stream<List<BigInteger>> gridRuns(BigInteger[][] grid, int runLength, Direction... directions) {
		Set<Direction> dirs = directions.length > 0 ? EnumSet.copyOf(asList(directions)) : EnumSet.allOf(Direction.class);

		validateArguments(grid, runLength, dirs);

		int height = grid.length;
		int width = grid[0].length;

		return StreamSupport.stream(new SplitlessSpliterator<List<BigInteger>>() {
			private final Direction[] directions = dirs.toArray(new Direction[dirs.size()]);

			private int row;
			private int column;
			private int directionIndex;

			@Override
			public boolean tryAdvance(Consumer<? super List<BigInteger>> action) {
				List<BigInteger> run = new ArrayList<>(runLength);

				while (run.isEmpty()) {
					if (directionIndex == directions.length) {
						directionIndex = 0;
						column++;
					}

					if (column == width) {
						column = 0;
						row++;
					}

					if (row == height) {
						return false;
					}

					switch (directions[directionIndex]) {
						case HORIZONTAL:
							if (column + runLength > width) {
								break;
							}

							for (int c = column; c < column + runLength; c++) {
								run.add(grid[row][c]);
							}

							break;
						case VERTICAL:
							if (row + runLength > height) {
								break;
							}

							for (int r = row; r < row + runLength; r++) {
								run.add(grid[r][column]);
							}

							break;
						case DIAGONAL_POSITIVE:
							if (column + runLength > width || row + runLength > height) {
								break;
							}

							for (int r = row + runLength - 1, c = column; c < column + runLength; r--, c++) {
								run.add(grid[r][c]);
							}

							break;
						case DIAGONAL_NEGATIVE:
							if (column + runLength > width || row + runLength > height) {
								break;
							}

							for (int r = row, c = column; c < column + runLength; r++, c++) {
								run.add(grid[r][c]);
							}

							break;
					}

					directionIndex++;
				}

				action.accept(run);
				return true;
			}
		}, false);
	}

	private static void validateArguments(BigInteger[][] grid, int runLength, Set<Direction> dirs) {
		if (runLength < 1) {
			throw new IllegalArgumentException("Run length argument must be greater than 0; got " + runLength);
		}

		int height = grid.length;
		int width = grid[0].length;

		Set<Direction> problemDirections = EnumSet.noneOf(Direction.class);

		if (dirs.contains(HORIZONTAL) && runLength > width) {
			problemDirections.add(HORIZONTAL);
		}

		if (dirs.contains(VERTICAL) && runLength > height) {
			problemDirections.add(VERTICAL);
		}

		if (dirs.contains(DIAGONAL_NEGATIVE) && (runLength > width || runLength > height)) {
			problemDirections.add(DIAGONAL_NEGATIVE);
		}

		if (dirs.contains(DIAGONAL_POSITIVE) && (runLength > width || runLength > height)) {
			problemDirections.add(DIAGONAL_POSITIVE);
		}

		if (!problemDirections.isEmpty()) {
			String problems = problemDirections.stream().map(Enum::name).collect(Collectors.joining(", "));

			throw new IllegalArgumentException("Cannot create runs of length " + runLength + " for " + width + "x" + height +
				" grid for direction(s) " + problems);
		}
	}

	public enum Direction {
		HORIZONTAL, VERTICAL, DIAGONAL_POSITIVE, DIAGONAL_NEGATIVE
	}

	// Prevent instantiation of this class
	private GridRunSequence() {}
}
