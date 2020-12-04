package com.hnuttin.aoc2020.day4;

import java.util.List;

public class PassportValidator {

	static long countValidPassportStructures(List<PassportData> passportDataList) {
		return passportDataList.stream()
				.filter(PassportData::hasValidStructure)
				.count();
	}

	static long countValidPassport(List<PassportData> passportDataList) {
		return passportDataList.stream()
				.filter(PassportData::isValid)
				.count();
	}

}
