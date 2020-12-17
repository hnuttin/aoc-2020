package com.hnuttin.aoc2020.day17;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day17 {

	public static void main(String... args) {
		AocApp.<List<String>>forDay(17)
				.withPart1(rawInput -> ConwayCubes3D.fromRawInput(rawInput).cycle(6))
				.withPart2(rawInput -> ConwayCubes4D.fromRawInput(rawInput).cycle(6))
				.run();
	}

}
