package com.hnuttin.aoc2020.day4;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;

public class Day4 {

	public static void main(String... args) {
		AocApp.<List<PassportData>>forDay(4)
				.withInputParser(PassportDataReader::fromRawInput)
				.withPart1(PassportValidator::countValidPassportStructures)
				.withPart2(PassportValidator::countValidPassport)
				.run();
	}
}
