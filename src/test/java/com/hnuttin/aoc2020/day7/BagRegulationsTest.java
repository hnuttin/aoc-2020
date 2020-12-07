package com.hnuttin.aoc2020.day7;

import static com.hnuttin.aoc2020.common.input.InputParsers.aLineParser;
import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BagRegulationsTest {

	private BagRegulations bagRegulations;

	@BeforeEach
	void setup() throws IOException {
		List<BagRule> bagRules = aLineParser(BagRule::fromRawInput).parse(readLines(getClass().getResourceAsStream("test.input"), defaultCharset()));
		bagRegulations = new BagRegulations(bagRules);
	}

	@Test
	void testPart1() {
		assertThat(bagRegulations.countBagOptions("shiny gold")).isEqualTo(4L);
	}

	@Test
	void testPart2() {
		assertThat(bagRegulations.countChildBags("shiny gold")).isEqualTo(32L);
	}

	@Test
	void testPart2_AnotherExample() throws IOException {
		List<BagRule> bagRules = aLineParser(BagRule::fromRawInput).parse(readLines(getClass().getResourceAsStream("another_example.input"), defaultCharset()));
		BagRegulations anotherExampleRegulations = new BagRegulations(bagRules);

		assertThat(anotherExampleRegulations.countChildBags("shiny gold")).isEqualTo(126L);
	}

}