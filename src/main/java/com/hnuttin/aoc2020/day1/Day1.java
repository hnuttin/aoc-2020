package com.hnuttin.aoc2020.day1;

import com.hnuttin.aoc2020.AocApp;

public class Day1 {

	public static void main(String... args) {
		AocApp.forDay(1)
				.withPart1(() -> ExpenseReport.fromInput(Input1.INPUT1).fix(2))
				.withPart2(() -> ExpenseReport.fromInput(Input1.INPUT1).fix(3))
				.run();
	}
}
