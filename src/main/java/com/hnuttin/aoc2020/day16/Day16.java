package com.hnuttin.aoc2020.day16;

import static com.hnuttin.aoc2020.common.input.InputParsers.aMultiLineParser;
import static com.hnuttin.aoc2020.day16.DepartureCalculator.departureValue;
import static com.hnuttin.aoc2020.day16.FieldRule.rulesFromRawInput;
import static com.hnuttin.aoc2020.day16.Ticket.myTicketFromStrings;
import static com.hnuttin.aoc2020.day16.Ticket.ticketsFromStrings;
import static com.hnuttin.aoc2020.day16.TicketValidator.errorRate;

import java.util.List;
import java.util.function.Function;

import com.hnuttin.aoc2020.common.app.AocApp;
import com.hnuttin.aoc2020.common.app.AocDay;

@AocDay
public class Day16 {

	public static void main(String... args) {
		AocApp.<List<List<String>>>forDay(16)
				.withInputParser(aMultiLineParser(Function.identity())::parse)
				.withPart1(rawInput -> errorRate(rulesFromRawInput(rawInput), ticketsFromStrings(rawInput)))
				.withPart2(rawInput -> departureValue(rulesFromRawInput(rawInput), myTicketFromStrings(rawInput), ticketsFromStrings(rawInput)))
				.run();
	}
}
