package com.hnuttin.aoc2020.day10;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;
import com.hnuttin.aoc2020.common.input.InputParsers;

@AocDay
public class Day10 {

	public static void main(String... args) {
		AocApp.<List<Integer>>forDay(10)
				.withInputParser(InputParsers.aLineParser(Integer::parseInt)::parse)
				.withPart1(AdapterChainCalculator::calculateDifferences)
				.withPart2(AdapterChainCalculator::calculateValidCombinations)
				.run();
	}
}
