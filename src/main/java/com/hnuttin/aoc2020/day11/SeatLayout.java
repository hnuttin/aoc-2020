package com.hnuttin.aoc2020.day11;

import static com.hnuttin.aoc2020.day11.SeatLayout.SeatType.EMPTY_SEAT;
import static com.hnuttin.aoc2020.day11.SeatLayout.SeatType.FLOOR;
import static com.hnuttin.aoc2020.day11.SeatLayout.SeatType.OCCUPIED_SEAT;
import static java.util.stream.Stream.of;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
class SeatLayout {

	int round = 0;

	boolean printEnabled;

	private final LayoutPosition[][] layout;

	void enablePrint() {
		printEnabled = true;
	}

	long runUntilStable() {
		boolean anyPositionUnstable = false;
		printCurrentLayout();
		for (int row = 0; row < layout.length; row++) {
			LayoutPosition[] rowLayout = layout[row];
			for (int column = 0; column < rowLayout.length; column++) {
				boolean positionChanged = progress(rowLayout[column], row, column);
				anyPositionUnstable = anyPositionUnstable || positionChanged;
			}
		}
		if (anyPositionUnstable) {
			round++;
			return runUntilStable();
		} else {
			printCurrentLayout();
			return of(layout)
					.flatMap(Arrays::stream)
					.map(LayoutPosition::currentSeatType)
					.filter(seatType -> seatType == OCCUPIED_SEAT)
					.count();
		}
	}

	private void printCurrentLayout() {
		if (printEnabled) {
			of(layout)
					.forEach(row -> System.out.println(of(row)
							.map(layoutPosition -> layoutPosition.currentSeatType().getValue())
							.collect(Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append, StringBuilder::toString))));
			System.out.println();
		}
	}

	private boolean progress(LayoutPosition layoutPosition, int row, int column) {
		if (layoutPosition.currentSeatType() == EMPTY_SEAT && adjacentLayoutPositions(row, column).noneMatch(position -> position.seatTypeForRound(round) == OCCUPIED_SEAT)) {
			layoutPosition.newSeatType(OCCUPIED_SEAT);
			return true;
		} else if (layoutPosition.currentSeatType() == OCCUPIED_SEAT && adjacentLayoutPositions(row, column).filter(position -> position.seatTypeForRound(round) == OCCUPIED_SEAT).count() >= 4) {
			layoutPosition.newSeatType(EMPTY_SEAT);
			return true;
		} else {
			layoutPosition.newSeatType(layoutPosition.currentSeatType());
			return false;
		}
	}

	private Stream<LayoutPosition> adjacentLayoutPositions(int row, int column) {
		return of(
				new LayoutCoordinate(row - 1, column - 1),
				new LayoutCoordinate(row - 1, column),
				new LayoutCoordinate(row - 1, column + 1),
				new LayoutCoordinate(row, column - 1),
				new LayoutCoordinate(row, column + 1),
				new LayoutCoordinate(row + 1, column - 1),
				new LayoutCoordinate(row + 1, column),
				new LayoutCoordinate(row + 1, column + 1)
		).filter(LayoutCoordinate::isValid).map(coord -> layout[coord.row][coord.column]);
	}

	static SeatLayout fromRawInput(List<String> rawInput) {
		LayoutPosition[][] layout = rawInput.stream()
				.map(SeatLayout::toLayoutPositions)
				.toArray(LayoutPosition[][]::new);
		return new SeatLayout(layout);
	}

	private static LayoutPosition[] toLayoutPositions(String line) {
		return line.chars().mapToObj(value -> switch (value) {
			case 'L' -> new LayoutPosition(EMPTY_SEAT);
			case '.' -> new LayoutPosition(FLOOR);
			default -> throw new IllegalArgumentException();
		}).toArray(LayoutPosition[]::new);
	}

	@Value
	static class LayoutPosition {

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

	@Getter
	@RequiredArgsConstructor
	enum SeatType {
		FLOOR('.'), EMPTY_SEAT('L'), OCCUPIED_SEAT('#');

		private final char value;

	}

	@RequiredArgsConstructor
	private class LayoutCoordinate {

		private final int row;
		private final int column;

		public boolean isValid() {
			return row >= 0 && row < layout.length &&
					column >= 0 && column < layout[0].length;
		}
	}
}
