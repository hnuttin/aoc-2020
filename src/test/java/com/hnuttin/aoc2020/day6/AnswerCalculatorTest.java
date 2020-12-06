package com.hnuttin.aoc2020.day6;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hnuttin.aoc2020.common.input.InputParsers;

class AnswerCalculatorTest {

	private List<GroupAnswers> groupAnswers;

	@BeforeEach
	void setup() {
		groupAnswers = InputParsers.aMultiLineParser(GroupAnswers::new).parse(Arrays.asList(
				"abc",
				"",
				"a",
				"b",
				"c",
				"",
				"ab",
				"ac",
				"",
				"a",
				"a",
				"a",
				"a",
				"",
				"b"
		));
	}

	@Test
	void countAllAnyYesAnswers() {
		assertThat(AnswerCalculator.countAllAnyYesAnswers(groupAnswers)).isEqualTo(11);
	}

	@Test
	void countAllEveryoneYesAnswers() {
		assertThat(AnswerCalculator.countAllEveryoneYesAnswers(groupAnswers)).isEqualTo(6);
	}

}