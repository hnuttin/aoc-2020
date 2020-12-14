package com.hnuttin.aoc2020.day14;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day14 {

	public static void main(String... args) {
		AocApp.<List<String>>forDay(14)
				.withPart1(instructions -> new Memory().initialize(instructions))
				.run();
	}

}
