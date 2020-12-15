package com.hnuttin.aoc2020.day15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class NumberGameTest {

	@Test
	void example1Part1() {
		assertThat(NumberGame.fromString("0,3,6").getNthNumber(2020)).isEqualTo(436);
	}

	@Test
	void example2Part1() {
		assertThat(NumberGame.fromString("1,3,2").getNthNumber(2020)).isEqualTo(1);
	}

	@Test
	void example3Part1() {
		assertThat(NumberGame.fromString("2,1,3").getNthNumber(2020)).isEqualTo(10);
	}

	@Test
	void example4Part1() {
		assertThat(NumberGame.fromString("1,2,3").getNthNumber(2020)).isEqualTo(27);
	}

	@Test
	void example5Part1() {
		assertThat(NumberGame.fromString("2,3,1").getNthNumber(2020)).isEqualTo(78);
	}

	@Test
	void example6Part1() {
		assertThat(NumberGame.fromString("3,2,1").getNthNumber(2020)).isEqualTo(438);
	}

	@Test
	void example7Part1() {
		assertThat(NumberGame.fromString("3,1,2").getNthNumber(2020)).isEqualTo(1836);
	}

	@Test
	void example1Part2() {
		assertThat(NumberGame.fromString("0,3,6").getNthNumber(30000000)).isEqualTo(175594);
	}

	@Test
	void example2Part2() {
		assertThat(NumberGame.fromString("1,3,2").getNthNumber(30000000)).isEqualTo(2578);
	}

	@Test
	void example3Part2() {
		assertThat(NumberGame.fromString("2,1,3").getNthNumber(30000000)).isEqualTo(3544142);
	}

	@Test
	void example4Part2() {
		assertThat(NumberGame.fromString("1,2,3").getNthNumber(30000000)).isEqualTo(261214);
	}

	@Test
	void example5Part2() {
		assertThat(NumberGame.fromString("2,3,1").getNthNumber(30000000)).isEqualTo(6895259);
	}

	@Test
	void example6Part2() {
		assertThat(NumberGame.fromString("3,2,1").getNthNumber(30000000)).isEqualTo(18);
	}

	@Test
	void example7Part2() {
		assertThat(NumberGame.fromString("3,1,2").getNthNumber(30000000)).isEqualTo(362);
	}

}