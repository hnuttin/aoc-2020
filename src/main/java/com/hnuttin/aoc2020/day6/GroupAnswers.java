package com.hnuttin.aoc2020.day6;

import java.util.List;

record GroupAnswers(List<String> answers) {

	long countAnyYesAnswers() {
		return answers.stream()
				.flatMap(personAnswers -> personAnswers.chars().boxed())
				.distinct()
				.count();
	}

	long countEveryoneYesAnswers() {
		return answers.stream()
				.reduce(null, (everyoneYesAnswers, personAnswers) -> {
					if (everyoneYesAnswers == null) {
						return personAnswers;
					} else {
						return everyoneYesAnswers.chars()
								.filter(answer -> personAnswers.indexOf(answer) > -1)
								.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
								.toString();
					}
				})
				.length();
	}

}
