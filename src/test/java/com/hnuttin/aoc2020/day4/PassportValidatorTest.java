package com.hnuttin.aoc2020.day4;

import static com.hnuttin.aoc2020.common.input.InputParsers.aMultiLineParser;
import static com.hnuttin.aoc2020.day4.PassportValidator.countValidPassportStructures;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class PassportValidatorTest {

	@Test
	void testPart1() {
		List<PassportData> passportData = aMultiLineParser(PassportData::fromRawInput).parse(asList(
				"ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
				"byr:1937 iyr:2017 cid:147 hgt:183cm",
				"",
				"iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
				"hcl:#cfa07d byr:1929",
				"",
				"hcl:#ae17e1 iyr:2013",
				"eyr:2024",
				"ecl:brn pid:760753108 byr:1931",
				"hgt:179cm",
				"",
				"hcl:#cfa07d eyr:2025 pid:166559648",
				"iyr:2011 ecl:brn hgt:59in"
		));

		assertThat(countValidPassportStructures(passportData)).isEqualTo(2);
	}

}