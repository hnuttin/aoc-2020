package com.hnuttin.aoc2020.day17;

import java.util.stream.IntStream;
import java.util.stream.Stream;

record Coordinate4D(int x, int y, int z, int w) {

	Stream<Coordinate4D> adjacentCoordinates() {
		return oneDifferenceStream()
				.boxed()
				.flatMap(xDiff -> oneDifferenceStream()
						.boxed()
						.flatMap(yDiff -> oneDifferenceStream()
								.boxed()
								.flatMap(zDiff -> oneDifferenceStream()
										.boxed()
										.map(wDiff -> new Coordinate4D(this.x + xDiff, this.y + yDiff, this.z + zDiff, this.w + wDiff)))))
				.filter(coord -> !coord.equals(this));
	}

	private IntStream oneDifferenceStream() {
		return IntStream.of(-1, 0, 1);
	}

}
