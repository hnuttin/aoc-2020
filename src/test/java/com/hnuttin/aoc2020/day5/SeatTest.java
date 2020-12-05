package com.hnuttin.aoc2020.day5;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SeatTest {

	@Test
	void seatIdPart1() {
		assertThat(new Seat("FBFBBFFRLR").seatId()).isEqualTo(357);
		assertThat(new Seat("BFFFBBFRRR").seatId()).isEqualTo(567);
		assertThat(new Seat("FFFBBBFRRR").seatId()).isEqualTo(119);
		assertThat(new Seat("BBFFBBFRLL").seatId()).isEqualTo(820);
	}

}