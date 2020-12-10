package com.hnuttin.aoc2020.day10;

import static com.hnuttin.aoc2020.common.input.InputParsers.aLineParser;
import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

class AdapterChainCalculatorTest {

	@Test
	void part1Input1() throws IOException {
		List<Integer> input = aLineParser(Integer::parseInt).parse(readLines(getClass().getResourceAsStream("test1.input"), defaultCharset()));

		assertThat(AdapterChainCalculator.calculateDifferences(input)).isEqualTo(35);
	}

	@Test
	void part1Input2() throws IOException {
		List<Integer> input = aLineParser(Integer::parseInt).parse(readLines(getClass().getResourceAsStream("test2.input"), defaultCharset()));

		assertThat(AdapterChainCalculator.calculateDifferences(input)).isEqualTo(220);
	}

	@Test
	void part2Input1() throws IOException {
		List<Integer> input = aLineParser(Integer::parseInt).parse(readLines(getClass().getResourceAsStream("test1.input"), defaultCharset()));

		assertThat(AdapterChainCalculator.calculateValidCombinations(input)).isEqualTo(8L);
	}

	@Test
	void part2Input2() throws IOException {
		List<Integer> input = aLineParser(Integer::parseInt).parse(readLines(getClass().getResourceAsStream("test2.input"), defaultCharset()));

		assertThat(AdapterChainCalculator.calculateValidCombinations(input)).isEqualTo(19208L);
	}

}