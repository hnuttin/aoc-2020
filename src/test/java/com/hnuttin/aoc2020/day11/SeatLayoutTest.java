package com.hnuttin.aoc2020.day11;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SeatLayoutTest {

	private SeatLayout seatLayout;

	@BeforeEach
	void setup() throws IOException {
		seatLayout = SeatLayout.fromRawInput(IOUtils.readLines(getClass().getResourceAsStream("test.input"), Charset.defaultCharset()));
//		seatLayout.enablePrint();
	}

	@Test
	void testPart1() {
		assertThat(seatLayout.runUntilStable()).isEqualTo(37L);
	}

}