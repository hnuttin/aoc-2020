package com.hnuttin.aoc2020.day5;

import java.util.Arrays;

public record Seat(String position) {

	private static final int SEAT_ROWS = 128;
	private static final int SEATS_PER_ROW = 8;

	public int seatId() {
		int seatRow = searchPosition(0, SEAT_ROWS - 1, position.chars().limit(7).toArray());
		int seat = searchPosition(0, SEATS_PER_ROW - 1, position.chars().skip(7).toArray());
		return seatRow * 8 + seat;
	}

	private int searchPosition(int min, int max, int[] navigatorChars) {
		if (navigatorChars.length == 0) {
			return min;
		} else {
			double i = (max - min) / 2D + min;
			if (navigatorChars[0] == 'F' || navigatorChars[0] == 'L') {
				return searchPosition(min, (int) Math.floor(i), remaningChars(navigatorChars));
			} else {
				return searchPosition((int) Math.ceil(i), max, remaningChars(navigatorChars));
			}
		}
	}

	private int[] remaningChars(int[] navigatorChars) {
		return Arrays.copyOfRange(navigatorChars, 1, navigatorChars.length);
	}
}
