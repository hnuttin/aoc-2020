package com.hnuttin.aoc2020.day11;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day11 {

	public static void main(String... args) {
		AocApp.<SeatLayout>forDay(11)
				.withInputParser(SeatLayout::fromRawInput)
				.withPart1(SeatLayout::runUntilStable)
				.run();
	}
}
