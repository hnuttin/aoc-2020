package com.hnuttin.aoc2020.day1;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day1 {

	public static void main(String... args) {
		AocApp.<List<Integer>>forDay(1)
				.withInputParser(rawInput -> rawInput.stream().map(Integer::parseInt).collect(toList()))
				.withPart1(input -> ExpenseReport.fromInput(input).fix(2))
				.withPart2(input -> ExpenseReport.fromInput(input).fix(3))
				.run();
	}
}
