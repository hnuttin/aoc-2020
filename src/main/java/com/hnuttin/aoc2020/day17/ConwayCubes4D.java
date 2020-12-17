package com.hnuttin.aoc2020.day17;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ConwayCubes4D {

	private final Map<Coordinate4D, Cube> cubes;

	long cycle(int rounds) {
		IntStream.range(0, rounds).forEach(this::progress);
		return cubes.values().stream()
				.filter(Cube::isActive)
				.count();
	}

	private void progress(int round) {
		int startX = minCoord(Coordinate4D::x) - 1;
		int endX = maxCoord(Coordinate4D::x) + 2;
		int startY = minCoord(Coordinate4D::y) - 1;
		int endY = maxCoord(Coordinate4D::y) + 2;
		int startZ = minCoord(Coordinate4D::z) - 1;
		int endZ = maxCoord(Coordinate4D::z) + 2;
		int startW = minCoord(Coordinate4D::z) - 1;
		int endW = maxCoord(Coordinate4D::z) + 2;
		for (int x = startX; x < endX; x++) {
			for (int y = startY; y < endY; y++) {
				for (int z = startZ; z < endZ; z++) {
					for (int w = startW; w < endW; w++) {
						Coordinate4D coord = new Coordinate4D(x, y, z, w);
						Cube cube = cubes.computeIfAbsent(coord, c -> new Cube(false, round + 1));
						long adjacentActive = coord.adjacentCoordinates()
								.filter(adjacent -> isActiveAt(adjacent, round))
								.count();
						if (cube.isActive()) {
							cube.addActive(adjacentActive == 2 || adjacentActive == 3);
						} else {
							cube.addActive(adjacentActive == 3);
						}
					}
				}
			}
		}
	}

	private boolean isActiveAt(Coordinate4D coord, int round) {
		Cube cube = cubes.get(coord);
		return cube != null && cube.isActive(round);
	}

	private int minCoord(ToIntFunction<Coordinate4D> coordFunction) {
		return cubes.keySet().stream()
				.mapToInt(coordFunction)
				.min().orElseThrow();
	}

	private int maxCoord(ToIntFunction<Coordinate4D> coordFunction) {
		return cubes.keySet().stream()
				.mapToInt(coordFunction)
				.max().orElseThrow();
	}

	static ConwayCubes4D fromRawInput(List<String> rawInput) {
		Map<Coordinate4D, Cube> cubes = IntStream.range(0, rawInput.size())
				.boxed()
				.flatMap(y -> IntStream.range(0, rawInput.get(y).length()).mapToObj(x -> new Coordinate4D(x, y, 0, 0)))
				.collect(toMap(identity(), coord -> new Cube(rawInput.get(coord.y()).charAt(coord.x()) == '#')));
		return new ConwayCubes4D(cubes);
	}

	@Getter
	private static class Cube {

		private List<Boolean> active = new ArrayList<>();

		Cube(boolean active) {
			this.active.add(active);
		}

		Cube(boolean active, int initialSize) {
			this.active = IntStream.range(0, initialSize).mapToObj(i -> i == initialSize && active).collect(toList());
		}

		boolean isActive() {
			return active.get(active.size() - 1);
		}

		public boolean isActive(int round) {
			return active.get(round);
		}

		public void addActive(boolean active) {
			this.active.add(active);
		}
	}
}
