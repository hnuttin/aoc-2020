package com.hnuttin.aoc2020.day7;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

record BagRule(String color, List<BagSlotRule> bagSlotRules) {

	private static final Pattern CONTAINS_DIGIT_PATTERN = Pattern.compile("\\d");

	boolean canContain(String color, List<BagRule> allBagRules) {
		return bagSlotRules.stream().anyMatch(bagSlotRule -> bagSlotRule.canContain(color, allBagRules));
	}

	public long countChildBags(List<BagRule> allBagRules) {
		return bagSlotRules.stream()
				.map(slotRule -> slotRule.number() + slotRule.number() * slotRule.countChildBags(allBagRules))
				.reduce(Long::sum)
				.orElse(0L);
	}

	static record BagSlotRule(String color, int number) {

		boolean canContain(String color, List<BagRule> allBagRules) {
			return allBagRules.stream()
					.filter(rule -> rule.color().equals(this.color))
					.findFirst()
					.map(slotBagRule -> slotBagRule.color().equals(color) || slotBagRule.canContain(color, allBagRules))
					.orElse(false);
		}

		long countChildBags(List<BagRule> allBagRules) {
			return allBagRules.stream()
					.filter(rule -> rule.color().equals(color))
					.findFirst()
					.map(bagRule -> bagRule.countChildBags(allBagRules))
					.orElse(0L);
		}

	}

	static BagRule fromRawInput(String rawInput) {
		String[] bagColorAndChildColors = rawInput.split("contain");
		String color = bagColorAndChildColors[0].replace("bags", "").trim();
		final List<BagSlotRule> childColors;
		if (CONTAINS_DIGIT_PATTERN.matcher(bagColorAndChildColors[1]).find()) {
			childColors = Stream.of(bagColorAndChildColors[1].split(","))
					.map(contain -> contain.replaceAll("bags\\.|bags|bag\\.|bag", ""))
					.map(String::trim)
					.map(childBagRule -> new BagSlotRule(childBagRule.substring(2), Integer.parseInt(childBagRule.substring(0, 1))))
					.collect(toList());
		} else {
			childColors = emptyList();
		}
		return new BagRule(color, childColors);
	}

}
