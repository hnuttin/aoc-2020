package com.hnuttin.aoc2020.day2;

import lombok.Value;

@Value
class PasswordPolicyDataWithPassword {

	PasswordPolicyData policyData;
	String password;

	static PasswordPolicyDataWithPassword fromString(String value) {
		String[] passwordpolicyAndPassword = value.split(": ");
		return new PasswordPolicyDataWithPassword(
				PasswordPolicyData.fromString(passwordpolicyAndPassword[0]),
				passwordpolicyAndPassword[1]);
	}
}
