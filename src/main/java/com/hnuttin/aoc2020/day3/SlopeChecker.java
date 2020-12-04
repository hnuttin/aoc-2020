package com.hnuttin.aoc2020.day3;

import java.util.stream.Stream;

import com.hnuttin.aoc2020.common.Coordinate;
import com.hnuttin.aoc2020.common.Slope;

class SlopeChecker {

	static long countTotalTrees(TreeMap treeMap) {
		Stream<Slope> slopes = Stream.of(
				new Slope(1, 1),
				new Slope(3, 1),
				new Slope(5, 1),
				new Slope(7, 1),
				new Slope(1, 2)
		);
		return slopes
				.map(slope -> treeMap.countTrees(new Coordinate(0, 0), slope))
				.reduce(Math::multiplyExact)
				.orElseThrow(IllegalStateException::new);
	}
}
