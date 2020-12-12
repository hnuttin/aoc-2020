package com.hnuttin.aoc2020.common;

import java.util.function.Function;
import java.util.stream.Stream;

import lombok.Value;

@Value
public class Coordinate {

	int x;
	int y;

	public Quadrant getQuadrant() {
		return Stream.of(Quadrant.values())
				.filter(quadrant -> quadrant.matchesCoordinate(this))
				.findFirst()
				.orElseThrow();
	}

	public Coordinate add(Slope slope) {
		return new Coordinate(x + slope.getRight(), y + slope.getDown());
	}

	public Coordinate move(Direction direction, int value) {
		return switch (direction) {
			case NORTH -> new Coordinate(x, y + value);
			case SOUTH -> new Coordinate(x, y - value);
			case EAST -> new Coordinate(x + value, y);
			case WEST -> new Coordinate(x - value, y);
		};
	}

	public Coordinate signAccordingTo(Quadrant quadrant) {
		return new Coordinate(quadrant.getXSign().sign(x), quadrant.getYSign().sign(y));
	}

	public Coordinate rotateLeftAroundCenter(Degrees degrees) {
		return rotateAroundCenter(degrees, Quadrant::turnLeft);
	}

	public Coordinate rotateRightAroundCenter(Degrees degrees) {
		return rotateAroundCenter(degrees, Quadrant::turnRight);
	}

	private Coordinate rotateAroundCenter(Degrees degrees, Function<Quadrant, Quadrant> quadrantRotation) {
		Coordinate rotated = this;
		for (int r = 0; r < degrees.numberOfQuadrants(); r++) {
			Quadrant rotatedQuadrant = quadrantRotation.apply(rotated.getQuadrant());
			rotated = new Coordinate(rotated.getY(), rotated.getX()).signAccordingTo(rotatedQuadrant);
		}
		return rotated;
	}
}
