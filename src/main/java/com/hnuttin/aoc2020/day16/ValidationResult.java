package com.hnuttin.aoc2020.day16;

record ValidationResult(boolean valid, Integer invalidValue) {

	boolean isInvalid() {
		return !valid;
	}

}
