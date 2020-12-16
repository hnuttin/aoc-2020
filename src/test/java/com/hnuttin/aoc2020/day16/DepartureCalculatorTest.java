package com.hnuttin.aoc2020.day16;

import static com.hnuttin.aoc2020.common.input.InputParsers.aMultiLineParser;
import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class DepartureCalculatorTest {

	@Test
	void testPart2() throws IOException {
		List<List<String>> rawInput = aMultiLineParser(Function.identity()).parse(readLines(getClass().getResourceAsStream("part2-test.input"), defaultCharset()));
		List<String> fieldNamesInOrder = DepartureCalculator.fieldNamesInOrder(
				FieldRule.rulesFromRawInput(rawInput),
				Ticket.ticketsFromStrings(rawInput));
		assertThat(fieldNamesInOrder).containsExactly("row", "class", "seat");
	}
}
