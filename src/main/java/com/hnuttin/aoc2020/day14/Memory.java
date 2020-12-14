package com.hnuttin.aoc2020.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Memory {

	private static final Pattern MASK_PATTERN = Pattern.compile("mask = (.*)");
	private static final Pattern MEM_PATTERN = Pattern.compile("mem\\[(\\d+)] = (.*)");

	private final Map<Long, Long> addresses = new HashMap<>();

	private BitMask currentBitMask;

	long initialize(List<String> instructions) {
		instructions.forEach(this::doInstruction);
		return addresses.values().stream().reduce(Long::sum).orElseThrow();
	}

	private void doInstruction(String instruction) {
		Matcher maskMatcher = MASK_PATTERN.matcher(instruction);
		if (maskMatcher.matches()) {
			currentBitMask = BitMask.fromString(maskMatcher.group(1));
		} else {
			Matcher memMatcher = MEM_PATTERN.matcher(instruction);
			if (memMatcher.matches()) {
				long address = Long.parseLong(memMatcher.group(1));
				long value = Long.parseLong(memMatcher.group(2));
				addresses.put(address, currentBitMask.apply(value));
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
			long setBits = Long.parseLong(mask.replaceAll("X", "0"), 2);
			long unsetBits = Long.parseLong(mask.replaceAll("0", "Y").replaceAll("[X1]", "0").replaceAll("Y", "1"), 2);
			return new BitMask(setBits, unsetBits);
		}
	}
}
