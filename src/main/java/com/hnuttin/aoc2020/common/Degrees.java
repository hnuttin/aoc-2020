package com.hnuttin.aoc2020.common;

public record Degrees(int value) {

	private static final int FULL_CIRCLE = 360;
	private static final int QUADRANT = 90;

	public Degrees add(int value) {
		int newDegrees = this.value + value;
		return new Degrees(newDegrees >= FULL_CIRCLE ? newDegrees - FULL_CIRCLE : newDegrees);
	}

	public Degrees substract(int value) {
		int newDegrees = this.value - value;
		return new Degrees(newDegrees < 0 ? newDegrees + FULL_CIRCLE : newDegrees);
	}

	public int numberOfQuadrants() {
		return value / QUADRANT;
	}
}
