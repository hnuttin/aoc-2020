package com.hnuttin.aoc2020.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

class PassportDataReader {

	static List<PassportData> fromRawInput(List<String> rawInput) {
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
						(list1, list2) -> list1)
				.stream()
				.map(PassportData::fromRawInput)
				.collect(Collectors.toList());
	}

}
