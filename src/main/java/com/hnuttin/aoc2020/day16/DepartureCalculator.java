package com.hnuttin.aoc2020.day16;

import static java.util.Collections.emptyList;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

public class DepartureCalculator {

	static long departureValue(List<FieldRule> rules, Ticket myTicket, List<Ticket> nearbyTickets) {
		List<String> fieldNamesInOrder = fieldNamesInOrder(rules, nearbyTickets);
		return fieldNamesInOrder.stream()
				.filter(fieldName -> fieldName.startsWith("departure"))
				.map(fieldNamesInOrder::indexOf)
				.map(departureFieldPosition -> myTicket.values().get(departureFieldPosition))
				.map(Long::valueOf)
				.reduce((value1, value2) -> value1 * value2)
				.orElse(0L);
	}

	static List<String> fieldNamesInOrder(List<FieldRule> rules, List<Ticket> nearbyTickets) {
		List<Ticket> validTickets = TicketValidator.keepValidTickets(rules, nearbyTickets);
		List<FieldRule> rulesLeft = new ArrayList<>(rules);
		return IntStream.range(0, validTickets.get(0).values().size())
				.boxed()
				.map(fieldPosition -> Pair.of(fieldPosition, validFieldRules(fieldPosition, rules, validTickets)))
				.sorted(comparingInt(validRules -> validRules.getRight().size()))
				.map(validRules -> {
					FieldRule onlyValidRule = validRules.getRight().stream().filter(rulesLeft::contains).findFirst().orElseThrow();
					rulesLeft.remove(onlyValidRule);
					return Pair.of(validRules.getLeft(), onlyValidRule);
				})
				.sorted(comparingInt(Pair::getLeft))
				.map(validRule -> validRule.getRight().field())
				.collect(toList());
	}

	private static List<FieldRule> validFieldRules(int fieldPosition, List<FieldRule> rules, List<Ticket> validTickets) {
		return validTickets.stream()
				.map(ticket -> ticket.values().get(fieldPosition))
				.reduce(rules, DepartureCalculator::filterRules, (l1, l2) -> emptyList());
	}

	private static List<FieldRule> filterRules(List<FieldRule> rulesLeft, Integer value) {
		return rulesLeft.stream()
				.filter(rule -> rule.isValid(value))
				.collect(toList());
	}
}
