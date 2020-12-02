package com.hnuttin.aoc2020.day2;

import lombok.Value;

@Value
class OldPasswordPolicy implements PasswordPolicy {

	@Override
	public boolean isValid(PasswordPolicyData policyData, String password) {
		long requiredCharCount = password.chars().filter(passwordChar -> passwordChar == policyData.getRequiredChar()).count();
		return requiredCharCount >= policyData.getDigit1() && requiredCharCount <= policyData.getDigit2();
	}

}
