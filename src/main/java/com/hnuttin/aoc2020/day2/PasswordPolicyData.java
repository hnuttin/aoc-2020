package com.hnuttin.aoc2020.day2;

import static java.lang.Integer.parseInt;

import lombok.Value;

@Value
class PasswordPolicyData {

	int digit1;
	int digit2;

	char requiredChar;

	static PasswordPolicyData fromString(String value) {
		String[] minMaxAndChar = value.split(" ");
		String[] minMax = minMaxAndChar[0].split("-");
		return new PasswordPolicyData(parseInt(minMax[0]), parseInt(minMax[1]), minMaxAndChar[1].charAt(0));
	}
}
