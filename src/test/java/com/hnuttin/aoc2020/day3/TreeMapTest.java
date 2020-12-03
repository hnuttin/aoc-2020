package com.hnuttin.aoc2020.day3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.hnuttin.aoc2020.common.Coordinate;
import com.hnuttin.aoc2020.common.Slope;

class TreeMapTest {

	@Test
	void testPart1() {
		long trees = TreeMap.fromRawInput(Arrays.asList(
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
		)).countTrees(new Coordinate(0, 0), new Slope(3, 1));

		assertThat(trees).isEqualTo(7);
	}

}