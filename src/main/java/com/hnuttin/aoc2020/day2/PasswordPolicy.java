package com.hnuttin.aoc2020.day2;

public interface PasswordPolicy {

	boolean isValid(PasswordPolicyData policyData, String password);

	default boolean isValid(PasswordPolicyDataWithPassword policyDataWithPassword) {
		PasswordPolicyData policyData = policyDataWithPassword.getPolicyData();
		String password = policyDataWithPassword.getPassword();
		return isValid(policyData, password);
	}
}
