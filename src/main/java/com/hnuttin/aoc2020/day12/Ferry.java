package com.hnuttin.aoc2020.day12;

import static com.hnuttin.aoc2020.common.Direction.EAST;
import static com.hnuttin.aoc2020.common.Direction.NORTH;
import static com.hnuttin.aoc2020.common.Direction.SOUTH;
import static com.hnuttin.aoc2020.common.Direction.WEST;
import static java.lang.Math.abs;

import java.util.List;

import com.hnuttin.aoc2020.common.Coordinate;
import com.hnuttin.aoc2020.common.Degrees;
import com.hnuttin.aoc2020.common.Direction;

class Ferry {

	private Coordinate waypoint = null;

	private Coordinate position = new Coordinate(0, 0);
	private Direction orientation = EAST;

	public Ferry() {
	}

	public Ferry(Coordinate waypoint) {
		this.waypoint = waypoint;
	}

	int navigate(List<String> instructions) {
		instructions.forEach(this::navigate);
		return abs(position.getX()) + abs(position.getY());
	}

	private void navigate(String instruction) {
		int value = Integer.parseInt(instruction.substring(1));
		switch (instruction.charAt(0)) {
		case 'N' -> move(NORTH, value);
		case 'S' -> move(SOUTH, value);
		case 'E' -> move(EAST, value);
		case 'W' -> move(WEST, value);
		case 'L' -> {
			if (hasWaypoint()) {
				waypoint = waypoint.rotateLeftAroundCenter(new Degrees(value));
			} else {
				orientation = orientation.turnLeft(new Degrees(value));
			}
		}
		case 'R' -> {
			if (hasWaypoint()) {
				waypoint = waypoint.rotateRightAroundCenter(new Degrees(value));
			} else {
				orientation = orientation.turnRight(new Degrees(value));
			}
		}
		case 'F' -> {
			if (hasWaypoint()) {
				position = position.move(waypoint.getQuadrant().getXDirection(), Math.abs(waypoint.getX()) * value);
				position = position.move(waypoint.getQuadrant().getYDirection(), Math.abs(waypoint.getY()) * value);
			} else {
				position = position.move(orientation, value);
			}
		}
		default -> throw new UnsupportedOperationException();
		}
	}

	private void move(Direction direction, int value) {
		if (hasWaypoint()) {
			waypoint = waypoint.move(direction, value);
		} else {
			position = position.move(direction, value);
		}
	}

	private boolean hasWaypoint() {
		return waypoint != null;
	}

}
