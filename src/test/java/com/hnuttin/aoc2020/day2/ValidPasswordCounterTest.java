package com.hnuttin.aoc2020.day2;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidPasswordCounterTest {

	private List<PasswordPolicyDataWithPassword> testInput;

	@BeforeEach
	void setupTestInput() {
		Stream<String> testInputValue = Stream.of(
				"1-3 a: abcde",
				"1-3 b: cdefg",
				"2-9 c: ccccccccc"
		);

		testInput = testInputValue
				.map(PasswordPolicyDataWithPassword::fromString)
				.collect(toList());
	}

	@Test
	void testOldPasswordPolicy() {
		long validPasswords = ValidPasswordCounter.coundValidPasswords(testInput, new OldPasswordPolicy());

		assertThat(validPasswords).isEqualTo(2);
	}

	@Test
	void testNewPasswordPolicy() {
		long validPasswords = ValidPasswordCounter.coundValidPasswords(testInput, new NewPasswordPolicy());

		assertThat(validPasswords).isEqualTo(1);
	}

}