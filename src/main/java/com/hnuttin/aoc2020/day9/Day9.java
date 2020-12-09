package com.hnuttin.aoc2020.day9;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;
import com.hnuttin.aoc2020.common.input.InputParsers;

@AocDay
public class Day9 {

	public static void main(String... args) {
		AocApp.<List<Long>>forDay(9)
				.withInputParser(InputParsers.aLineParser(Long::parseLong)::parse)
				.withPart1(input -> XMASCypherDebugger.findInvalidNumber(input, 25))
				.withPart2(input -> XMASCypherDebugger.findEncryptionWeakness(input, 25))
				.run();
	}
}
