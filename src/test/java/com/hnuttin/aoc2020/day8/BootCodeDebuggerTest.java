package com.hnuttin.aoc2020.day8;

import static com.hnuttin.aoc2020.common.input.InputParsers.aLineParser;
import static com.hnuttin.aoc2020.day8.BootCodeDebugger.debug;
import static com.hnuttin.aoc2020.day8.BootCodeDebugger.fixAndDebug;
import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BootCodeDebuggerTest {

	private List<Instruction> instructions;

	@BeforeEach
	void setup() throws IOException {
		instructions = aLineParser(Instruction::fromRawInput).parse(readLines(getClass().getResourceAsStream("test.input"), defaultCharset()));
	}

	@Test
	void testPart1() {
		assertThat(debug(instructions)).isEqualTo(5);
	}

	@Test
	void testPart2() {
		assertThat(fixAndDebug(instructions)).isEqualTo(8);
	}

}