package com.hnuttin.aoc2020.day3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class SlopeCheckerTest {

	@Test
	void testPart2() {
		TreeMap treeMap = TreeMap.fromRawInput(Arrays.asList(
				"..##.......",
				"#...#...#..",
				".#....#..#.",
				"..#.#...#.#",
				".#...##..#.",
				"..#.##.....",
				".#.#.#....#",
				".#........#",
				"#.##...#...",
				"#...##....#",
				".#..#...#.#"
		));

		assertThat(SlopeChecker.countTotalTrees(treeMap)).isEqualTo(336);
	}

}