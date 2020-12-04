package com.hnuttin.aoc2020.day4;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
class PassportData {

	private static final List<String> REQUIRED_FIELDS = Arrays.asList(
			BirthYearEntry.BIRTH_YEAR,
			IssueYearEntry.ISSUE_YEAR,
			ExpirationYearEntry.EXPIRATION_YEAR,
			HeightEntry.HEIGHT,
			HairColorEntry.HAIR_COLOR,
			EyeColorEntry.EYE_COLOR,
			PassportIdEntry.PASSPORT_ID);
	private static final List<String> OPTIONAL_FIELDS = Collections.singletonList(CountryIdEntry.COUNTRY_ID);

	List<PassportEntry> entries;

	public boolean isValid() {
		return hasValidStructure() && allFieldsAreValid();
	}

	public boolean hasValidStructure() {
		return containsRequiredFields() && remainingFieldsAreOptional();
	}

	private boolean containsRequiredFields() {
		List<String> fieldNames = entries.stream().map(PassportEntry::getFieldName).collect(toList());
		return fieldNames.containsAll(REQUIRED_FIELDS);
	}

	private boolean remainingFieldsAreOptional() {
		List<String> optionalFields = entries.stream()
				.filter(Predicate.not(PassportEntry::isRequired))
				.map(PassportEntry::getFieldName)
				.collect(toList());
		return optionalFields.isEmpty() || OPTIONAL_FIELDS.containsAll(optionalFields);
	}

	private boolean allFieldsAreValid() {
		return entries.stream().allMatch(PassportEntry::isValid);
	}

	static PassportData fromRawInput(List<String> rawInput) {
		return new PassportData(rawInput.stream()
				.flatMap(lineData -> Arrays.stream(lineData.split(" ")))
				.map(entry -> {
					String[] entryData = entry.split(":");
					return createPassportEntry(entryData[0], entryData[1]);
				})
				.collect(toList()));
	}

	private static PassportEntry createPassportEntry(String fieldName, String value) {
		return switch (fieldName) {
			case BirthYearEntry.BIRTH_YEAR -> new BirthYearEntry(value);
			case IssueYearEntry.ISSUE_YEAR -> new IssueYearEntry(value);
			case ExpirationYearEntry.EXPIRATION_YEAR -> new ExpirationYearEntry(value);
			case HeightEntry.HEIGHT -> new HeightEntry(value);
			case HairColorEntry.HAIR_COLOR -> new HairColorEntry(value);
			case EyeColorEntry.EYE_COLOR -> new EyeColorEntry(value);
			case PassportIdEntry.PASSPORT_ID -> new PassportIdEntry(value);
			case CountryIdEntry.COUNTRY_ID -> new CountryIdEntry(value);
			default -> new InvalidEntry(fieldName, value);
		};
	}

	@Value
	@NonFinal
	static abstract class PassportEntry {

		String fieldName;
		String value;

		boolean required;

		public PassportEntry(String fieldName, String value) {
			this.fieldName = fieldName;
			this.value = value;
			this.required = REQUIRED_FIELDS.contains(fieldName);
		}

		boolean isRequired() {
			return required;
		}

		abstract boolean isValid();

	}

	@Value
	@ToString(callSuper = true)
	@EqualsAndHashCode(callSuper = true)
	static class InvalidEntry extends PassportEntry {

		public InvalidEntry(String fieldName, String value) {
			super(fieldName, value);
		}

		@Override
		public boolean isValid() {
			return false;
		}
	}

	@Value
	static class YearValidator {

		int min;
		int max;

		public boolean isValid(String value) {
			return StringUtils.isNotBlank(value) && value.length() == 4 && betweenMinMax(value);
		}

		private boolean betweenMinMax(String value) {
			int year = Integer.parseInt(value);
			return min <= year && year <= max;
		}
	}

	@Value
	@ToString(callSuper = true)
	@EqualsAndHashCode(callSuper = true)
	static class BirthYearEntry extends PassportEntry {

