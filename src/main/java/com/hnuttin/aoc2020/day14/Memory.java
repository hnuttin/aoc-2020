package com.hnuttin.aoc2020.day14;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class Memory {

	private static final Pattern MASK_PATTERN = Pattern.compile("mask = (.*)");
	private static final Pattern MEM_PATTERN = Pattern.compile("mem\\[(\\d+)] = (.*)");

	private final Map<Long, Long> addresses = new HashMap<>();

	private String currentBitMask;

	long initialize(List<String> instructions, boolean version2) {
		instructions.forEach(instruction -> doInstruction(instruction, version2));
		return addresses.values().stream().reduce(Long::sum).orElseThrow();
	}

	private void doInstruction(String instruction, boolean version2) {
		Matcher maskMatcher = MASK_PATTERN.matcher(instruction);
		if (maskMatcher.matches()) {
			currentBitMask = maskMatcher.group(1);
		} else {
			Matcher memMatcher = MEM_PATTERN.matcher(instruction);
			if (memMatcher.matches()) {
				long address = parseLong(memMatcher.group(1));
				long value = parseLong(memMatcher.group(2));
				if (version2) {
					BitMask.fromVersion2Mask(currentBitMask)
							.forEach(bitMask -> addresses.put(bitMask.apply(address), value));
				} else {
					addresses.put(address, BitMask.fromString(currentBitMask).apply(value));
				}
			} else {
				throw new IllegalArgumentException();
			}
		}
	}

	private static record BitMask(long setBits, long unsetBits) {

		public Long apply(long value) {
			value |= setBits;
			value &= ~unsetBits;
			return value;
		}

		static BitMask fromString(String mask) {
			long setBits = parseLong(mask.replaceAll("X", "0"), 2);
			long unsetBits = parseLong(mask.replaceAll("0", "Y").replaceAll("[X1]", "0").replaceAll("Y", "1"), 2);
			return new BitMask(setBits, unsetBits);
		}

		static List<BitMask> fromVersion2Mask(String mask) {
			String highestBinary = mask.chars()
					.filter(c -> c == 'X')
					.map(c -> '1')
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
			return LongStream.range(0, parseLong(highestBinary, 2) + 1)
					.mapToObj(Long::toBinaryString)
					.map(unpadded -> String.format("%" + highestBinary.length() + "s", unpadded).replace(' ', '0'))
					.map(combination -> fromString(combination.chars()
							.mapToObj(c -> String.valueOf((char) c))
							.reduce(mask.replaceAll("0", "Y"), (newMask, c) -> newMask.replaceFirst("X", c))
							.replaceAll("Y", "X")))
					.collect(toList());
		}
	}
}
