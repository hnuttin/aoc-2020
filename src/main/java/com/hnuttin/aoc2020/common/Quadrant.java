package com.hnuttin.aoc2020.common;

import static com.hnuttin.aoc2020.common.Direction.EAST;
import static com.hnuttin.aoc2020.common.Direction.NORTH;
import static com.hnuttin.aoc2020.common.Direction.SOUTH;
import static com.hnuttin.aoc2020.common.Direction.WEST;
import static com.hnuttin.aoc2020.common.Sign.NEGATIVE;
import static com.hnuttin.aoc2020.common.Sign.POSITIVE;
import static com.hnuttin.aoc2020.common.Sign.forValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Quadrant {

	NORTH_EAST(POSITIVE, EAST, POSITIVE, NORTH),
	NORTH_WEST(NEGATIVE, WEST, POSITIVE, NORTH),
	SOUTH_WEST(NEGATIVE, WEST, NEGATIVE, SOUTH),
	SOUTH_EAST(POSITIVE, EAST, NEGATIVE, SOUTH);

	private final Sign xSign;
	private final Direction xDirection;
	private final Sign ySign;
	private final Direction yDirection;

	public boolean matchesCoordinate(Coordinate coordinate) {
		return xSign == forValue(coordinate.getX()) && ySign == forValue(coordinate.getY());
	}

	public Quadrant turnLeft() {
		return switch (this) {
			case NORTH_EAST -> NORTH_WEST;
			case NORTH_WEST -> SOUTH_WEST;
			case SOUTH_WEST -> SOUTH_EAST;
			case SOUTH_EAST -> NORTH_EAST;
		};
	}

	public Quadrant turnRight() {
		return switch (this) {
			case NORTH_EAST -> SOUTH_EAST;
			case SOUTH_EAST -> SOUTH_WEST;
			case SOUTH_WEST -> NORTH_WEST;
			case NORTH_WEST -> NORTH_EAST;
		};
	}
}
