package com.hnuttin.aoc2020.day17;

import static com.hnuttin.aoc2020.day17.ConwayCubes3D.fromRawInput;
import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.IOUtils.readLines;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ConwayCubes3DTest {

	@Test
	void testPart1() throws IOException {
		ConwayCubes3D conwayCubes = fromRawInput(readLines(getClass().getResourceAsStream("test.input"), defaultCharset()));
		conwayCubes.enablePrint();
		assertThat(conwayCubes.cycle(6)).isEqualTo(112);
	}

}