		private static final String BIRTH_YEAR = "byr";

		YearValidator yearValidator;

		public BirthYearEntry(String value) {
			super(BIRTH_YEAR, value);
			this.yearValidator = new YearValidator(1920, 2002);
		}

		@Override
		boolean isValid() {
			return yearValidator.isValid(getValue());
		}
	}

	@Value
	@ToString(callSuper = true)
	@EqualsAndHashCode(callSuper = true)
	static class IssueYearEntry extends PassportEntry {

		private static final String ISSUE_YEAR = "iyr";

		YearValidator yearValidator;

		public IssueYearEntry(String value) {
			super(ISSUE_YEAR, value);
			this.yearValidator = new YearValidator(2010, 20202);
		}

		@Override
		boolean isValid() {
			return yearValidator.isValid(getValue());
		}

	}

	@Value
	@ToString(callSuper = true)
	@EqualsAndHashCode(callSuper = true)
	static class ExpirationYearEntry extends PassportEntry {

		private static final String EXPIRATION_YEAR = "eyr";

		YearValidator yearValidator;

		public ExpirationYearEntry(String value) {
			super(EXPIRATION_YEAR, value);
			this.yearValidator = new YearValidator(2020, 2030);
		}

		@Override
		boolean isValid() {
			return yearValidator.isValid(getValue());
		}

	}

	@Value
	@ToString(callSuper = true)
	@EqualsAndHashCode(callSuper = true)
	static class HeightEntry extends PassportEntry {

		private static final String HEIGHT = "hgt";

		public HeightEntry(String value) {
			super(HEIGHT, value);
		}

		@Override
		boolean isValid() {
			if (getValue().endsWith("cm")) {
				return isBetween(150, 193);
			} else if (getValue().endsWith("in")) {
				return isBetween(59, 76);
			} else {
				return false;
			}
		}

		private boolean isBetween(int min, int max) {
			return min <= parseHeight() && parseHeight() <= max;
		}

		private int parseHeight() {
			return Integer.parseInt(getValue().substring(0, getValue().length() - 2));
		}

	}

	@Value
	@ToString(callSuper = true)
	@EqualsAndHashCode(callSuper = true)
	static class HairColorEntry extends PassportEntry {

		private static final Pattern COLOR_MATCHER = Pattern.compile("^#[0-9a-f]{6}$");
		private static final String HAIR_COLOR = "hcl";

		public HairColorEntry(String value) {
			super(HAIR_COLOR, value);
		}

		@Override
		boolean isValid() {
			return COLOR_MATCHER.matcher(getValue()).matches();
		}

	}

	@Value
	@ToString(callSuper = true)
	@EqualsAndHashCode(callSuper = true)
	static class EyeColorEntry extends PassportEntry {

		private static final String EYE_COLOR = "ecl";

		public EyeColorEntry(String value) {
			super(EYE_COLOR, value);
		}

		@Override
		boolean isValid() {
			return Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(getValue());
		}

	}

	@Value
	@ToString(callSuper = true)
	@EqualsAndHashCode(callSuper = true)
	static class PassportIdEntry extends PassportEntry {

		private static final Pattern PASSPORT_ID_MATCHER = Pattern.compile("^[0-9]{9}$");
		private static final String PASSPORT_ID = "pid";

		public PassportIdEntry(String value) {
			super(PASSPORT_ID, value);
		}

		@Override
		boolean isValid() {
			return PASSPORT_ID_MATCHER.matcher(getValue()).matches();
		}

	}

	@Value
	@ToString(callSuper = true)
	@EqualsAndHashCode(callSuper = true)
	static class CountryIdEntry extends PassportEntry {

		private static final String COUNTRY_ID = "cid";

		public CountryIdEntry(String value) {
			super(COUNTRY_ID, value);
		}

		@Override
		boolean isValid() {
			return true;
		}

	}
}
