package com.hnuttin.aoc2020.day16;

import static java.util.stream.Collectors.toList;

import java.util.List;

class TicketValidator {

	static int errorRate(List<FieldRule> rules, List<Ticket> tickets) {
		return tickets.stream()
				.map(ticket -> ticket.validate(rules))
				.filter(ValidationResult::isInvalid)
				.map(ValidationResult::invalidValue)
				.reduce(Integer::sum)
				.orElse(0);
	}

	static List<Ticket> keepValidTickets(List<FieldRule> rules, List<Ticket> tickets) {
		return tickets.stream()
				.filter(ticket -> ticket.validate(rules).valid())
				.collect(toList());
	}
}
