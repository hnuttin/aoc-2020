package com.hnuttin.aoc2020.day1;

import static com.hnuttin.aoc2020.AocApp.anAocApp;

public class Day1 {

	public static void main(String... args) {
		anAocApp()
				.withPart1(() -> ExpenseReport.fromInput(Input1.INPUT1).fix(2))
				.withPart2(() -> ExpenseReport.fromInput(Input1.INPUT1).fix(3))
				.run();
	}
}
