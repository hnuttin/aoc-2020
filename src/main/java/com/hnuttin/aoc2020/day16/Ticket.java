package com.hnuttin.aoc2020.day16;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.util.List;

record Ticket(List<Integer> values) {

	ValidationResult validate(List<FieldRule> rules) {
		return values().stream()
				.filter(value -> rules.stream().allMatch(rule -> rule.isInvalid(value)))
				.findFirst()
				.map(invalidValue -> new ValidationResult(false, invalidValue))
				.orElseGet(() -> new ValidationResult(true, null));
	}

	static Ticket fromString(String rawInput) {
		return new Ticket(stream(rawInput.split(",")).map(Integer::parseInt).collect(toList()));
	}

	static List<Ticket> ticketsFromStrings(List<List<String>> rawInput) {
		return rawInput.get(2).stream().skip(1).map(Ticket::fromString).collect(toList());
	}

	static Ticket myTicketFromStrings(List<List<String>> rawInput) {
		return Ticket.fromString(rawInput.get(1).get(1));
	}
}
