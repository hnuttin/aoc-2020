package com.hnuttin.aoc2020.day15;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day15 {

	public static void main(String... args) {
		AocApp.<String>forDay(15)
				.withInput("1,17,0,10,18,11,6")
				.withPart1(input -> NumberGame.fromString(input).getNthNumber(2020))
				.withPart1(input -> NumberGame.fromString(input).getNthNumber(30000000))
				.run();
	}
}
