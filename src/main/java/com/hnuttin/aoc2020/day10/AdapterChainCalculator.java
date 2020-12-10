package com.hnuttin.aoc2020.day10;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.of;

import java.util.ArrayList;
import java.util.List;

class AdapterChainCalculator {

	static int calculateDifferences(List<Integer> adapters) {
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

	static long calculateValidCombinations(List<Integer> adapters) {
		return concat(of(0), adapters.stream())
				.sorted()
				.reduce(
						(List<List<Integer>>) new ArrayList<List<Integer>>(),
						(acc, value) -> {
							if (acc.isEmpty()) {
								ArrayList<Integer> newGroup = new ArrayList<>();
								newGroup.add(value);
								acc.add(newGroup);
							} else {
								List<Integer> previousGroup = acc.get(acc.size() - 1);
								if (value - previousGroup.get(previousGroup.size() - 1) == 1) {
									previousGroup.add(value);
								} else {
									ArrayList<Integer> newGroup = new ArrayList<>();
									newGroup.add(value);
									acc.add(newGroup);
								}
							}
							return acc;
						},
						(list1, list2) -> emptyList())
				.stream()
				.map(group -> switch (group.size()) {
					case 1, 2 -> 1L;
					case 3 -> 2L;
					case 4 -> 4L;
					case 5 -> 7L;
					default -> throw new IllegalArgumentException();
				})
				.reduce((a, b) -> a * b)
				.orElseThrow();
	}

}
