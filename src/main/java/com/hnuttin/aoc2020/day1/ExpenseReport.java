package com.hnuttin.aoc2020.day1;

import lombok.Value;

import java.util.List;
import java.util.stream.Stream;

@Value
class ExpenseReport {

    List<Integer> expenses;

    static ExpenseReport fromInput(List<Integer> input) {
        return new ExpenseReport(input);
    }

    int fix() {
        return expensePermutations()
                .filter(ExpensePermutation::matches2020)
                .findFirst()
                .map(ExpensePermutation::multiply)
                .orElseThrow(IllegalArgumentException::new);
    }

    private Stream<ExpensePermutation> expensePermutations() {
        return expenses.stream().flatMap(this::permutationsForExpense);
    }

    private Stream<ExpensePermutation> permutationsForExpense(int expense) {
        return expenses.stream().map(otherExpense -> new ExpensePermutation(expense, otherExpense));
    }

    @Value
    private static class ExpensePermutation {

        int expense1;
        int expense2;

        boolean matches2020() {
            return expense1 + expense2 == 2020;
        }

        int multiply() {
            return expense1 * expense2;
        }
    }
}
