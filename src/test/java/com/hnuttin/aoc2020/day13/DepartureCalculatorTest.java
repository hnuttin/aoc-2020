package com.hnuttin.aoc2020.day13;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DepartureCalculatorTest {

	@Test
	void example1Part1() {
		long wait = DepartureCalculator.calculate("939", "7,13,x,x,59,x,31,19");
		assertThat(wait).isEqualTo(295L);
	}

	@Test
	void test1Part2() {
		long wait = DepartureCalculator.calculate("2,7");
		assertThat(wait).isEqualTo(6L);
	}

	@Test
	void test2Part2() {
		long wait = DepartureCalculator.calculate("2,3,5");
		assertThat(wait).isEqualTo(8L);
	}

	@Test
	void test3Part2() {
		long wait = DepartureCalculator.calculate("2,3,7");
		assertThat(wait).isEqualTo(26L);
	}

	@Test
	void test4Part2() {
		long wait = DepartureCalculator.calculate("3,x,5");
		assertThat(wait).isEqualTo(3L);
	}

	@Test
	void example1Part2() {
		long wait = DepartureCalculator.calculate("7,13,x,x,59,x,31,19");
		assertThat(wait).isEqualTo(1068781L);
	}

	@Test
	void example2Part2() {
		long wait = DepartureCalculator.calculate("17,x,13,19");
		assertThat(wait).isEqualTo(3417L);
	}

	@Test
	void example3Part2() {
		long wait = DepartureCalculator.calculate("67,7,59,61");
		assertThat(wait).isEqualTo(754018L);
	}

	@Test
	void example4Part2() {
		long wait = DepartureCalculator.calculate("67,x,7,59,61");
		assertThat(wait).isEqualTo(779210L);
	}

	@Test
	void example5Part2() {
		long wait = DepartureCalculator.calculate("67,7,x,59,61");
		assertThat(wait).isEqualTo(1261476L);
	}

	@Test
	void example56Part2() {
		long wait = DepartureCalculator.calculate("1789,37,47,1889");
		assertThat(wait).isEqualTo(1202161486L);
	}

}