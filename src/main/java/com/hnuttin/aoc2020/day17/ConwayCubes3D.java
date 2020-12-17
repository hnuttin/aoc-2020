package com.hnuttin.aoc2020.day17;

import static java.util.Comparator.comparingInt;
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
class ConwayCubes3D {

	private final Map<Coordinate3D, Cube> cubes;
	private boolean print;

	long cycle(int rounds) {
		IntStream.range(0, rounds).forEach(this::progress);
		return cubes.values().stream()
				.filter(Cube::isActive)
				.count();
	}

	void enablePrint() {
		print = true;
	}

	private void progress(int round) {
		int startX = minCoord(Coordinate3D::x) - 1;
		int endX = maxCoord(Coordinate3D::x) + 2;
		int startY = minCoord(Coordinate3D::y) - 1;
		int endY = maxCoord(Coordinate3D::y) + 2;
		int startZ = minCoord(Coordinate3D::z) - 1;
		int endZ = maxCoord(Coordinate3D::z) + 2;
		for (int x = startX; x < endX; x++) {
			for (int y = startY; y < endY; y++) {
				for (int z = startZ; z < endZ; z++) {
					Coordinate3D coord = new Coordinate3D(x, y, z);
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
		if (print) {
			int maxX = maxCoord(Coordinate3D::x);
			int maxY = maxCoord(Coordinate3D::y);
			cubes.keySet().stream()
					.sorted(comparingInt(Coordinate3D::z).thenComparing(Coordinate3D::y).thenComparing(Coordinate3D::x))
					.collect(toList())
					.forEach(coord -> {
						System.out.print(cubes.get(coord).isActive() ? '#' : '.');
						if (coord.x() == maxX) {
							System.out.println();
							if (coord.y() == maxY) {
								System.out.println();
								System.out.println();
							}
						}

					});
		}
	}

	private boolean isActiveAt(Coordinate3D coord, int round) {
		Cube cube = cubes.get(coord);
		return cube != null && cube.isActive(round);
	}

	private int minCoord(ToIntFunction<Coordinate3D> coordFunction) {
		return cubes.keySet().stream()
				.mapToInt(coordFunction)
				.min().orElseThrow();
	}

	private int maxCoord(ToIntFunction<Coordinate3D> coordFunction) {
		return cubes.keySet().stream()
				.mapToInt(coordFunction)
				.max().orElseThrow();
	}

	static ConwayCubes3D fromRawInput(List<String> rawInput) {
		Map<Coordinate3D, Cube> cubes = IntStream.range(0, rawInput.size())
				.boxed()
				.flatMap(y -> IntStream.range(0, rawInput.get(y).length()).mapToObj(x -> new Coordinate3D(x, y, 0)))
				.collect(toMap(identity(), coord -> new Cube(rawInput.get(coord.y()).charAt(coord.x()) == '#')));
		return new ConwayCubes3D(cubes);
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
