package com.hnuttin.aoc2020.day2;

import static com.hnuttin.aoc2020.common.input.InputParsers.aLineParser;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day2 {

	public static void main(String... args) {
		AocApp.<List<PasswordPolicyDataWithPassword>>forDay(2)
				.withInputParser(aLineParser(PasswordPolicyDataWithPassword::fromString)::parse)
				.withPart1(input -> ValidPasswordCounter.coundValidPasswords(input, new OldPasswordPolicy()))
				.withPart2(input -> ValidPasswordCounter.coundValidPasswords(input, new NewPasswordPolicy()))
				.run();
	}
}
