package com.hnuttin.aoc2020.common.app;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.lang3.time.StopWatch;

import com.hnuttin.aoc2020.common.input.InputReader;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderMethodName = "forDay", setterPrefix = "with")
public class AocApp<I> {

	int day;

	I input;
	Function<List<String>, I> inputParser;
	Function<String, I> inputEntryParser;
	Function<I, Object> part1;
	Function<I, Object> part2;

	private void run() {
		System.out.printf("Day %s...%n", day);
		System.out.println("Parsing input...");
		runPart(part1, getParsedInput(), 1);
		runPart(part2, getParsedInput(), 2);
	}

	private I getParsedInput() {
		return input == null ? inputParser.apply(InputReader.readInput(day)) : input;
	}

	private void runPart(Function<I, Object> part, I input, int partNumber) {
		if (part != null) {
			StopWatch stopWatch = StopWatch.createStarted();
			Object result = part.apply(input);
			stopWatch.stop();
			String color = stopWatch.getTime(TimeUnit.MILLISECONDS) > 200 ? "\u001b[31m" : "";
			System.out.printf("\tPart %s (%s%sms%s): %s%n", partNumber, color, stopWatch.getTime(TimeUnit.MILLISECONDS), "\u001b[0m", result);
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
