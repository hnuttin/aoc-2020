package com.hnuttin.aoc2020.day13;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.tuple.Pair.of;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

class DepartureCalculator {

	static long calculate(String earliestDepartureValue, String busses) {
		long earlestDeparture = Long.parseLong(earliestDepartureValue);
		Pair<Long, BusId> earliestResult = parseBusIds(busses).stream()
				.map(busId -> of(busId.getEarliestDeparture(earlestDeparture), busId))
				.min(Comparator.comparingLong(Pair::getLeft))
				.orElseThrow();
		return (earliestResult.getLeft() - earlestDeparture) * earliestResult.getRight().id();
	}

	static long calculate(String busses) {
		List<BusId> busIds = parseBusIds(busses);
		long previous = 0L;
		long cycle = 0L;
		for (BusId busId : busIds) {
			if (previous == 0L) {
				previous = busId.id();
				cycle = busId.id();
			} else {
				long firstMatch = findMatch(previous, cycle, busId);
				previous = firstMatch;
				if (busIds.indexOf(busId) < busIds.size() - 1) {
					long secondMatch = findMatch(firstMatch - busId.positionToPreviousBus() + cycle, cycle, busId);
					cycle = secondMatch - firstMatch;
				}
			}
		}
		return previous - busIds.stream().map(BusId::positionToPreviousBus).reduce(Integer::sum).orElseThrow();
	}

	private static Long findMatch(long previous, long cycle, BusId busId) {
		return Stream.iterate(previous, p -> p + cycle)
				.filter(departure -> (departure + busId.positionToPreviousBus()) % busId.id() == 0)
				.findFirst()
				.map(departure -> departure + busId.positionToPreviousBus())
				.orElseThrow();
	}

	private static List<BusId> parseBusIds(String busses) {
		String[] split = busses.split(",");
		return IntStream
				.range(0, split.length)
				.filter(i -> !split[i].equals("x"))
				.mapToObj(i -> BusId.fromString(split[i], positionToPreviousBus(split, i)))
				.collect(toList());
	}

	private static int positionToPreviousBus(String[] split, int i) {
		if (i == 0) {
			return 0;
		} else {
			int prev = i - 1;
			while (split[prev].equals("x")) {
				prev--;
			}
			return i - prev;
		}
	}

}
