package com.hnuttin.aoc2020.day13;

import static java.lang.Long.parseLong;

import java.util.stream.Stream;

record BusId(long id, int positionToPreviousBus) {

	long getEarliestDeparture(long earliestDeparture) {
		return Stream.iterate(id, previous -> previous + id)
				.filter(departure -> departure >= earliestDeparture)
				.findFirst()
				.orElseThrow();
	}

	static BusId fromString(String value, int positionToPreviousBus) {
		return new BusId(parseLong(value), positionToPreviousBus);
	}

}
