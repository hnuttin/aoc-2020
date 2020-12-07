package com.hnuttin.aoc2020.day6;

import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hnuttin.aoc2020.common.input.InputParsers;

class AnswerCalculatorTest {

	private List<GroupAnswers> groupAnswers;

	@BeforeEach
	void setup() throws IOException {
		groupAnswers = InputParsers.aMultiLineParser(GroupAnswers::new).parse(readLines(getClass().getResourceAsStream("test.input"), defaultCharset()));
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