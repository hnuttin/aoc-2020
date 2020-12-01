package com.hnuttin.aoc2020.day1;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
class ExpenseReport {

	private static final int MATCH_VALUE = 2020;

	List<Integer> expenses;

	static ExpenseReport fromInput(List<Integer> input) {
		return new ExpenseReport(input);
	}

	long fix(int permutationLength) {
		return expensePermutations(permutationLength)
				.filter(ExpensePermutation::matches2020)
				.findFirst()
				.map(ExpensePermutation::multiply)
				.orElseThrow(IllegalArgumentException::new);
	}

	private Stream<ExpensePermutation> expensePermutations(int permutationLength) {
		return expenses.stream()
				.map(ExpensePermutation::singlePermutation)
				.flatMap(singlePermutation -> extendPermutation(singlePermutation, permutationLength - 1));
	}

	private Stream<ExpensePermutation> extendPermutation(ExpensePermutation permutation, int permutationLength) {
		if (permutationLength <= 0) {
			return Stream.of(permutation);
		} else {
			return expenses.stream()
					.map(permutation::extendPermutation)
					.filter(ExpensePermutation::lowerOrEqualTo2020)
					.flatMap(extendedPermutation -> extendPermutation(extendedPermutation, permutationLength - 1));
		}
	}

	@Value
	@AllArgsConstructor
	private static class ExpensePermutation {

		List<Integer> expenses;

		boolean lowerOrEqualTo2020() {
			return reducePermutation(Integer::sum) <= MATCH_VALUE;
		}

		boolean matches2020() {
			return reducePermutation(Integer::sum) == MATCH_VALUE;
		}

		long multiply() {
			return reducePermutation(Math::multiplyExact);
		}

		private Integer reducePermutation(BinaryOperator<Integer> reduceFunction) {
			return expenses.stream().reduce(reduceFunction).orElseThrow(IllegalArgumentException::new);
		}

		ExpensePermutation extendPermutation(int expense) {
			return new ExpensePermutation(Stream.concat(expenses.stream(), Stream.of(expense)).collect(toList()));
		}

		static ExpensePermutation singlePermutation(int expense) {
			return new ExpensePermutation(singletonList(expense));
		}
	}
}
