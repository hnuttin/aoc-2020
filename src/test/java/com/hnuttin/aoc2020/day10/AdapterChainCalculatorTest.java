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
	void test1Input() throws IOException {
		List<Integer> input = aLineParser(Integer::parseInt).parse(readLines(getClass().getResourceAsStream("test1.input"), defaultCharset()));

		assertThat(AdapterChainCalculator.calculate(input)).isEqualTo(35);
	}

	@Test
	void test2Input() throws IOException {
		List<Integer> input = aLineParser(Integer::parseInt).parse(readLines(getClass().getResourceAsStream("test2.input"), defaultCharset()));

		assertThat(AdapterChainCalculator.calculate(input)).isEqualTo(220);
	}

}