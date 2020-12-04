package com.hnuttin.aoc2020.day3;

import java.util.List;

import com.hnuttin.aoc2020.common.Coordinate;
import com.hnuttin.aoc2020.common.Slope;

import lombok.Value;

@Value
class TreeMap {

	Boolean[][] trees;

	static TreeMap fromRawInput(List<String> rawInput) {
		Boolean[][] trees = rawInput.stream()
				.map(line -> line.chars().mapToObj(value -> value == '#').toArray(Boolean[]::new))
				.toArray(Boolean[][]::new);
		return new TreeMap(trees);
	}

	long countTrees(Coordinate position, Slope slope) {
		if (position.getY() < trees.length) {
			return countTreeOnPosition(position) + countTrees(position.add(slope), slope);
		} else {
			return 0L;
		}
	}

	private long countTreeOnPosition(Coordinate position) {
		int x = position.getX() % getWidth();
		return trees[position.getY()][x] ? 1L : 0L;
	}

	private int getWidth() {
		return trees[0].length;
	}

}
