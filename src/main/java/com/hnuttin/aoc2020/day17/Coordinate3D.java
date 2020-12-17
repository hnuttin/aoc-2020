package com.hnuttin.aoc2020.day17;

import java.util.stream.IntStream;
import java.util.stream.Stream;

record Coordinate3D(int x, int y, int z) {

	Stream<Coordinate3D> adjacentCoordinates() {
		return oneDifferenceStream()
				.boxed()
				.flatMap(xDiff -> oneDifferenceStream()
						.boxed()
						.flatMap(yDiff -> oneDifferenceStream()
								.boxed()
								.map(zDiff -> new Coordinate3D(this.x + xDiff, this.y + yDiff, this.z + zDiff))))
				.filter(coord -> !coord.equals(this));
	}

	private IntStream oneDifferenceStream() {
		return IntStream.of(-1, 0, 1);
	}

}
