package com.hnuttin.aoc2020.common;

import lombok.Value;

@Value
public class Coordinate {

	int x;
	int y;

	public Coordinate add(Slope slope) {
		return new Coordinate(x + slope.getRight(), y + slope.getDown());
	}
}
