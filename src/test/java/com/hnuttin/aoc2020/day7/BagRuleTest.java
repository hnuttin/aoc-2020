package com.hnuttin.aoc2020.day7;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BagRuleTest {

	@Test
	void testParseRawInput_MultipleChildColors() {
		BagRule bagRule = BagRule.fromRawInput("light red bags contain 1 bright white bag, 2 muted yellow bags.");

		assertThat(bagRule.color()).isEqualTo("light red");
		assertThat(bagRule.bagSlotRules()).containsExactlyInAnyOrder(new BagRule.BagSlotRule("bright white", 1), new BagRule.BagSlotRule("muted yellow", 2));
	}

	@Test
	void testParseRawInput_SingleChildColor() {
		BagRule bagRule = BagRule.fromRawInput("bright white bags contain 1 shiny gold bag.");

		assertThat(bagRule.color()).isEqualTo("bright white");
		assertThat(bagRule.bagSlotRules()).containsExactlyInAnyOrder(new BagRule.BagSlotRule("shiny gold", 1));
	}

	@Test
	void testParseRawInput_NoChildColors() {
		BagRule bagRule = BagRule.fromRawInput("dotted black bags contain no other bags.");

		assertThat(bagRule.color()).isEqualTo("dotted black");
		assertThat(bagRule.bagSlotRules()).isEmpty();
	}

}