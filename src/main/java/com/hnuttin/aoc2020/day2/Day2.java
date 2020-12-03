package com.hnuttin.aoc2020.day2;

import static java.util.stream.Collectors.toList;

import java.util.List;

import com.hnuttin.aoc2020.AocApp;

public class Day2 {

	public static void main(String... args) {
		AocApp.<List<PasswordPolicyDataWithPassword>>forDay(2)
				.withInputParser(rawInput -> rawInput.stream().map(PasswordPolicyDataWithPassword::fromString).collect(toList()))
				.withPart1(input -> ValidPasswordCounter.coundValidPasswords(input, new OldPasswordPolicy()))
				.withPart2(input -> ValidPasswordCounter.coundValidPasswords(input, new NewPasswordPolicy()))
				.run();
	}
}
