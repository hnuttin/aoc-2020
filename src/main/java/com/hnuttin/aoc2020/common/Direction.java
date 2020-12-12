package com.hnuttin.aoc2020.common;

import static java.util.Arrays.stream;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Direction {

	EAST(new Degrees(90)), SOUTH(new Degrees(180)), WEST(new Degrees(270)), NORTH(new Degrees(0));

	private final Degrees degrees;

	public Direction turnLeft(Degrees value) {
		Degrees degrees = this.degrees.substract(value.value());
		return byDegrees(degrees);
	}

	public Direction turnRight(Degrees value) {
		Degrees degrees = this.degrees.add(value.value());
		return byDegrees(degrees);
	}

	static Direction byDegrees(Degrees degrees) {
		return stream(values())
				.filter(direction -> direction.degrees.equals(degrees))
				.findFirst()
				.orElseThrow();
	}

}
