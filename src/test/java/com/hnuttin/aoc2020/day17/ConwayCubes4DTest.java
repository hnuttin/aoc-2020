package com.hnuttin.aoc2020.day17;

import static com.hnuttin.aoc2020.day17.ConwayCubes4D.fromRawInput;
import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ConwayCubes4DTest {

	@Test
	void testPart2() throws IOException {
		ConwayCubes4D conwayCubes = fromRawInput(readLines(getClass().getResourceAsStream("test.input"), defaultCharset()));
		conwayCubes.enablePrint();
		assertThat(conwayCubes.cycle(6)).isEqualTo(848);
	}

}