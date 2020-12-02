package com.hnuttin.aoc2020.day2;

import lombok.Value;

@Value
public class NewPasswordPolicy implements PasswordPolicy {

	@Override
	public boolean isValid(PasswordPolicyData policyData, String password) {
		char firstChar = password.charAt(policyData.getDigit1() - 1);
		char secondChar = password.charAt(policyData.getDigit2() - 1);
		return firstChar == policyData.getRequiredChar() ^ secondChar == policyData.getRequiredChar();
	}
}
