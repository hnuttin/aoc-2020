package com.hnuttin.aoc2020.day11;

import java.util.ArrayList;
import java.util.List;

import lombok.Value;

@Value
class LayoutPosition {

	List<SeatType> types;

	LayoutPosition(SeatType type) {
		this.types = new ArrayList<>();
		this.types.add(type);
	}

	SeatType currentSeatType() {
		return types.get(types.size() - 1);
	}

	SeatType seatTypeForRound(int round) {
		return types.get(round);
	}

	void newSeatType(SeatType type) {
		types.add(type);
	}
}
