package com.hnuttin.aoc2020.day13;

import static com.hnuttin.aoc2020.day13.DepartureCalculator.calculate;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day13 {

	public static void main(String... args) {
		AocApp.<List<String>>forDay(13)
				.withPart1(rawInput -> calculate(rawInput.get(0), rawInput.get(1)))
				.withPart2(rawInput -> calculate(rawInput.get(1)))
				.run();
	}
}
