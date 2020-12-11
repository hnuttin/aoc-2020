package com.hnuttin.aoc2020.day11;

import static com.hnuttin.aoc2020.day11.SeatType.EMPTY_SEAT;
import static com.hnuttin.aoc2020.day11.SeatType.FLOOR;
import static com.hnuttin.aoc2020.day11.SeatType.OCCUPIED_SEAT;
import static java.util.function.Function.identity;
import static java.util.stream.Stream.of;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SeatLayout {

	int round = 0;

	boolean printEnabled;

	private final LayoutPosition[][] layout;

	void enablePrint() {
		printEnabled = true;
	}

	long runUntilStable(boolean part1) {
		boolean anyPositionUnstable = false;
		printCurrentLayout();
		for (int row = 0; row < layout.length; row++) {
			LayoutPosition[] rowLayout = layout[row];
			for (int column = 0; column < rowLayout.length; column++) {
				boolean positionChanged = progress(rowLayout[column], row, column, part1);
				anyPositionUnstable = anyPositionUnstable || positionChanged;
			}
		}
		if (anyPositionUnstable) {
			round++;
			return runUntilStable(part1);
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

	private boolean progress(LayoutPosition layoutPosition, int row, int column, boolean part1) {
		if (layoutPosition.currentSeatType() == EMPTY_SEAT && emptyBecomesOccupied(row, column, part1)) {
			layoutPosition.newSeatType(OCCUPIED_SEAT);
			return true;
		} else if (layoutPosition.currentSeatType() == OCCUPIED_SEAT && occupiedBecomesEmpty(row, column, part1)) {
			layoutPosition.newSeatType(EMPTY_SEAT);
			return true;
		} else {
			layoutPosition.newSeatType(layoutPosition.currentSeatType());
			return false;
		}
	}

	private boolean emptyBecomesOccupied(int row, int column, boolean part1) {
		if (part1) {
			return adjacentLayoutPositions(row, column).noneMatch(position -> position.seatTypeForRound(round) == OCCUPIED_SEAT);
		} else {
			return visibleLayoutPositions(row, column).noneMatch(position -> position.seatTypeForRound(round) == OCCUPIED_SEAT);
		}
	}

	private boolean occupiedBecomesEmpty(int row, int column, boolean part1) {
		if (part1) {
			return adjacentLayoutPositions(row, column).filter(position -> position.seatTypeForRound(round) == OCCUPIED_SEAT).count() >= 4;
		} else {
			return visibleLayoutPositions(row, column).filter(position -> position.seatTypeForRound(round) == OCCUPIED_SEAT).count() >= 5;
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

	private Stream<LayoutPosition> visibleLayoutPositions(int row, int column) {
		return Stream.of(
				searchUntilSeat(row, r -> r - 1, column, c -> c - 1),
				searchUntilSeat(row, identity(), column, c -> c - 1),
				searchUntilSeat(row, r -> r + 1, column, c -> c - 1),
				searchUntilSeat(row, r -> r + 1, column, identity()),
				searchUntilSeat(row, r -> r + 1, column, c -> c + 1),
				searchUntilSeat(row, identity(), column, c -> c + 1),
				searchUntilSeat(row, r -> r - 1, column, c -> c + 1),
				searchUntilSeat(row, r -> r - 1, column, identity())
		).filter(LayoutCoordinate::isValid).map(coord -> layout[coord.row][coord.column]);
	}

	private LayoutCoordinate searchUntilSeat(int row, Function<Integer, Integer> rowIncrementer, int column, Function<Integer, Integer> columnIncrementer) {
		int newRow = rowIncrementer.apply(row);
		int newColumn = columnIncrementer.apply(column);
		if (newRow < 0 ||
				newRow >= layout.length ||
				newColumn < 0 ||
				newColumn >= layout[0].length ||
				layout[newRow][newColumn].seatTypeForRound(round) == EMPTY_SEAT ||
				layout[newRow][newColumn].seatTypeForRound(round) == OCCUPIED_SEAT) {
			return new LayoutCoordinate(newRow, newColumn);
		} else {
			return searchUntilSeat(newRow, rowIncrementer, newColumn, columnIncrementer);
		}
	}

	@RequiredArgsConstructor
	public class LayoutCoordinate {

		private final int row;
		private final int column;

		public boolean isValid() {
			return row >= 0 && row < layout.length &&
					column >= 0 && column < layout[0].length;
		}
	}

	static SeatLayout fromRawInput(List<String> rawInput) {
		LayoutPosition[][] layout = rawInput.stream()
				.map(SeatLayout::toLayoutPositions)
				.toArray(LayoutPosition[][]::new);
		return new SeatLayout(layout);
	}

	static LayoutPosition[] toLayoutPositions(String line) {
		return line.chars().mapToObj(value -> switch (value) {
			case 'L' -> new LayoutPosition(EMPTY_SEAT);
			case '.' -> new LayoutPosition(FLOOR);
			default -> throw new IllegalArgumentException();
		}).toArray(LayoutPosition[]::new);
	}
}
