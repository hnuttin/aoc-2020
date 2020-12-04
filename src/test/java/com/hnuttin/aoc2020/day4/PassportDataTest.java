package com.hnuttin.aoc2020.day4;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PassportDataTest {

	private static Stream<Arguments> passportEntryValidations() {
		return Stream.of(
				Arguments.of(new PassportData.BirthYearEntry("2002"), true),
				Arguments.of(new PassportData.BirthYearEntry("2003"), false),
				Arguments.of(new PassportData.HeightEntry("60in"), true),
				Arguments.of(new PassportData.HeightEntry("190cm"), true),
				Arguments.of(new PassportData.HeightEntry("190in"), false),
				Arguments.of(new PassportData.HeightEntry("190"), false),
				Arguments.of(new PassportData.HairColorEntry("#123abc"), true),
				Arguments.of(new PassportData.HairColorEntry("#123abz"), false),
				Arguments.of(new PassportData.HairColorEntry("123abc"), false),
				Arguments.of(new PassportData.EyeColorEntry("brn"), true),
				Arguments.of(new PassportData.EyeColorEntry("wat"), false),
				Arguments.of(new PassportData.PassportIdEntry("000000001"), true),
				Arguments.of(new PassportData.PassportIdEntry("0123456789"), false));
	}

	@MethodSource
	@ParameterizedTest
	void passportEntryValidations(PassportData.PassportEntry entry, boolean valid) {
		assertThat(entry.isValid()).isEqualTo(valid);
	}

	private static Stream<Arguments> passportDataValidations() {
		return Stream.of(
				Arguments.of(PassportData.fromRawInput(asList("eyr:1972 cid:100", "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926")), false),
				Arguments.of(PassportData.fromRawInput(asList("iyr:2019", "hcl:#602927 eyr:1967 hgt:170cm", "ecl:grn pid:012533040 byr:1946")), false),
				Arguments.of(PassportData.fromRawInput(asList("hcl:dab227 iyr:2012", "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277")), false),
				Arguments.of(PassportData.fromRawInput(asList("hgt:59cm ecl:zzz", "eyr:2038 hcl:74454a iyr:2023", "pid:3556412378 byr:2007")), false),
				Arguments.of(PassportData.fromRawInput(asList("pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980", "hcl:#623a2f")), true),
				Arguments.of(PassportData.fromRawInput(asList("eyr:2029 ecl:blu cid:129 byr:1989", "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm")), true),
				Arguments.of(PassportData.fromRawInput(asList("hcl:#888785", "hgt:164cm byr:2001 iyr:2015 cid:88", "pid:545766238 ecl:hzl", "eyr:2022")), true),
				Arguments.of(PassportData.fromRawInput(singletonList("iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719")), true));
	}

	@MethodSource
	@ParameterizedTest
	void passportDataValidations(PassportData data, boolean valid) {
		assertThat(data.isValid()).isEqualTo(valid);
	}

}