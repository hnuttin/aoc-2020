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
		));
		assertThat(result).isEqualTo(165L);
	}

}