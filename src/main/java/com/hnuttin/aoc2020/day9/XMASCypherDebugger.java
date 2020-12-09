package com.hnuttin.aoc2020.day9;

import java.util.List;

class XMASCypherDebugger {

	static long findInvalidNumber(List<Long> input, int preamble) {
		return findInvalidNumber(input, preamble, preamble);
	}

	private static long findInvalidNumber(List<Long> input, int preamble, int startIndex) {
		Long possibleWeakness = input.get(startIndex);
		if (containsSum(input.subList(startIndex - preamble, startIndex), possibleWeakness)) {
			return findInvalidNumber(input, preamble, startIndex + 1);
		} else {
			return possibleWeakness;
		}
	}

	private static boolean containsSum(List<Long> values, Long sum) {
		return values.stream()
				.flatMap(value -> values.stream().map(otherValue -> new SumPair(value, otherValue)))
				.distinct()
				.anyMatch(sumPair -> sumPair.sum() == sum);
	}

	static long findEncryptionWeakness(List<Long> input, int preamble) {
		long invalidNumber = findInvalidNumber(input, preamble);
		return findEncryptionWeakness(input, invalidNumber);
	}

	private static long findEncryptionWeakness(List<Long> input, long invalidNumber) {
		int startIndex = 0;
		while (startIndex < input.size()) {
			long sum = 0L;
			int endIndex = startIndex - 1;
			while (sum < invalidNumber) {
				endIndex++;
				sum += input.get(endIndex);
			}
			if (sum == invalidNumber) {
				return calculateEncryptionWeakness(input, startIndex, endIndex);
			} else {
				startIndex++;
			}
		}
		throw new IllegalArgumentException();
	}

	private static long calculateEncryptionWeakness(List<Long> input, int startIndex, int endIndex) {
		List<Long> contiguousNumbers = input.subList(startIndex, endIndex + 1);
		return contiguousNumbers.stream().min(Long::compareTo).orElseThrow() + contiguousNumbers.stream().max(Long::compareTo).orElseThrow();
	}

	private static record SumPair(long a, long b) {

		long sum() {
			return a + b;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			SumPair sumPair = (SumPair) o;
			return sum() == sumPair.sum();
		}

		@Override
		public int hashCode() {
			return (int) a + (int) b;
		}
	}

}
