package com.hnuttin.aoc2020.day12;

import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.hnuttin.aoc2020.common.Coordinate;

class FerryTest {

	@Test
	void testPart1() throws IOException {
		int manhattenDistance = new Ferry().navigate(readLines(getClass().getResourceAsStream("test.input"), defaultCharset()));
		assertThat(manhattenDistance).isEqualTo(25);
	}

	@Test
	void testPart2() throws IOException {
		int manhattenDistance = new Ferry(new Coordinate(10, 1)).navigate(readLines(getClass().getResourceAsStream("test.input"), defaultCharset()));
		assertThat(manhattenDistance).isEqualTo(286);
	}

}