package com.hnuttin.aoc2020.day9;

import static com.hnuttin.aoc2020.common.input.InputParsers.aLineParser;
import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class XMASCypherDebuggerTest {

	private List<Long> input;

	@BeforeEach
	void setup() throws IOException {
		input = aLineParser(Long::parseLong).parse(readLines(getClass().getResourceAsStream("test.input"), defaultCharset()));
	}

	@Test
	void testPart1() {
		assertThat(XMASCypherDebugger.findInvalidNumber(input, 5)).isEqualTo(127L);
	}

	@Test
	void testPart2() {
		assertThat(XMASCypherDebugger.findEncryptionWeakness(input, 5)).isEqualTo(62L);
	}

}