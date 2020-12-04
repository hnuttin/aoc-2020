package com.hnuttin.aoc2020.common.app;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.lang3.time.StopWatch;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "forDay", setterPrefix = "with")
public class AocApp<I> {

	int day;

	Function<List<String>, I> inputParser;
	Function<String, I> inputEntryParser;
	Function<I, Object> part1;
	Function<I, Object> part2;

	private void run() {
		System.out.printf("Day %s...%n", day);
		System.out.println("Parsing input...");
		I parsedInput = inputParser.apply(InputReader.readInput(day));
		runPart(part1, parsedInput, 1);
		runPart(part2, parsedInput, 2);
	}

	private void runPart(Function<I, Object> part, I input, int partNumber) {
		if (part != null) {
			StopWatch stopWatch = StopWatch.createStarted();
			Object result = part.apply(input);
			stopWatch.stop();
			System.out.printf("\tPart %s (%sms): %s%n", partNumber, stopWatch.getTime(TimeUnit.MILLISECONDS), result);
		}
	}

	@SuppressWarnings("unchecked")
	public static <I> AocAppBuilder<I> forDay(int day) {
		return new AocAppBuilder<I>()
				.withDay(day)
				.withInputParser(rawInput -> (I) rawInput);
	}

	public static class AocAppBuilder<I> {

		public void run() {
			build().run();
		}

	}
}
