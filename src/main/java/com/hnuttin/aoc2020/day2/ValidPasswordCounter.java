package com.hnuttin.aoc2020.day2;

import java.util.List;

class ValidPasswordCounter {

	static long coundValidPasswords(List<PasswordPolicyDataWithPassword> input, PasswordPolicy passwordPolicy) {
		return input.stream()
				.filter(passwordPolicy::isValid)
				.count();
	}

}
