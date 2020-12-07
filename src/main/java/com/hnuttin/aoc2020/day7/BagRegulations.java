package com.hnuttin.aoc2020.day7;

import java.util.List;

record BagRegulations(List<BagRule> bagRules) {

	long countBagOptions(String color) {
		return bagRules.stream()
				.filter(bagRule -> bagRule.canContain(color, bagRules))
				.count();
	}

	long countChildBags(String color) {
		return bagRules.stream()
				.filter(bagRule -> bagRule.color().equals(color))
				.findFirst()
				.map(bagRule -> bagRule.countChildBags(bagRules))
				.orElseThrow();
	}
}
