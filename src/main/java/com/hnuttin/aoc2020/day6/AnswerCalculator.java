package com.hnuttin.aoc2020.day6;

import java.util.List;

class AnswerCalculator {

	static long countAllAnyYesAnswers(List<GroupAnswers> groupAnswers) {
		return groupAnswers.stream()
				.map(GroupAnswers::countAnyYesAnswers)
				.reduce(Long::sum)
				.orElseThrow();
	}

	static long countAllEveryoneYesAnswers(List<GroupAnswers> groupAnswers) {
		return groupAnswers.stream()
				.map(GroupAnswers::countEveryoneYesAnswers)
				.reduce(Long::sum)
				.orElseThrow();
	}

}
