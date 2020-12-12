package com.hnuttin.aoc2020.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Sign {

	NEGATIVE(-1), POSITIVE(1);

	private final int multiplier;

	public static Sign forValue(int value) {
		return value >= 0 ? POSITIVE : NEGATIVE;
	}

	public int sign(int value) {
		return Math.abs(value) * multiplier;
	}
}
