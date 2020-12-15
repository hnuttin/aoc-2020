package com.hnuttin.aoc2020.day15;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class NumberGame {

	private final List<Integer> startNumbers;

	private final Map<Integer, Spoken> spokenNumbers = new HashMap<>();

	int getNthNumber(int nthNumber) {
		int previousNumber = 0;
		for (int round = 0; round < nthNumber; round++) {
			if (round < startNumbers.size()) {
				spokenNumbers.put(startNumbers.get(round), new Spoken(round, null));
				previousNumber = startNumbers.get(round);
			} else {
				Spoken previousNumberSpoken = spokenNumbers.get(previousNumber);
				final int numberToAdd;
				if (previousNumberSpoken.spokenPreviously()) {
					numberToAdd = round - 1 - previousNumberSpoken.previousRound();
				} else {
					numberToAdd = 0;
				}
				Spoken numberToAddSpoken = spokenNumbers.get(numberToAdd);
				if (numberToAddSpoken == null) {
					spokenNumbers.put(numberToAdd, new Spoken(round, null));
				} else {
					spokenNumbers.put(numberToAdd, new Spoken(round, numberToAddSpoken.round()));
				}
				previousNumber = numberToAdd;
			}
		}
		return previousNumber;
	}

	static NumberGame fromString(String startNumbers) {
		return new NumberGame(stream(startNumbers.split(",")).map(Integer::parseInt).collect(toList()));
	}

	private record Spoken(int round, Integer previousRound) {

		boolean spokenPreviously() {
			return previousRound != null;
		}

	}

}
