package com.hnuttin.aoc2020.day10;

import static java.util.stream.Collectors.toList;

import java.util.List;

class AdapterChainCalculator {

	static int calculate(List<Integer> adapters) {
		int oneJoltDifferences = 0;
		int threeJoltDifferences = 0;
		List<Integer> sortedAdapters = adapters.stream().sorted().collect(toList());
		int currentJolts = 0;
		for (Integer adapter : sortedAdapters) {
			int joltDifference = adapter - currentJolts;
			if (joltDifference == 1) {
				oneJoltDifferences++;
			} else if (joltDifference == 3) {
				threeJoltDifferences++;
			} else {
				throw new IllegalStateException("Unsupported jolt difference: " + joltDifference);
			}
			currentJolts = adapter;
		}
		return oneJoltDifferences * (threeJoltDifferences + 1);
	}

}
