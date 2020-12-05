package com.hnuttin.aoc2020.day5;

import java.util.Arrays;
import java.util.List;

public class SeatCalculator {

	public static int highestSeatId(List<Seat> seats) {
		return seats.stream()
				.mapToInt(Seat::seatId)
				.max()
				.orElseThrow();
	}

	public static int missingSeatId(List<Seat> seats) {
		int[] seatIds = seats.stream()
				.mapToInt(Seat::seatId)
				.sorted()
				.toArray();
		return searchMissingSeatId(seatIds);
	}

	private static int searchMissingSeatId(int[] seats) {
		if ((seats[1] - seats[0]) == 1) {
			return searchMissingSeatId(Arrays.copyOfRange(seats, 1, seats.length));
		} else {
			return seats[0] + 1;
		}
	}

}
