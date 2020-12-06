package com.hnuttin.aoc2020.day6;

import static com.hnuttin.aoc2020.common.input.InputParsers.aMultiLineParser;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day6 {

	public static void main(String... args) {
		AocApp.<List<GroupAnswers>>forDay(6)
				.withInputParser(aMultiLineParser(GroupAnswers::new)::parse)
				.withPart1(AnswerCalculator::countAllAnyYesAnswers)
				.withPart2(AnswerCalculator::countAllEveryoneYesAnswers)
				.run();
	}
}
