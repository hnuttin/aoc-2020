package com.hnuttin.aoc2020.day7;

import java.util.List;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;
import com.hnuttin.aoc2020.common.input.InputParsers;

@AocDay
public class Day7 {

	public static void main(String... args) {
		AocApp.<List<BagRule>>forDay(7)
				.withInputParser(InputParsers.aLineParser(BagRule::fromRawInput)::parse)
				.withPart1(bagRules -> new BagRegulations(bagRules).countBagOptions("shiny gold"))
				.withPart2(bagRules -> new BagRegulations(bagRules).countChildBags("shiny gold"))
				.run();
	}
}
