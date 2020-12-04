package com.hnuttin.aoc2020.day3;

import com.hnuttin.aoc2020.common.Coordinate;
import com.hnuttin.aoc2020.common.Slope;
import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day3 {

	public static void main(String... args) {
		AocApp.<TreeMap>forDay(3)
				.withInputParser(TreeMap::fromRawInput)
				.withPart1(treeMap -> treeMap.countTrees(new Coordinate(0, 0), new Slope(3, 1)))
				.withPart2(SlopeChecker::countTotalTrees)
				.run();
	}
}
