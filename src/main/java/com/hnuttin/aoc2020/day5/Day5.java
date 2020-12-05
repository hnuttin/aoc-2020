package com.hnuttin.aoc2020.day5;

import java.util.List;
import java.util.stream.Collectors;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day5 {

	public static void main(String...args) {
		AocApp.<List<Seat>>forDay(5)
				.withInputParser(rawInput -> rawInput.stream().map(Seat::new).collect(Collectors.toList()))
				.withPart1(SeatCalculator::highestSeatId)
				.withPart2(SeatCalculator::missingSeatId)
				.run();
	}
}
