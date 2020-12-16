package com.hnuttin.aoc2020.day16;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

record FieldRule(String field, List<Range> validRanges) {

	private static final Pattern RANGE_PATTERN = Pattern.compile("(\\d*)-(\\d*)");

	boolean isValid(int value) {
		return validRanges.stream().anyMatch(range -> range.isValid(value));
	}

	boolean isInvalid(int value) {
		return !isValid(value);
	}

	static FieldRule fromString(String rawInput) {
		Matcher matcher = RANGE_PATTERN.matcher(rawInput);
		List<Range> validRanges = new ArrayList<>();
		while (matcher.find()) {
			validRanges.add(new Range(parseInt(matcher.group(1)), parseInt(matcher.group(2))));
		}
		return new FieldRule(rawInput.split(":")[0], validRanges);
	}

	static List<FieldRule> rulesFromRawInput(List<List<String>> rawInput) {
		return rawInput.get(0).stream().map(FieldRule::fromString).collect(toList());
	}

	record Range(int min, int max) {

		boolean isValid(int value) {
			return value >= min && value <= max;
		}

	}

}
