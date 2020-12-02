package com.hnuttin.aoc2020.day2;

import com.hnuttin.aoc2020.AocApp;

public class Day2 {

	public static void main(String... args) {
		AocApp.forDay(2)
				.withPart1(() -> ValidPasswordCounter.coundValidPasswords(Input1.input1(), new OldPasswordPolicy()))
				.withPart2(() -> ValidPasswordCounter.coundValidPasswords(Input1.input1(), new NewPasswordPolicy()))
				.run();
	}
}
