package com.hnuttin.aoc2020.common.input;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

public class InputParsers {

	public static <T> LineParser<T> aLineParser(Function<String, T> mapper) {
		return new LineParser<>(mapper);
	}

	public static record LineParser<T>(Function<String, T> mapper) {

		public List<T> parse(java.util.List<String> rawInput) {
			return rawInput.stream().map(mapper).collect(toList());
		}

	}

	public static <T> MultiLineParser<T> aMultiLineParser(Function<List<String>, T> mapper) {
		return new MultiLineParser<>(mapper);
	}

	public static record MultiLineParser<T>(Function<List<String>, T> mapper) {

		public List<T> parse(java.util.List<String> rawInput) {
			return splitByEmptyLine(rawInput).stream().map(mapper).collect(toList());
		}

		private List<List<String>> splitByEmptyLine(List<String> rawInput) {
			List<List<String>> identity = new ArrayList<>();
			identity.add(new ArrayList<>());
			return rawInput.stream()
					.reduce(identity,
							(list, line) -> {
								if (StringUtils.isBlank(line)) {
									list.add(new ArrayList<>());
								} else {
									list.get(list.size() - 1).add(line);
								}
								return list;
							},
							(list1, list2) -> emptyList());
		}

	}
}
