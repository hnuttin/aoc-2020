package com.hnuttin.aoc2020.day14;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class MemoryTest {

	@Test
	void testPart1() {
		long result = new Memory().initialize(Arrays.asList(
				"mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
				"mem[8] = 11",
				"mem[7] = 101",
				"mem[8] = 0"
		), false);
		assertThat(result).isEqualTo(165L);
	}

	@Test
	void testPart2() {
		long result = new Memory().initialize(Arrays.asList(
				"mask = 000000000000000000000000000000X1001X",
				"mem[42] = 100",
				"mask = 00000000000000000000000000000000X0XX",
				"mem[26] = 1"
		), true);
		assertThat(result).isEqualTo(208L);
	}

}