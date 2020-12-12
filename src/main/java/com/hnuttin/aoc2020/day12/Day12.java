package com.hnuttin.aoc2020.day12;

import java.util.List;

import com.hnuttin.aoc2020.common.Coordinate;
import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day12 {

	public static void main(String... args) {
		AocApp.<List<String>>forDay(12)
				.withPart1(instructions -> new Ferry().navigate(instructions))
				.withPart2(instructions -> new Ferry(new Coordinate(10, 1)).navigate(instructions))
				.run();
	}
}
