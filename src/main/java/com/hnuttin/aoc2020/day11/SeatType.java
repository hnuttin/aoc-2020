package com.hnuttin.aoc2020.day11;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
enum SeatType {
	FLOOR('.'), EMPTY_SEAT('L'), OCCUPIED_SEAT('#');

	private final char value;

}
