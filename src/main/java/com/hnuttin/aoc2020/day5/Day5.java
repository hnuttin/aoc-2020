package com.hnuttin.aoc2020.day5;

import static com.hnuttin.aoc2020.common.input.InputParsers.aLineParser;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day5 {

	public static void main(String... args) {
		AocApp.<List<Seat>>forDay(5)
				.withInputParser(aLineParser(Seat::new)::parse)
				.withPart1(SeatCalculator::highestSeatId)
				.withPart2(SeatCalculator::missingSeatId)
				.run();
	}
}
